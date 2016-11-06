package com.jyyx.service;

import java.util.List;
import java.util.Map;

import com.jyyx.dao.mysql.entity.Pic;
import com.jyyx.dao.utils.PageData;

/**
 * andy xu
 * 2016年11月3日
 */
public interface PicService {

	/** 批量添加图片资源 */
	void addPicResources(List<Pic> picList);
	
	/** 修改图片资源 */
	void modifyPicResources(Pic pic);
	
	/** 批量修改图片排序号 */
	void modifyPicOrders(Map<Integer, Integer> picOrders);
	
	/** 查询图片资源 */
	List<Pic> getPicResources(Pic pic);
	
	/** 分页查询图片资源 */
	PageData<Pic> getPicResourcesWithPage(Pic pic, int page, int pageRow);
	
	/** 删除图片资源 */
	void deletePicResources(int picId);
	
	/** 根据ID查找资源 */
	Pic getResourceById(int picId);
}
