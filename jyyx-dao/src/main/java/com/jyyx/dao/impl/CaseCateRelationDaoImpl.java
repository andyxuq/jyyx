package com.jyyx.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jyyx.dao.CaseCateRelationDao;
import com.jyyx.dao.mysql.dao.CaseCateRelationMapper;
import com.jyyx.dao.mysql.entity.CaseCateRelation;
import com.jyyx.dao.mysql.entity.CaseCateRelationExample;
import com.jyyx.dao.mysql.entity.CaseCateRelationKey;

/**
 * 案例-分类关系管理实现
 * andy xu
 * 2016年11月9日
 */
@Repository
public class CaseCateRelationDaoImpl implements CaseCateRelationDao {

	@Autowired
	private CaseCateRelationMapper ccrMapper;
	
	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductCateRelationDao#deleteResources(int, int)
	 */
	public void deleteResources(int caseId, int categoryId) {
		CaseCateRelationKey relationKey = new CaseCateRelationKey();
		relationKey.setCategoryId(categoryId);
		relationKey.setCaseId(caseId);
		
		ccrMapper.deleteByPrimaryKey(relationKey);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductCateRelationDao#deleteResourcesByResId(int)
	 */
	public void deleteResourcesByResId(int caseId) {
		CaseCateRelationExample example = new CaseCateRelationExample();
		example.createCriteria().andCaseIdEqualTo(caseId);
		
		ccrMapper.deleteByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductCateRelationDao#addResources(com.jyyx.dao.mysql.entity.ProductCateRelation)
	 */
	public void addResources(CaseCateRelation caseCate) {
		caseCate.setCreateTime(new Date());
		ccrMapper.insertSelective(caseCate);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductCateRelationDao#modifyResources(com.jyyx.dao.mysql.entity.ProductCateRelation)
	 */
	public void modifyResources(CaseCateRelation caseCate) {
		ccrMapper.updateByPrimaryKey(caseCate);
	}
}
