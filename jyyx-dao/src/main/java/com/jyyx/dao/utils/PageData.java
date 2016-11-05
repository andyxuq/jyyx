package com.jyyx.dao.utils;

import java.util.List;

/**
 * 分页数据包装类
 * andy xu
 * 2016年11月2日
 */
public class PageData<T> {

	/** 总页数 */
	private int total;
	
	/** 当前页数 */
	private int page;
	
	/** 每页显示条数 */
	private int pageRow;
	
	/** 被分页数据 */
	private List<T> pageData;

	public PageData(PageInfo pageInfo) {
		this.total = pageInfo.getPages();
		this.page = pageInfo.getPage();
		this.pageRow = pageInfo.getPageRow();
	}
	
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

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
}
