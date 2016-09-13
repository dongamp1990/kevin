package com.alipp.weixin.domain;

public class WxTemplateMsgValue {
	private String value;
	private String color = "#173177";

	
	public WxTemplateMsgValue() {
	}
	
	public WxTemplateMsgValue(String value, String color) {
		super();
		this.value = value;
		this.color = color;
	}

	public WxTemplateMsgValue(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
