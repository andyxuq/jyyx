package com.jyyx.core.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 常量存放类
 * andy xu
 * 2016年11月2日
 * 
 */
public class Constants {

	public static final String PROJECT_NAME = "jyyx_project";
	
	public static final String USER_SESSION_KEY = "user_session";
	
	/** 文件上传根路径 */
	public static final String FILE_UPLOAD_PATH = "file.upload.path";
	
	/** 图片上传路径 */
	public static final String PIC_UPLOAD_PATH = "pic.upload.path";
	
	/** 上传图片类型 */
	public static final List<String> UPLOAD_PIC_TYPE = new ArrayList<String>();
	
	static {
		UPLOAD_PIC_TYPE.add("jpg");
		UPLOAD_PIC_TYPE.add("png");
		UPLOAD_PIC_TYPE.add("gif");
	}
}
