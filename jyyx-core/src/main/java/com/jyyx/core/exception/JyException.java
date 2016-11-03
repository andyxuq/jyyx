package com.jyyx.core.exception;

/**
 * 自定义异常
 * andy xu
 * 2016年11月3日
 */
public class JyException extends Exception {

	public JyException(String msg) {
		super(msg);
	}
	
	public JyException(String msg, Throwable e) {
		super(msg + "[" + e.getMessage() + "]", e);
	}
	
	public JyException(Throwable e) {
		super(e.getMessage(), e);
	}
}
