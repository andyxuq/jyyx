package com.jyyx.webapp.model;

/**
 * andy xu
 * 2016年11月3日
 */
public class UploadFileVo {

	private String filePath;
	
	private int fileLength;
	
	private int orderCode;

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the fileLength
	 */
	public int getFileLength() {
		return fileLength;
	}

	/**
	 * @param fileLength the fileLength to set
	 */
	public void setFileLength(int fileLength) {
		this.fileLength = fileLength;
	}

	/**
	 * @return the orderCode
	 */
	public int getOrderCode() {
		return orderCode;
	}

	/**
	 * @param orderCode the orderCode to set
	 */
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	
}
