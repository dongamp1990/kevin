package com.alipp.weixin.strategy;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.alipp.weixin.constant.MsgType;
import com.alipp.weixin.domain.TextMessageResp;
import com.alipp.weixin.util.JacksonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class TextMessage implements Message {

	@Override
	public String processMessage(Map<String, String> requestMap) {
		String openId = requestMap.get("FromUserName");
		TextMessageResp resp = new TextMessageResp();
		resp.setContent("收到：" + requestMap.get("Content"));
		resp.setMsgType(MsgType.TEXT.getTypeStr());
		resp.setToUserName(openId);
		resp.setFromUserName(requestMap.get("ToUserName"));
		resp.setCreateTime(System.currentTimeMillis() / 1000);
		String xml = null;
		try {
			xml = JacksonUtils.beanToXml(resp);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	@Override
	public String getMessageType() {
		return MsgType.TEXT.getTypeStr();
	}
}
