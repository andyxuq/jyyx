package com.jyyx.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jyyx.dao.ProductCateRelationDao;
import com.jyyx.dao.mysql.dao.ProductCateRelationMapper;
import com.jyyx.dao.mysql.entity.ProductCateRelation;
import com.jyyx.dao.mysql.entity.ProductCateRelationExample;
import com.jyyx.dao.mysql.entity.ProductCateRelationKey;

/**
 * 产品-分类关系管理实现
 * andy xu
 * 2016年11月9日
 */
@Repository
public class ProductCateRelationDaoImpl implements ProductCateRelationDao {

	@Autowired
	private ProductCateRelationMapper pcrMapper;
	
	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductCateRelationDao#deleteResources(int, int)
	 */
	public void deleteResources(int productId, int categoryId) {
		ProductCateRelationKey relationKey = new ProductCateRelationKey();
		relationKey.setCategoryId(categoryId);
		relationKey.setProductId(productId);
		
		pcrMapper.deleteByPrimaryKey(relationKey);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductCateRelationDao#deleteResourcesByResId(int)
	 */
	public void deleteResourcesByResId(int productId) {
		ProductCateRelationExample example = new ProductCateRelationExample();
		example.createCriteria().andProductIdEqualTo(productId);
		
		pcrMapper.deleteByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductCateRelationDao#addResources(com.jyyx.dao.mysql.entity.ProductCateRelation)
	 */
	public void addResources(ProductCateRelation productCate) {
		pcrMapper.insertSelective(productCate);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductCateRelationDao#modifyResources(com.jyyx.dao.mysql.entity.ProductCateRelation)
	 */
	public void modifyResources(ProductCateRelation productCate) {
		pcrMapper.updateByPrimaryKey(productCate);
	}
}
