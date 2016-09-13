package com.alipp.weixin.ws.vo;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.alipp.weixin.ws.vo.json.BaseRestResponseVO;

@JsonPropertyOrder({ "status", "code", "build", "serverName", "duration",
"result" })
public class GenericResponseVO  extends BaseRestResponseVO{

	private Object payload;
	
	public GenericResponseVO(){
		
	}

	public GenericResponseVO(Object payload) {
		super();
		this.payload = payload;
	}

	@JsonProperty("result")
	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	
	
}
