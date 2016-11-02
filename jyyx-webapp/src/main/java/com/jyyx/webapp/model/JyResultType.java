package com.jyyx.webapp.model;

public enum JyResultType {

	SUCCESS(0, "SUCCESS")
	
	, FAIL(1, "FAIL")
	
	, ERROR_PARAMS(2, "error params");
	
	private int code;
	
	private String msg;
	
	private JyResultType(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
