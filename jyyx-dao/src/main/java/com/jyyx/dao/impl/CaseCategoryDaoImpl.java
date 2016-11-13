package com.jyyx.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jyyx.dao.CaseCategoryDao;
import com.jyyx.dao.model.JyCaseCategory;
import com.jyyx.dao.model.JyProductCategory;
import com.jyyx.dao.mysql.dao.CaseCategoryMapper;
import com.jyyx.dao.mysql.entity.CaseCategory;
import com.jyyx.dao.mysql.entity.CaseCategoryExample;
import com.jyyx.dao.mysql.entity.ProductCategory;
import com.jyyx.dao.mysql.entity.ProductCategoryExample;
import com.jyyx.dao.mysql.entity.CaseCategoryExample.Criteria;
import com.jyyx.dao.mysql.entity.CaseExample;
import com.jyyx.dao.utils.ModelUtils;

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
	public JyCaseCategory getResourcesById(int resourceId) throws BeansException, InstantiationException, IllegalAccessException {
		CaseCategory caseCategory = caseCategoryMapper.selectByPrimaryKey(resourceId);
		JyCaseCategory jyCaseCategory = ModelUtils.copyProperty(caseCategory, JyCaseCategory.class);
		loopCategoryRelation(jyCaseCategory);
		
		return jyCaseCategory;
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.CaseCategoryDao#getResources(com.jyyx.dao.mysql.entity.CaseCategory)
	 */
	public List<JyCaseCategory> getResources(CaseCategory caseCategory) throws BeansException, InstantiationException, IllegalAccessException {
		CaseCategoryExample example = new CaseCategoryExample();
		Criteria criteria = example.createCriteria();
		boolean hasCondition = false;
		if (null != caseCategory.getId()) {
			criteria.andIdEqualTo(caseCategory.getId());
			hasCondition = true;
		}
		if (StringUtils.isNotBlank(caseCategory.getCategoryName())) {
			criteria.andCategoryNameLike("%" + caseCategory.getCategoryName() + "%");
			hasCondition = true;
		}
		if (null != caseCategory.getParentId()) {
			criteria.andParentIdEqualTo(caseCategory.getParentId());
			hasCondition = true;
		}
		if (!hasCondition) {
			criteria.andParentIdEqualTo(0);
		}
		example.setOrderByClause("order_code asc, create_time desc");
		List<CaseCategory> caseList = caseCategoryMapper.selectByExample(example);
		List<JyCaseCategory> jyList = ModelUtils.copyList(caseList, JyCaseCategory.class);
		for (JyCaseCategory jyCaseCategory : jyList) {
			loopCategoryRelation(jyCaseCategory);
		}
		return jyList;
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
	
	/**
	 * 该方法在分类庞大，访问量大的时候会有问题（需要优化）
	 * @param category
	 * @throws BeansException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private void loopCategoryRelation(JyCaseCategory category) throws BeansException, InstantiationException, IllegalAccessException {
		CaseCategoryExample example = new CaseCategoryExample();
		example.createCriteria().andParentIdEqualTo(category.getId());
		example.setOrderByClause("order_code asc, create_time desc");
		
		List<CaseCategory> subList = caseCategoryMapper.selectByExample(example);
		List<JyCaseCategory> subJyList = ModelUtils.copyList(subList, JyCaseCategory.class);
		category.setChildList(subJyList);
		
		for (JyCaseCategory subCaseCategory : subJyList) {			
			loopCategoryRelation(subCaseCategory);
		}
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.CaseCategoryDao#getResouceByIds(java.util.Set)
	 */
	public List<JyCaseCategory> getResouceByIds(Set<Integer> ids) throws BeansException, InstantiationException, IllegalAccessException {
		List<Integer> idList = new ArrayList<Integer>();
		idList.addAll(ids);
		CaseCategoryExample example = new CaseCategoryExample();
		example.createCriteria().andIdIn(idList);
		
		List<CaseCategory> categoryList = caseCategoryMapper.selectByExample(example);
		return ModelUtils.copyList(categoryList, JyCaseCategory.class);
	}
}
