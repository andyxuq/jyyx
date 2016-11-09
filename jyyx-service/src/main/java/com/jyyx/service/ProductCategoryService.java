package com.jyyx.service;

import java.util.List;
import java.util.Map;

import com.jyyx.core.exception.JyException;
import com.jyyx.dao.model.JyProductCategory;
import com.jyyx.dao.mysql.entity.ProductCategory;

/**
 * andy xu
 * 2016年11月8日
 */
public interface ProductCategoryService {
	
	/** 添加资源 */
	void addResources(ProductCategory productCategory);
	
	/** 修改资源 
	 * @throws JyException */
	void modifyResources(ProductCategory productCategory) throws JyException;
	
	/** 批量修改排序号 
	 * @throws JyException */
	void modifyResourcesOrders(Map<Integer, Integer> ordersMap) throws JyException;
	
	/** 查询资源 
	 * @throws JyException */
	List<JyProductCategory> getResources(ProductCategory productCategory) throws JyException;
	
	/** 删除资源 */
	void deleteResources(int resourceId);
	
	/** 根据ID查询资源 
	 * @throws JyException */
	ProductCategory getResourcesById(int resourceId) throws JyException;
}
