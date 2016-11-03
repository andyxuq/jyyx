package com.jyyx.core.enums;

/**
 * 图片code类型
 * andy xu
 * 2016年11月3日
 */
public enum PicCodeType {

	HOME_HEADER("首页大图")
	, DECORATE_HEADER("装修大图")
	, DECORATE_SMALL("装修小图")
	, PRODUCT_HEADER("产品LOGO图")
	, NEWS_HEADER("资讯大图");
	
	private String desc;
	
	private PicCodeType(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
