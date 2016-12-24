package com.jyyx.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jyyx.core.enums.PicCodeType;
import com.jyyx.dao.PicDao;
import com.jyyx.dao.ProductCateRelationDao;
import com.jyyx.dao.ProductCategoryDao;
import com.jyyx.dao.ProductDao;
import com.jyyx.dao.model.CaseParam;
import com.jyyx.dao.model.JyCase;
import com.jyyx.dao.model.JyCaseCategory;
import com.jyyx.dao.model.JyProduct;
import com.jyyx.dao.model.JyProductCategory;
import com.jyyx.dao.model.ProductParam;
import com.jyyx.dao.mysql.dao.ProductMapper;
import com.jyyx.dao.mysql.dao.extend.ProductExtendMapper;
import com.jyyx.dao.mysql.entity.Pic;
import com.jyyx.dao.mysql.entity.Product;
import com.jyyx.dao.mysql.entity.ProductCateRelation;
import com.jyyx.dao.utils.ModelUtils;
import com.jyyx.dao.utils.PageData;
import com.jyyx.dao.utils.PageInfo;

/**
 * 产品dao实现
 * andy xu
 * 2016年11月10日
 */
@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private ProductMapper productMapper;
	
	@Autowired 
	ProductExtendMapper productExtendMapper;
	
	@Autowired
	private ProductCateRelationDao pcrDao;
	
	@Autowired 
	private PicDao picDao;
	
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductDao#getResourcesCount(com.jyyx.dao.model.ProductParam)
	 */
	public int getResourcesCount(ProductParam param) {
		return productExtendMapper.countByParam(param);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductDao#getResourcesById(int)
	 */
	public JyProduct getResourcesById(int resourceId) throws BeansException, InstantiationException, IllegalAccessException {
		ProductParam param = new ProductParam();
		param.setId(resourceId);
		List<JyProduct> caseList = productExtendMapper.selectByParam(param);
		
		fetchPicRes(caseList);
		fetchCategoryRelation(caseList);
		return caseList.isEmpty() ? null : caseList.get(0);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductDao#getResources(com.jyyx.dao.model.ProductParam)
	 */
	public List<JyProduct> getResources(ProductParam param) throws BeansException, InstantiationException, IllegalAccessException {
		List<JyProduct> jyProductList = productExtendMapper.selectByParam(param);
		fetchPicRes(jyProductList);
		fetchCategoryRelation(jyProductList);
		return jyProductList;
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductDao#getResourcesWithPage(com.jyyx.dao.model.ProductParam, com.jyyx.dao.utils.PageInfo)
	 */
	public PageData<JyProduct> getResourcesWithPage(ProductParam param, PageInfo pageInfo) throws BeansException, InstantiationException, IllegalAccessException {
		param.setStartRow(pageInfo.getStartRow());
		param.setPageRow(pageInfo.getPageRow());
		
		List<JyProduct> jyProductList = productExtendMapper.selectByParam(param);
		fetchPicRes(jyProductList);
		fetchCategoryRelation(jyProductList);
		
		PageData<JyProduct> pageData = new PageData<JyProduct>(pageInfo);
		pageData.setPageData(jyProductList);
		return pageData;
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductDao#deleteResources(int)
	 */
	public void deleteResources(int resourceId) {
		pcrDao.deleteResourcesByResId(resourceId);
		productMapper.deleteByPrimaryKey(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductDao#addResources(com.jyyx.dao.model.ProductParam)
	 */
	@Transactional(rollbackFor = Exception.class)
	public void addResources(ProductParam param) {
		Product product = getProductFromParam(param);
		productExtendMapper.insertSelective(product);
		for (ProductCateRelation pcr : param.getProductCategorys()) {
			pcr.setProductId(product.getId());
			pcrDao.addResources(pcr);
		}
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductDao#modifyResources(com.jyyx.dao.model.ProductParam)
	 */
	@Transactional(rollbackFor = Exception.class)
	public void modifyResources(ProductParam param) {
		pcrDao.deleteResourcesByResId(param.getId());
		
		Product product = getProductFromParam(param);
		product.setId(param.getId());
		
		productMapper.updateByPrimaryKeySelective(product);
		for (ProductCateRelation pcr : param.getProductCategorys()) {
			pcr.setProductId(product.getId());
			pcrDao.addResources(pcr);
		}
	}
	
	private Product getProductFromParam(ProductParam param) {
		Product product = new Product();
		product.setProductName(param.getProductName());
		product.setProductDesc(param.getProductDesc());
		product.setProductLink(param.getProductLink());
		product.setCreateTime(new Date());
		
		return product;
	}
	
	private void fetchPicRes(List<JyProduct> productList) {
		Map<Integer, JyProduct> productMap = new HashMap<Integer, JyProduct>();
		for (JyProduct product : productList) {
			productMap.put(product.getId(), product);
		}
		
		if (productMap.size() > 0) {
			Map<Integer, List<Pic>> picMap = picDao.getResourceByCode(PicCodeType.PRODUCT_HEADER, productMap.keySet());
			for (int id : picMap.keySet()) {
				productMap.get(id).setPics(picMap.get(id));
			}
		}
	}
	
	private void fetchCategoryRelation(List<JyProduct> jyProductList) throws BeansException, InstantiationException, IllegalAccessException {
		Map<Integer, JyProductCategory> parentMap = new HashMap<Integer, JyProductCategory>();
		Set<Integer> parentIds = new HashSet<Integer>();
		
		for (JyProduct jyCase : jyProductList) {
			for (JyProductCategory category : jyCase.getProductCategorys()) {
				if (category.getParentId() != 0) {
					parentIds.add(category.getParentId());
				}
			}
		}
		if (!parentIds.isEmpty()) {
			List<JyProductCategory> parentCategoryList = productCategoryDao.getResouceByIds(parentIds);
			for (JyProductCategory parentCategory : parentCategoryList) {
				parentMap.put(parentCategory.getId(), parentCategory);
			}
		}

		for (JyProduct jyCase : jyProductList) {
			List<JyProductCategory> categoryList = new ArrayList<JyProductCategory>();
			for (JyProductCategory childCategory : jyCase.getProductCategorys()) {
				JyProductCategory parentCategory = parentMap.get(childCategory.getParentId());
				if (null != parentCategory) {
					JyProductCategory newParent = new JyProductCategory();
					newParent.setId(parentCategory.getId());
					newParent.setCategoryName(parentCategory.getCategoryName());
					newParent.setCreateTime(parentCategory.getCreateTime());
					newParent.setUpdatedTime(parentCategory.getUpdatedTime());
					newParent.setOrderCode(parentCategory.getOrderCode());
					newParent.setParentId(parentCategory.getParentId());
					
					newParent.getChildList().add(childCategory);
					categoryList.add(newParent);
				} else {
					categoryList.add(childCategory);
				}
			}
			jyCase.setProductCategorys(categoryList);
		}
	}
}
