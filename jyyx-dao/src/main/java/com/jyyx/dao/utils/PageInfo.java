package com.jyyx.dao.utils;

/**
 * 保存分页信息
 * andy xu
 * 2016年11月5日
 */
public class PageInfo {
	
	/** 当前第几页 */
	private int page;
	
	/** 每页显示条数 */
	private int pageRow;
	
	/** 总条数 */
	private int totalCount;
	
	/** 总页数 */
	private int pages;

	public PageInfo(int page, int pageRow, int totalCount) {
		if (page < 1) {
			page = 1;
		}
		if (pageRow < 10) { //每页最少10行
			pageRow = 10;
		}
		
		this.page = page;
		this.pageRow = pageRow;
		this.totalCount = totalCount;
		this.pages = (totalCount%pageRow == 0 ? totalCount / pageRow : (totalCount / pageRow + 1));
	}
	
	public int getStartRow() {
		if (page > pages) {
			page = pages;
		}
		
		return (page - 1) * pageRow;
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
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the pages
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}
}
