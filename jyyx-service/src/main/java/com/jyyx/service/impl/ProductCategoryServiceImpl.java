package com.jyyx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyyx.core.exception.JyException;
import com.jyyx.dao.ProductCategoryDao;
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
	public void addResources(ProductCategory productCategory) {
		productCategoryDao.addResources(productCategory);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#modifyResources(com.jyyx.dao.mysql.entity.ProductCategory)
	 */
	public void modifyResources(ProductCategory productCategory) throws JyException {
		productCategoryDao.modifyResources(productCategory);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#modifyResourcesOrders(java.util.Map)
	 */
	@Transactional(rollbackFor = Exception.class)
	public void modifyResourcesOrders(Map<Integer, Integer> ordersMap) throws JyException {
		for (Map.Entry<Integer, Integer> entry : ordersMap.entrySet()) {
			ProductCategory productCategory = productCategoryDao.getResourcesById(entry.getKey());
			if (null == productCategory) {
				throw new JyException("无法修改" + entry.getKey() + "的排序号，资源不存在");
			}
			
			productCategory.setOrderCode(entry.getValue());
			productCategoryDao.modifyResources(productCategory);
		}
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#getResources(com.jyyx.dao.mysql.entity.ProductCategory)
	 */
	public List<ProductCategory> getResources(ProductCategory productCategory) {
		return productCategoryDao.getResources(productCategory);
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
	public ProductCategory getResourcesById(int resourceId) {
		return productCategoryDao.getResourcesById(resourceId);
	}
	
}