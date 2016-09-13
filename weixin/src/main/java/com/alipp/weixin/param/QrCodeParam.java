package com.alipp.weixin.param;

public class QrCodeParam {
	private Integer expire_seconds;//二维码有效时间，以秒为单位。 最大不超过604800（即7天
	private String action_name; //二维码类型
	private QrActionInfo action_info;//二维码详细信息

	public Integer getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(Integer expire_seconds) {
		this.expire_seconds = expire_seconds;
	}

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	public QrActionInfo getAction_info() {
		return action_info;
	}

	public void setAction_info(QrActionInfo action_info) {
		this.action_info = action_info;
	}

}
