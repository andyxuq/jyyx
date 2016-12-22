package com.jyyx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyyx.core.exception.JyException;
import com.jyyx.dao.CaseCategoryDao;
import com.jyyx.dao.ProductCategoryDao;
import com.jyyx.dao.model.JyCaseCategory;
import com.jyyx.dao.mysql.entity.CaseCategory;
import com.jyyx.dao.mysql.entity.ProductCategory;
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
	public void addResources(CaseCategory caseCategory) throws JyException {
		checkRes(caseCategory);
		caseCategoryDao.addResources(caseCategory);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#modifyResources(com.jyyx.dao.mysql.entity.CaseCategory)
	 */
	public void modifyResources(CaseCategory caseCategory) throws JyException {
		checkRes(caseCategory);
		caseCategoryDao.modifyResources(caseCategory);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#modifyResourcesOrders(java.util.Map)
	 */
	@Transactional(rollbackFor = Exception.class)
	public void modifyResourcesOrders(Map<Integer, Integer> ordersMap) throws JyException {
		try {
		for (Map.Entry<Integer, Integer> entry : ordersMap.entrySet()) {
			CaseCategory caseCategory = caseCategoryDao.getResourcesById(entry.getKey());
			if (null == caseCategory) {
				throw new JyException("无法修改" + entry.getKey() + "的排序号，资源不存在");
			}
			
			caseCategory.setOrderCode(entry.getValue());
			caseCategoryDao.modifyResources(caseCategory);
		}
		} catch (Exception e) {
			throw new JyException("批量修改案例-分类排序号" + ordersMap + "出错", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductCategoryService#getResources(com.jyyx.dao.mysql.entity.CaseCategory)
	 */
	public List<JyCaseCategory> getResources(CaseCategory caseCategory) throws JyException {
		try {
			return caseCategoryDao.getResources(caseCategory);
		} catch (Exception e) {
			throw new JyException("查询案例-分类出错", e);
		}
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
	public CaseCategory getResourcesById(int resourceId) throws JyException {
		try {
			return caseCategoryDao.getResourcesById(resourceId);
		} catch (Exception e) {
			throw new JyException("查询案例-分类详情出错", e);
		}
	}
	
	private void checkRes(CaseCategory caseCategory) throws JyException {
		if (0 != caseCategory.getParentId()) {
			CaseCategory parentRes = getResourcesById(caseCategory.getParentId());
			if (null == parentRes) {
				throw new JyException("父分类资源" + caseCategory.getParentId() + "不存在");
			}
			if (parentRes.getParentId() != 0) {
				throw new JyException("资源级数添加过多，只能添加两级资源信息");
			}
		}
	}
	
}
