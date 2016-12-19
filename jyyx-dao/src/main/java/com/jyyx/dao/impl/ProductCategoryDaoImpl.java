package com.jyyx.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jyyx.dao.ProductCategoryDao;
import com.jyyx.dao.model.JyProductCategory;
import com.jyyx.dao.mysql.dao.ProductCategoryMapper;
import com.jyyx.dao.mysql.entity.ProductCategory;
import com.jyyx.dao.mysql.entity.ProductCategoryExample;
import com.jyyx.dao.mysql.entity.ProductCategoryExample.Criteria;
import com.jyyx.dao.utils.ModelUtils;

/**
 * product category dao实现
 * andy xu
 * 2016年11月8日
 */
@Repository
public class ProductCategoryDaoImpl implements ProductCategoryDao {

	@Autowired
	private ProductCategoryMapper productCategoryMapper;
	
	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductCategoryDao#getResourcesById(int)
	 */
	public JyProductCategory getResourcesById(int resourceId) throws BeansException, InstantiationException, IllegalAccessException {
		ProductCategory productCategory = productCategoryMapper.selectByPrimaryKey(resourceId);
		JyProductCategory jyProductCategory = ModelUtils.copyProperty(productCategory, JyProductCategory.class);
		loopCategoryRelation(jyProductCategory);
		
		return jyProductCategory;
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductCategoryDao#getResources(com.jyyx.dao.mysql.entity.ProductCategory)
	 */
	public List<JyProductCategory> getResources(ProductCategory productCategory) throws BeansException, InstantiationException, IllegalAccessException {
		ProductCategoryExample example = new ProductCategoryExample();
		Criteria criteria = example.createCriteria();
		boolean hasCondition = false;
		if (null != productCategory.getId()) {
			criteria.andIdEqualTo(productCategory.getId());
			hasCondition = true;
		}
		if (StringUtils.isNotBlank(productCategory.getCategoryName())) {
			criteria.andCategoryNameLike("%" + productCategory.getCategoryName() + "%");
			hasCondition = true;
		}
		if (null != productCategory.getParentId()) {
			criteria.andParentIdEqualTo(productCategory.getParentId());
			hasCondition = true;
		}
		if (!hasCondition) {
			criteria.andParentIdEqualTo(0);
		}
		example.setOrderByClause("order_code asc, create_time desc");
		List<ProductCategory> productCateList = productCategoryMapper.selectByExample(example);
		
		List<JyProductCategory> jyList = ModelUtils.copyList(productCateList, JyProductCategory.class);
		for (JyProductCategory jyCategory : jyList) {
			loopCategoryRelation(jyCategory);
		}
		return jyList;
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductCategoryDao#deleteResources(int)
	 */
	public void deleteResources(int resourceId) {
		productCategoryMapper.deleteByPrimaryKey(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductCategoryDao#addResources(com.jyyx.dao.mysql.entity.ProductCategory)
	 */
	public void addResources(ProductCategory productCategory) {
		productCategory.setCreateTime(new Date());
		productCategoryMapper.insertSelective(productCategory);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductCategoryDao#modifyResources(com.jyyx.dao.mysql.entity.ProductCategory)
	 */
	public void modifyResources(ProductCategory productCategory) {
		productCategoryMapper.updateByPrimaryKeySelective(productCategory);
	}
	
	/**
	 * 该方法在分类庞大，访问量大的时候会有问题（需要优化）
	 * @param category
	 * @throws BeansException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private void loopCategoryRelation(JyProductCategory category) throws BeansException, InstantiationException, IllegalAccessException {
		if (null == category) {
			return;
		}
		ProductCategoryExample example = new ProductCategoryExample();
		example.createCriteria().andParentIdEqualTo(category.getId());
		example.setOrderByClause("order_code asc, create_time desc");
		
		List<ProductCategory> subList = productCategoryMapper.selectByExample(example);
		List<JyProductCategory> subJyList = ModelUtils.copyList(subList, JyProductCategory.class);
		category.setChildList(subJyList);
		
		for (JyProductCategory subProductCategory : subJyList) {			
			loopCategoryRelation(subProductCategory);
		}
	}
}
