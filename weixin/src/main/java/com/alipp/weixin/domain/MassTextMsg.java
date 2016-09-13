package com.alipp.weixin.domain;

import java.util.List;
import java.util.Map;

import com.alipp.weixin.constant.MsgType;

public class MassTextMsg {
	private List<String> touser;
	private String msgtype = MsgType.TEXT.getTypeStr();
	private Map<String, Object> text;

	public List<String> getTouser() {
		return touser;
	}

	public void setTouser(List<String> touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public Map<String, Object> getText() {
		return text;
	}

	public void setText(Map<String, Object> text) {
		this.text = text;
	}

	protected void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

}
