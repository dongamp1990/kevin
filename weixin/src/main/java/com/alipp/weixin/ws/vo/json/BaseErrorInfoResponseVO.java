package com.alipp.weixin.ws.vo.json;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *=====================================================================
 * ACP Restful Web Service Based Error Response Value Object 
 *
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "response")
public class BaseErrorInfoResponseVO  extends BaseRestResponseVO {

	/**
	 * Default constructor for Base Error Info Response.
	 */
	public BaseErrorInfoResponseVO() {
	}
	
	public BaseErrorInfoResponseVO(StatusVO responseStatus) {
		this.responseStatus = responseStatus;
	}

}