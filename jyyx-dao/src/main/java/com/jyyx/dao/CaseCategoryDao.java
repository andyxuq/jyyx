package com.jyyx.dao;

import java.util.List;

import org.springframework.beans.BeansException;

import com.jyyx.dao.model.JyCaseCategory;
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
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException 
	 */
	JyCaseCategory getResourcesById(int resourceId) throws BeansException, InstantiationException, IllegalAccessException;
	
	/** 查询资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	List<JyCaseCategory> getResources(CaseCategory caseCategory) throws BeansException, InstantiationException, IllegalAccessException;
	
	/** 删除资源  */
	void deleteResources(int resourceId);
	
	/** 添加资源 */
	void addResources(CaseCategory caseCategory);
	
	/** 修改资源 */
	void modifyResources(CaseCategory caseCategory);
}
