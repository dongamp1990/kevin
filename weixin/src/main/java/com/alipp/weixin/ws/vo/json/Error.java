package com.alipp.weixin.ws.vo.json;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

@SuppressWarnings("deprecation")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonWriteNullProperties(false)
@XmlRootElement(name = "error")
public class Error implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String message;
	private String trace;
	
	public Error() {
	}	

	public Error(String errorCode, String errorMessage) {
		this.code = errorCode;
		this.message = errorMessage;
	}

	public Error(String errorCode, String errorMessage, String errorTrace) {
		this.code = errorCode;
		this.message = errorMessage;
		this.trace = errorTrace;
	}

	@JsonProperty("code")
	@XmlElement(name="code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("message")
	@XmlElement(name="message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("trace")
	@XmlElement(name="trace")
	public String getTrace() {
		return trace;
	}
	public void setTrace(String trace) {
		this.trace = trace;
	}

}