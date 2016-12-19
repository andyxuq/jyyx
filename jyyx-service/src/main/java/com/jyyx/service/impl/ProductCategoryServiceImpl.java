package com.jyyx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyyx.core.exception.JyException;
import com.jyyx.dao.ProductCategoryDao;
import com.jyyx.dao.model.JyProductCategory;
import com.jyyx.dao.mysql.entity.ProductCategory;
import com.jyyx.service.ProductCategoryService;

/**
 * andy xu
 * 2016年11月8日
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#addResources(com.jyyx.dao.mysql.entity.ProductCategory)
	 */
	public void addResources(ProductCategory productCategory) throws JyException {
		checkRes(productCategory);
		productCategoryDao.addResources(productCategory);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#modifyResources(com.jyyx.dao.mysql.entity.ProductCategory)
	 */
	public void modifyResources(ProductCategory productCategory) throws JyException {
		checkRes(productCategory);
		productCategoryDao.modifyResources(productCategory);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#modifyResourcesOrders(java.util.Map)
	 */
	@Transactional(rollbackFor = Exception.class)
	public void modifyResourcesOrders(Map<Integer, Integer> ordersMap) throws JyException {
		try {
			for (Map.Entry<Integer, Integer> entry : ordersMap.entrySet()) {
				ProductCategory productCategory = productCategoryDao.getResourcesById(entry.getKey());
				if (null == productCategory) {
					throw new JyException("无法修改" + entry.getKey() + "的排序号，资源不存在");
				}
				
				productCategory.setOrderCode(entry.getValue());
				productCategoryDao.modifyResources(productCategory);
			}
		} catch (Exception e) {
			throw new JyException("批量修改排序号" + ordersMap + "出错", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#getResources(com.jyyx.dao.mysql.entity.ProductCategory)
	 */
	public List<JyProductCategory> getResources(ProductCategory productCategory) throws JyException {
		try {
			return productCategoryDao.getResources(productCategory);
		} catch (Exception e) {
			throw new JyException("获取产品分类出错", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#deleteResources(int)
	 */
	public void deleteResources(int resourceId) {
		productCategoryDao.deleteResources(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#getResourcesById(int)
	 */
	public ProductCategory getResourcesById(int resourceId) throws JyException {
		try {
			return productCategoryDao.getResourcesById(resourceId);
		} catch (Exception e) {
			throw new JyException("查询产品-分类详情出错", e);
		}
	}
	
	private void checkRes(ProductCategory productCategory) throws JyException {
		if (0 != productCategory.getParentId()) {
			ProductCategory parentRes = getResourcesById(productCategory.getParentId());
			if (null == parentRes) {
				throw new JyException("父分类资源" + productCategory.getParentId() + "不存在");
			}
			if (parentRes.getParentId() != 0) {
				throw new JyException("资源级数添加过多，只能添加两级资源信息");
			}
		}
	}
}
