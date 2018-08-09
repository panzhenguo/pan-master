package com.pan.exception;


import lombok.extern.slf4j.Slf4j;
/**
 * 也在中心自定义异常
* @Title: BcRunTimeException.java  
* @Package com.bjttsf.exception
* @ProjectName bc-common-core  
* @Description:   
* @Author ttsf-pzg  
* @Date 2018年7月18日下午4:38:06  
* @Version V1.0
 */
@Slf4j
public class BcRunTimeException extends RuntimeException {

	/**
	 * 异常码
	 */
	protected int code;

	private static final long serialVersionUID = 3160241586346324994L;

	public BcRunTimeException() {
	}

	public BcRunTimeException(Throwable cause) {
		super(cause);
	}

	public BcRunTimeException(String message) {
		super(message);
	}

	public BcRunTimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BcRunTimeException(int code, String message) {
		super(message);
		this.code = code;
	}

	public BcRunTimeException(int code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
