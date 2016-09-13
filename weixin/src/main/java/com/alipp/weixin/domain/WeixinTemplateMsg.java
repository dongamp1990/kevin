package com.alipp.weixin.domain;

public class WeixinTemplateMsg {
	private Integer templateId;
	private String name;
	private String description;
	private String wxTemplateId;
	private String templateContent;
	private String url;

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWxTemplateId() {
		return wxTemplateId;
	}

	public void setWxTemplateId(String wxTemplateId) {
		this.wxTemplateId = wxTemplateId;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
