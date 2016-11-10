package com.jyyx.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jyyx.core.enums.PicCodeType;
import com.jyyx.dao.mysql.entity.Pic;
import com.jyyx.dao.utils.PageData;
import com.jyyx.dao.utils.PageInfo;

/**
 * andy xu
 * 2016年11月5日
 */
public interface PicDao {

	/** 查询资源总数 */
	int getResourcesCount(Pic pic);
	
	/**
	 * 根据ID查找资源
	 * @param picId
	 * @return
	 */
	Pic getResourcesById(int picId);
	
	/** 查询资源 */
	List<Pic> getResources(Pic pic);
	
	/** 分页查询资源 */
	PageData<Pic> getResourcesWithPage(Pic pic, PageInfo pageInfo);
	
	/** 删除资源  */
	void deleteResources(int picId);
	
	/** 添加资源 */
	void addResources(Pic pic);
	
	/** 修改资源 */
	void modifyResources(Pic pic);
	
	/** 根据ID，code查找图片资源 */
	Map<Integer, List<Pic>> getResourceByCode(PicCodeType codeType, Set<Integer> referIdList);
}
