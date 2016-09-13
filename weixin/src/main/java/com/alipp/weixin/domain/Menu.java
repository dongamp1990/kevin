package com.alipp.weixin.domain;

import java.util.List;

//微信菜单按钮
public class Menu {
	
	private List<BaseButton> button;

	public List<BaseButton> getButton() {
		return button;
	}

	public void setButton(List<BaseButton> button) {
		this.button = button;
	}
}
