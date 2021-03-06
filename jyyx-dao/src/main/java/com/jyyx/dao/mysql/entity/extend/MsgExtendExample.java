package com.jyyx.dao.mysql.entity.extend;

import com.jyyx.dao.mysql.entity.MsgExample;

/**
 * msg DAO对象增加分页信息
 * andy xu
 * 2016年11月7日
 */
public class MsgExtendExample extends MsgExample {

	/** 开始行数 */
	private int startRow;
	
	/** 每页显示行数 */
	private int pageRow;

	/**
	 * @return the startRow
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * @param startRow the startRow to set
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
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
	
}
