package com.jyyx.service;

import java.util.List;
import java.util.Map;

import com.jyyx.core.exception.JyException;
import com.jyyx.dao.model.JyCaseCategory;
import com.jyyx.dao.mysql.entity.CaseCategory;

/**
 * andy xu
 * 2016年11月8日
 */
public interface CaseCategoryService {
	
	/** 添加资源 */
	void addResources(CaseCategory caseCategory);
	
	/** 修改资源 
	 * @throws JyException */
	void modifyResources(CaseCategory caseCategory) throws JyException;
	
	/** 批量修改排序号 
	 * @throws JyException */
	void modifyResourcesOrders(Map<Integer, Integer> ordersMap) throws JyException;
	
	/** 查询资源 
	 * @throws JyException */
	List<JyCaseCategory> getResources(CaseCategory caseCategory) throws JyException;
	
	/** 删除资源 */
	void deleteResources(int resourceId);
	
	/** 根据ID查询资源 
	 * @throws JyException */
	CaseCategory getResourcesById(int resourceId) throws JyException;
}
