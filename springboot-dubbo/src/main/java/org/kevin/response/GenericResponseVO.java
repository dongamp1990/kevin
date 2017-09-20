package org.kevin.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericResponseVO {
	private int status;
	private String message;
	@JsonProperty("result")
	private Object payload;
	
	public GenericResponseVO(Object payload) {
		super();
		this.status = 200;
		this.message = "ok";
		this.payload = payload;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}
}
