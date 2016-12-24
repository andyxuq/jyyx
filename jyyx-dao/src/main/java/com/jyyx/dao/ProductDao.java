package com.jyyx.dao;

import java.util.List;

import org.springframework.beans.BeansException;

import com.jyyx.dao.model.JyProduct;
import com.jyyx.dao.model.ProductParam;
import com.jyyx.dao.utils.PageData;
import com.jyyx.dao.utils.PageInfo;

/**
 * 
 * andy xu
 * 2016年11月9日
 */
public interface ProductDao {

	/** 查询资源总数 */
	int getResourcesCount(ProductParam param);
	
	/**
	 * 根据ID查找资源
	 * @param resourceId
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException 
	 */
	JyProduct getResourcesById(int resourceId) throws BeansException, InstantiationException, IllegalAccessException;
	
	/** 查询资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	List<JyProduct> getResources(ProductParam param) throws BeansException, InstantiationException, IllegalAccessException;
	
	/** 分页查询资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	PageData<JyProduct> getResourcesWithPage(ProductParam param, PageInfo pageInfo) throws BeansException, InstantiationException, IllegalAccessException;
	
	/** 删除资源  */
	void deleteResources(int resourceId);
	
	/** 添加资源 */
	void addResources(ProductParam param);
	
	/** 修改资源 */
	void modifyResources(ProductParam param);
}
