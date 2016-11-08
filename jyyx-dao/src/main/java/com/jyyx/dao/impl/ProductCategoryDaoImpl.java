package com.jyyx.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jyyx.dao.ProductCategoryDao;
import com.jyyx.dao.mysql.dao.ProductCategoryMapper;
import com.jyyx.dao.mysql.entity.ProductCategory;
import com.jyyx.dao.mysql.entity.ProductCategoryExample;
import com.jyyx.dao.mysql.entity.ProductCategoryExample.Criteria;

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
	public ProductCategory getResourcesById(int resourceId) {
		return productCategoryMapper.selectByPrimaryKey(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductCategoryDao#getResources(com.jyyx.dao.mysql.entity.ProductCategory)
	 */
	public List<ProductCategory> getResources(ProductCategory productCategory) {
		ProductCategoryExample example = new ProductCategoryExample();
		Criteria criteria = example.createCriteria();
		if (null != productCategory.getId()) {
			criteria.andIdEqualTo(productCategory.getId());
		}
		if (StringUtils.isNotBlank(productCategory.getCategoryName())) {
			criteria.andCategoryNameLike("%" + productCategory.getCategoryName() + "%");
		}
		if (null != productCategory.getParentId()) {
			criteria.andParentIdEqualTo(productCategory.getParentId());
		}
		example.setOrderByClause("order_code asc, create_time desc");
		return productCategoryMapper.selectByExample(example);
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
}
