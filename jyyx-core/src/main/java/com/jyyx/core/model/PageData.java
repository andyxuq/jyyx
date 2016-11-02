package com.jyyx.core.model;

import java.util.List;

/**
 * 分页数据包装类
 * andy xu
 * 2016年11月2日
 */
public class PageData<T> {

	/** 总页数 */
	private int total;
	
	/** 每页显示条数 */
	private int pageRow;
	
	/** 被分页数据 */
	private List<T> pageData;

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the pageRow
	 */
	public int getPageRow() {
		return pageRow;
	}

	/**
	 * @param pageRow the pageRow to set
	 */
	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}

	/**
	 * @return the pageData
	 */
	public List<T> getPageData() {
		return pageData;
	}

	/**
	 * @param pageData the pageData to set
	 */
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
}
