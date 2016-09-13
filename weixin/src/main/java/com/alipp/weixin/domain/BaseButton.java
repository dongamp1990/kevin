package com.alipp.weixin.domain;

import java.util.List;

public abstract class BaseButton {

	private String name;
	private String type;
	
	private List<BaseButton> sub_button;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<BaseButton> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<BaseButton> sub_button) {
		this.sub_button = sub_button;
	}
	
	
}
