package com.jyyx.dao;

import java.util.List;

import org.springframework.beans.BeansException;

import com.jyyx.dao.model.CaseParam;
import com.jyyx.dao.model.JyCase;
import com.jyyx.dao.utils.PageData;
import com.jyyx.dao.utils.PageInfo;

/**
 * 
 * andy xu
 * 2016年11月9日
 */
public interface CaseDao {

	/** 查询资源总数 */
	int getResourcesCount(CaseParam param);
	
	/**
	 * 根据ID查找资源
	 * @param resourceId
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException 
	 */
	JyCase getResourcesById(int resourceId) throws BeansException, InstantiationException, IllegalAccessException;
	
	/** 查询资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	List<JyCase> getResources(CaseParam param) throws BeansException, InstantiationException, IllegalAccessException;
	
	/** 分页查询资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	PageData<JyCase> getResourcesWithPage(CaseParam param, PageInfo pageInfo) throws BeansException, InstantiationException, IllegalAccessException;
	
	/** 删除资源  */
	void deleteResources(int resourceId);
	
	/** 添加资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	void addResources(CaseParam param) throws BeansException, InstantiationException, IllegalAccessException;
	
	/** 修改资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	void modifyResources(CaseParam param) throws BeansException, InstantiationException, IllegalAccessException;
}
