package com.jyyx.dao;

import java.util.List;

import com.jyyx.dao.mysql.entity.CaseCategory;

/**
 * 案例分类dao接口
 * andy xu
 * 2016年11月8日
 */
public interface CaseCategoryDao {

	/**
	 * 根据ID查找资源
	 * @param picId
	 * @return
	 */
	CaseCategory getResourcesById(int resourceId);
	
	/** 查询资源 */
	List<CaseCategory> getResources(CaseCategory caseCategory);
	
	/** 删除资源  */
	void deleteResources(int resourceId);
	
	/** 添加资源 */
	void addResources(CaseCategory caseCategory);
	
	/** 修改资源 */
	void modifyResources(CaseCategory caseCategory);
}
