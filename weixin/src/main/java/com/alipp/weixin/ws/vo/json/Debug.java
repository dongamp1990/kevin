package com.alipp.weixin.ws.vo.json;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonProperty;

public class Debug implements Serializable {

	private static final long serialVersionUID = 1L;

	private String server;
	private String duration;
	private String trace;

	@JsonProperty("server")
	@XmlElement(name="server")
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}

	@JsonProperty("duration")
	@XmlElement(name="duration")
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
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