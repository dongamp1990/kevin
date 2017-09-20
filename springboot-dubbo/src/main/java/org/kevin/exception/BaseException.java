package org.kevin.exception;

/**
 *=====================================================================
 * ACP Base Exception
 *
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */

import java.io.Serializable;

/**
 * 
 */
public class BaseException extends Exception implements Serializable {
	private static final long serialVersionUID = 3743203851771467234L;

	public BaseException(String exceptionType, String errCd, String errMsg) {
		super();
		this.exceptionType = exceptionType;
		this.errCd = errCd;
		this.errMsg = errMsg;
	}

	public BaseException() {
	}
	
	private String exceptionType;
	private String errCd;
	private String errMsg;

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getErrCd() {
		return errCd;
	}

	public void setErrCd(String errCd) {
		this.errCd = errCd;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
