package com.jyyx.webapp.model;

public class JyResultVo {

	private int code;
	
	private String msg;
	
	private Object data;

	public JyResultVo(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public JyResultVo(int code, String msg, Object data) {
		this(code, msg);
		this.data = data;
	}
	
	public JyResultVo(JyResultType result) {
		this(result.getCode(), result.getMsg());
	}
	
	public JyResultVo(JyResultType result, Exception e) {
		this(result.getCode(), result.getMsg() + ":" + e.getMessage());
	}
	
	public JyResultVo(JyResultType result, Object data) {
		this(result.getCode(), result.getMsg(), data);
	}
	
	public JyResultVo(JyResultType result, Object data, Exception e) {
		this(result, e);
	}
	
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
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
