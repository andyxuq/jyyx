package com.jyyx.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jyyx.dao.CaseCategoryDao;
import com.jyyx.dao.mysql.dao.CaseCategoryMapper;
import com.jyyx.dao.mysql.entity.CaseCategory;
import com.jyyx.dao.mysql.entity.CaseCategoryExample;
import com.jyyx.dao.mysql.entity.CaseCategoryExample.Criteria;

/**
 * case category dao实现
 * andy xu
 * 2016年11月8日
 */
@Repository
public class CaseCategoryDaoImpl implements CaseCategoryDao {

	@Autowired
	private CaseCategoryMapper caseCategoryMapper;
	
	/* (non-Javadoc)
	 * @see com.jyyx.dao.CaseCategoryDao#getResourcesById(int)
	 */
	public CaseCategory getResourcesById(int resourceId) {
		return caseCategoryMapper.selectByPrimaryKey(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.CaseCategoryDao#getResources(com.jyyx.dao.mysql.entity.CaseCategory)
	 */
	public List<CaseCategory> getResources(CaseCategory caseCategory) {
		CaseCategoryExample example = new CaseCategoryExample();
		Criteria criteria = example.createCriteria();
		if (null != caseCategory.getId()) {
			criteria.andIdEqualTo(caseCategory.getId());
		}
		if (StringUtils.isNotBlank(caseCategory.getCategoryName())) {
			criteria.andCategoryNameLike("%" + caseCategory.getCategoryName() + "%");
		}
		if (null != caseCategory.getParentId()) {
			criteria.andParentIdEqualTo(caseCategory.getParentId());
		}
		example.setOrderByClause("order_code asc, create_time desc");
		return caseCategoryMapper.selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.CaseCategoryDao#deleteResources(int)
	 */
	public void deleteResources(int resourceId) {
		caseCategoryMapper.deleteByPrimaryKey(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.CaseCategoryDao#addResources(com.jyyx.dao.mysql.entity.CaseCategory)
	 */
	public void addResources(CaseCategory CaseCategory) {
		CaseCategory.setCreateTime(new Date());
		caseCategoryMapper.insertSelective(CaseCategory);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.CaseCategoryDao#modifyResources(com.jyyx.dao.mysql.entity.CaseCategory)
	 */
	public void modifyResources(CaseCategory CaseCategory) {
		caseCategoryMapper.updateByPrimaryKeySelective(CaseCategory);
	}
}
