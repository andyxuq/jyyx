package com.jyyx.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyyx.dao.PicDao;
import com.jyyx.dao.mysql.entity.Pic;
import com.jyyx.dao.utils.PageData;
import com.jyyx.dao.utils.PageInfo;
import com.jyyx.service.PicService;
import com.jyyx.service.utils.ConfigLoader;

/**
 * andy xu
 * 2016年11月5日
 */
@Service
public class PicServiceImpl implements PicService {

	@Autowired 
	private PicDao picDao;
	
	/* (non-Javadoc)
	 * @see com.jyyx.service.PicService#addPicResources(java.util.List)
	 */
	@Transactional(rollbackFor = {Exception.class})
	public void addPicResources(List<Pic> picList) {
		for (Pic pic : picList) {
			picDao.addResources(pic);
		}
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.PicService#modifyPicResources(com.jyyx.dao.mysql.entity.Pic)
	 */
	public void modifyPicResources(Pic pic) {
		picDao.modifyResources(pic);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.PicService#modifyPicOrders(java.util.Map)
	 */
	@Transactional(rollbackFor = {Exception.class})
	public void modifyPicOrders(Map<Integer, Integer> picOrders) {
		for (Map.Entry<Integer, Integer> entry : picOrders.entrySet()) {
			Pic pic = picDao.getResourcesById(entry.getKey());
			pic.setOrderCode(entry.getValue());
			
			picDao.modifyResources(pic);
		}
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.PicService#getPicResources(com.jyyx.dao.mysql.entity.Pic)
	 */
	public List<Pic> getPicResources(Pic pic) {
		return picDao.getResources(pic);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.PicService#getPicResourcesWithPage(com.jyyx.dao.mysql.entity.Pic, int, int)
	 */
	public PageData<Pic> getPicResourcesWithPage(Pic pic, int page, int pageRow) {
		int totalCount = picDao.getResourcesCount(pic);
		PageInfo pageInfo = new PageInfo(page, pageRow, totalCount);
		
		return picDao.getResourcesWithPage(pic, pageInfo);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.PicService#deletePicResources(int)
	 */
	public void deletePicResources(int picId) {
		Pic pic = picDao.getResourcesById(picId);
		String filePath = pic.getPicPath();
		File deleteFile = new File(ConfigLoader.getFileUploadPath() + filePath);
		FileUtils.deleteQuietly(deleteFile);
		
		picDao.deleteResources(picId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.PicService#getResourceById(int)
	 */
	public Pic getResourceById(int picId) {
		return picDao.getResourcesById(picId);
	}
}
