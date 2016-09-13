package com.alipp.weixin.param;

public class QrScene {

	private Long scene_id; // 目前参数只支持1--100000
	private String scene_str; // 字符串类型，长度限制为1到64

	public Long getScene_id() {
		return scene_id;
	}

	public void setScene_id(Long scene_id) {
		this.scene_id = scene_id;
	}

	public String getScene_str() {
		return scene_str;
	}

	public void setScene_str(String scene_str) {
		this.scene_str = scene_str;
	}

}
