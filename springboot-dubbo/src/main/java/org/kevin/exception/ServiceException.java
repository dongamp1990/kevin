package org.kevin.exception;

public class ServiceException extends BaseException {
	private static final long serialVersionUID = -8190787260758587475L;
	
	public ServiceException(String exceptionType, String errCd, String errMsg) {
		super(exceptionType, errCd, errMsg);
	}

}
