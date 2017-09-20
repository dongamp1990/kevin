package org.kevin.exception;

public class DaoException extends BaseException {
	private static final long serialVersionUID = 5404733870677069564L;

	/**
	 * Constructor
	 * 
	 * @param exceptionType
	 * @param errvo
	 */
	public DaoException(String exceptionType, String errCd, String errMsg) {
		super(exceptionType, errCd, errMsg);
	}
	
	public DaoException() {
	}

}
