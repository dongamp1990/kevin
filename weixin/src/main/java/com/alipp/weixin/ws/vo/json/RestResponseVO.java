package com.alipp.weixin.ws.vo.json;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;


public class RestResponseVO extends BaseRestResponseVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private Object result;
	
	@JsonProperty("result")
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	

	

}
