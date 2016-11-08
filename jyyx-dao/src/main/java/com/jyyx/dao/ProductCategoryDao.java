package com.jyyx.dao;

import java.util.List;

import com.jyyx.dao.mysql.entity.ProductCategory;

/**
 * 产品分类dao接口
 * andy xu
 * 2016年11月8日
 */
public interface ProductCategoryDao {

	/**
	 * 根据ID查找资源
	 * @param picId
	 * @return
	 */
	ProductCategory getResourcesById(int resourceId);
	
	/** 查询资源 */
	List<ProductCategory> getResources(ProductCategory productCategory);
	
	/** 删除资源  */
	void deleteResources(int resourceId);
	
	/** 添加资源 */
	void addResources(ProductCategory productCategory);
	
	/** 修改资源 */
	void modifyResources(ProductCategory productCategory);
}
