package com.jyyx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyyx.core.exception.JyException;
import com.jyyx.dao.CaseCategoryDao;
import com.jyyx.dao.ProductCategoryDao;
import com.jyyx.dao.mysql.entity.CaseCategory;
import com.jyyx.service.CaseCategoryService;
import com.jyyx.service.ProductCategoryService;

/**
 * andy xu
 * 2016年11月8日
 */
@Service
public class CaseCategoryServiceImpl implements CaseCategoryService {

	@Autowired
	private CaseCategoryDao caseCategoryDao;
	
	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#addResources(com.jyyx.dao.mysql.entity.CaseCategory)
	 */
	public void addResources(CaseCategory caseCategory) {
		caseCategoryDao.addResources(caseCategory);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#modifyResources(com.jyyx.dao.mysql.entity.CaseCategory)
	 */
	public void modifyResources(CaseCategory caseCategory) throws JyException {
		caseCategoryDao.modifyResources(caseCategory);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#modifyResourcesOrders(java.util.Map)
	 */
	@Transactional(rollbackFor = Exception.class)
	public void modifyResourcesOrders(Map<Integer, Integer> ordersMap) throws JyException {
		for (Map.Entry<Integer, Integer> entry : ordersMap.entrySet()) {
			CaseCategory caseCategory = caseCategoryDao.getResourcesById(entry.getKey());
			if (null == caseCategory) {
				throw new JyException("无法修改" + entry.getKey() + "的排序号，资源不存在");
			}
			
			caseCategory.setOrderCode(entry.getValue());
			caseCategoryDao.modifyResources(caseCategory);
		}
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#getResources(com.jyyx.dao.mysql.entity.CaseCategory)
	 */
	public List<CaseCategory> getResources(CaseCategory caseCategory) {
		return caseCategoryDao.getResources(caseCategory);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#deleteResources(int)
	 */
	public void deleteResources(int resourceId) {
		caseCategoryDao.deleteResources(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#getResourcesById(int)
	 */
	public CaseCategory getResourcesById(int resourceId) {
		return caseCategoryDao.getResourcesById(resourceId);
	}
	
}
