package com.jyyx.dao.model;

import java.util.ArrayList;
import java.util.List;

import com.jyyx.dao.mysql.entity.ProductCategory;

/**
 * 前端展示的productCategory
 * andy xu
 * 2016年11月9日
 */
public class JyProductCategory extends ProductCategory {

	/** 子分类列表 */
	private List<JyProductCategory> childList = new ArrayList<JyProductCategory>();

	/**
	 * @return the childList
	 */
	public List<JyProductCategory> getChildList() {
		return childList;
	}

	/**
	 * @param childList the childList to set
	 */
	public void setChildList(List<JyProductCategory> childList) {
		this.childList = childList;
	}
}
