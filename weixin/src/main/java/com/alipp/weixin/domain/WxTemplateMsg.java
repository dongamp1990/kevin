package com.alipp.weixin.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WxTemplateMsg {

	private String touser;
	@JsonProperty("template_id")
	private String templateId;
	private String url;
	private String topcolor = "#FF0000";
	private Map<String, WxTemplateMsgValue> data;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	public Map<String, WxTemplateMsgValue> getData() {
		return data;
	}

	public void setData(Map<String, WxTemplateMsgValue> data) {
		this.data = data;
	}

}
