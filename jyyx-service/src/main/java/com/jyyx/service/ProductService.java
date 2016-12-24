package com.jyyx.service;

import java.util.List;

import org.springframework.beans.BeansException;

import com.jyyx.dao.model.JyProduct;
import com.jyyx.dao.model.ProductParam;
import com.jyyx.dao.mysql.entity.Msg;
import com.jyyx.dao.utils.PageData;

/**
 * andy xu
 * 2016年11月12日
 */
public interface ProductService {

	/** 添加资源 */
	void addResource(ProductParam productParam);
	
	/** 修改资源 */
	void modifyResources(ProductParam productParam);
	
	/** 查询资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	List<JyProduct> getResources(ProductParam productParam) throws BeansException, InstantiationException, IllegalAccessException;
	
	/** 分页查询资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	PageData<JyProduct> getResourcesWithPage(ProductParam productParam, int page, int pageRow) throws BeansException, InstantiationException, IllegalAccessException;
	
	/** 删除资源 */
	void deleteResources(int resourceId);
	
	/** 根据ID查找资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	JyProduct getResourceById(int resourceId) throws BeansException, InstantiationException, IllegalAccessException;

}
