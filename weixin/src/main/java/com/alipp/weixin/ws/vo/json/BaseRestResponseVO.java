package com.alipp.weixin.ws.vo.json;

/**
 *=====================================================================
 * ACP Restful Web Service Based Response Value Object 
 *
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;


@JsonPropertyOrder({"status"})
public abstract class BaseRestResponseVO {	

	protected StatusVO responseStatus;

	@JsonProperty("status")
	@XmlElement(name="status")
	public StatusVO getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(StatusVO responseStatus) {
		this.responseStatus = responseStatus;
	}

}