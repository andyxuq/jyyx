package com.jyyx.webapp.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.jyyx.core.constant.Constants;
import com.jyyx.core.enums.PicCodeType;
import com.jyyx.core.exception.JyException;
import com.jyyx.dao.mysql.entity.Pic;
import com.jyyx.service.PicService;
import com.jyyx.service.utils.ConfigLoader;
import com.jyyx.webapp.model.JyResultType;
import com.jyyx.webapp.model.JyResultVo;
import com.jyyx.webapp.model.UploadFileVo;

/**
 * andy xu
 * 2016年11月3日
 */
@Controller
@RequestMapping("/api/pic")
public class PicController {

	private static Logger logger = LoggerFactory.getLogger(PicController.class);
	
	@Autowired
	private PicService picService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo addPicResources(Pic pic, HttpServletRequest request) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
//			String picCode = request.getParameter("picCode");
//			String referId = request.getParameter("referId");
//			PicCodeType picCodeType = PicCodeType.valueOf(picCode);
			
//			if (StringUtils.isBlank(referId)) {
//				referId = "0";
//			}
			int referId = pic.getReferId() == null ? 0 : pic.getReferId();
			List<UploadFileVo> fileList = getHttpRequestFiles(request, String.valueOf(referId));
			if (fileList.size() == 0) {
				throw new JyException("没有上传文件");
			}
			List<Pic> picList = new ArrayList<Pic>();
			for (UploadFileVo fileVo : fileList) {
				Pic uploadPic = new Pic();
				uploadPic.setPicCode(pic.getPicCode());
				uploadPic.setReferId(referId);
				uploadPic.setOrderCode(fileVo.getOrderCode());
				uploadPic.setPicLength(fileVo.getFileLength());
				uploadPic.setPicPath(fileVo.getFilePath());
				picList.add(uploadPic);
			}
			picService.addPicResources(picList);
			return result;
		} catch (Exception e) {
			logger.error("上传图片出错", e);
			return new JyResultVo(JyResultType.ERROR_PARAMS, e);
		}
	}
	
	@RequestMapping(value = "/modify/{picId}", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo addPicResources(@PathVariable int picId, HttpServletRequest request) {
		return null;
	}
	
	public static List<UploadFileVo> getHttpRequestFiles(HttpServletRequest request, String resourceId) 
			throws IllegalStateException, IOException, JyException {
		CommonsMultipartResolver fileUploadResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		Iterator<String> fileItems = multipartRequest.getFileNames();

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		List<UploadFileVo> fileList = new ArrayList<UploadFileVo>();
		while (fileItems.hasNext()) {
			String fieldName = fileItems.next();
			String orderCode = "0";
			
			if (fieldName.contains("_")) {
				String orderCodeField = fieldName.substring(fieldName.lastIndexOf("_"));
				String orderCodeValue = multipartRequest.getParameter("orderCode" + orderCodeField);
				
				if (StringUtils.isNotBlank(orderCodeValue)) {
					orderCode = orderCodeValue;
				}
			}
			
			MultipartFile multipartFile = multipartRequest.getFile(fieldName);
			if (null != multipartFile) {
				UploadFileVo fileVo = new UploadFileVo();
				
				String originFileName = multipartFile.getOriginalFilename();
				String originSubfix = originFileName.substring(originFileName.lastIndexOf(".") + 1);
				originSubfix = originSubfix.toLowerCase();
				
				if (!Constants.UPLOAD_PIC_TYPE.contains(originSubfix)) {
					throw new JyException("不支持的文件上传类型:" + originSubfix);
				}
				
				String rootPath = ConfigLoader.getConfig(Constants.FILE_UPLOAD_PATH);
				StringBuilder filePath = new StringBuilder(ConfigLoader.getConfig(Constants.PIC_UPLOAD_PATH));
				filePath.append("/");
				filePath.append(format.format(new Date()));
				filePath.append("/");
				filePath.append(resourceId);
				filePath.append("_");
				filePath.append(System.currentTimeMillis());
				filePath.append("." + originSubfix);
				
				File file = new File(rootPath + "/" + filePath.toString());
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				multipartFile.transferTo(file);
				
				fileVo.setFilePath(filePath.toString());
				fileVo.setFileLength((int)file.length());
				fileVo.setOrderCode(Integer.parseInt(orderCode));
				
				fileList.add(fileVo);
			}
		}
		return fileList;
	}
}