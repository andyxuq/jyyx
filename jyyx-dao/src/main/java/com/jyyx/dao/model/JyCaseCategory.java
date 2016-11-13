package com.jyyx.dao.model;

import java.util.ArrayList;
import java.util.List;

import com.jyyx.dao.mysql.entity.CaseCategory;

/**
 * 前端展示的caseCategory
 * andy xu
 * 2016年11月9日
 */
public class JyCaseCategory extends CaseCategory {

	private List<JyCaseCategory> childList = new ArrayList<JyCaseCategory>();

	/**
	 * @return the childList
	 */
	public List<JyCaseCategory> getChildList() {
		return childList;
	}

	/**
	 * @param childList the childList to set
	 */
	public void setChildList(List<JyCaseCategory> childList) {
		this.childList = childList;
	}
	
}
