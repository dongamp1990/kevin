package com.alipp.weixin.ws.vo.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

@SuppressWarnings("deprecation")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonWriteNullProperties(false)
@XmlRootElement(name = "status")
public class StatusVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String message;
	private List<Error> errors;
	private ResponseDebugVO debug;


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

	@JsonProperty("errors")
	@XmlElementWrapper(name="errors")
	@XmlElements(value = { @XmlElement(name="error",type=Error.class)})
	public List<Error> getErrors() {
		if (errors == null) {
			errors = new ArrayList<Error>();
		}
		return errors;
	}
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
	@JsonProperty("debug")
	@XmlElement(name="debug")
	public ResponseDebugVO getDebug() {
		return debug;
	}
	public void setDebug(ResponseDebugVO debug) {
		this.debug = debug;
	}

}