package com.jyyx.service;

import java.util.List;

import org.springframework.beans.BeansException;

import com.jyyx.dao.model.CaseParam;
import com.jyyx.dao.model.JyCase;
import com.jyyx.dao.model.JyProduct;
import com.jyyx.dao.model.ProductParam;
import com.jyyx.dao.mysql.entity.Msg;
import com.jyyx.dao.utils.PageData;

/**
 * andy xu
 * 2016年11月12日
 */
public interface CaseService {

	/** 添加资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	void addResource(CaseParam caseParam) throws BeansException, InstantiationException, IllegalAccessException;
	
	/** 修改资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	void modifyResources(CaseParam caseParam) throws BeansException, InstantiationException, IllegalAccessException;
	
	/** 查询资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	List<JyCase> getResources(CaseParam caseParam) throws BeansException, InstantiationException, IllegalAccessException;
	
	/** 分页查询资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	PageData<JyCase> getResourcesWithPage(CaseParam caseParam, int page, int pageRow) throws BeansException, InstantiationException, IllegalAccessException;
	
	/** 删除资源 */
	void deleteResources(int resourceId);
	
	/** 根据ID查找资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	JyCase getResourceById(int resourceId) throws BeansException, InstantiationException, IllegalAccessException;

}
