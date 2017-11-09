package com.alipp.weixin.strategy;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipp.weixin.constant.CommonConstant;
import com.alipp.weixin.constant.EventType;
import com.alipp.weixin.constant.MsgType;
import com.alipp.weixin.domain.TextMessageResp;
import com.alipp.weixin.util.JacksonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class MessageProcessFactory {
	
	@Autowired
	private TextMessage textMessage;
	@Autowired
	private SubscribeEventMessage subscribeEventMessage;
	@Autowired
	private UnsubscribeEventMessage unsubscribeEventMessage;
	@Autowired
	private ScanEventMessage scanEventMessage;
	@Autowired
	private TemplateSendJobFinishEventMessage templateSendJobFinishEventMessage;
	@Autowired
	private MassSendJobFinishEventMessage massSendJobFinishEventMessage;
	private Logger logger = CommonConstant.LOGGER;
	
	public String processMessage(MsgType msgType, Map<String, String> requestMap) {
		String result = null;
		String fromUserName = requestMap.get("FromUserName");
		String toUserName = requestMap.get("ToUserName");
		//事件推送
		if (MsgType.EVENT == msgType) {
			String event = requestMap.get("Event");
			EventMessage eventMessage = null;
			switch (event) {
				case EventType.SUBSCRIBE:
					//关注
					eventMessage  = (EventMessage) subscribeEventMessage;
					break;
				case EventType.UNSUBSCRIBE:
					//取消关注
					eventMessage = (EventMessage) unsubscribeEventMessage;
					break;
				case EventType.SCAN:
					//扫描二维码
					eventMessage = (EventMessage) scanEventMessage;
					break;
				case EventType.VIEW:
					System.out.println(requestMap.toString());
					break;
				case EventType.TEMPLATESENDJOBFINISH:
					//  处理模板短信任务完成推送
					eventMessage = (EventMessage) massSendJobFinishEventMessage;
					break;
				case EventType.MASSSENDJOBFINISH:
					//  处理模板短信任务完成推送
					eventMessage = (EventMessage) massSendJobFinishEventMessage;
					break;
				default:
					logger.info("收到其他事件消息: {}", requestMap.toString());
					break;
			}
			if (eventMessage != null) {
				result = eventMessage.processMessage(requestMap);
			}
		}else if (MsgType.TEXT == msgType) {
			//  处理模板短信任务完成推送
			result = textMessage.processMessage(requestMap);
		}else {
			//收到其他消息 
			logger.info("收到其他消息: {}", requestMap.toString());
			result = initTestResoXml(fromUserName, toUserName, "好的，好的，已收到!");
		}
		return result;
	}
	
	/**
	 * @param toUserName 接收者
	 * @param fromUserName 发送者
	 * @param respContent 发送内容
	 * @return xml
	 */
	public static String initTestResoXml(String toUserName, String fromUserName, String respContent) {
		TextMessageResp resp = new TextMessageResp();
		resp.setContent(respContent);
		resp.setMsgType(MsgType.TEXT.getTypeStr());
		resp.setToUserName(toUserName);
		resp.setFromUserName(fromUserName);
		resp.setCreateTime(System.currentTimeMillis() / 1000);
		String xml = null;
		try {
			xml = JacksonUtils.beanToXml(resp);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return xml;
	}
}
