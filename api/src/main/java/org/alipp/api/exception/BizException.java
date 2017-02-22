package org.alipp.api.exception;

public class BizException extends RuntimeException {

	private static final long serialVersionUID = 3572514373727269890L;

	public BizException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BizException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BizException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BizException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
