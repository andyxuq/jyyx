package com.jyyx.dao;

import com.jyyx.dao.mysql.entity.ProductCateRelation;

/**
 * 产品-分类关系dao接口
 * andy xu
 * 2016年11月9日
 */
public interface ProductCateRelationDao {

	/** 删除资源  */
	void deleteResources(int productId, int categoryId);
	
	/** 根据资源ID删除关系 */
	void deleteResourcesByResId(int productId);
	
	/** 添加资源 */
	void addResources(ProductCateRelation productCate);
	
	/** 修改资源 */
	void modifyResources(ProductCateRelation productCate);
}
