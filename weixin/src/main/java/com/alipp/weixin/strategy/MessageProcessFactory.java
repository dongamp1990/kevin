package com.alipp.weixin.strategy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.inject.Named;

import com.alipp.weixin.constant.EventType;
import com.alipp.weixin.constant.MsgType;
import com.alipp.weixin.util.SpringUtil;

@Named
public class MessageProcessFactory {

	public static void processMessage(MsgType msgType, Map<String, String> requestMap, 
			OutputStream outputStream) {
		
		//收到所有推送，都返回空串，后面使用客服接口给微信用户发送消息
		response("", outputStream);
		
		//事件推送
		if (MsgType.EVENT == msgType) {
			
			String event = requestMap.get("Event");
			
			EventMessage eventMessage = null;
			
			switch (event) {
				case EventType.SUBSCRIBE:
					//关注
					eventMessage  = (EventMessage) SpringUtil.getBeanById("subscribeEventMessage");
					break;
				case EventType.UNSUBSCRIBE:
					//取消关注
					eventMessage = (EventMessage) SpringUtil.getBeanById("unsubscribeEventMessage");
					break;
				case EventType.SCAN:
					//扫描二维码
					eventMessage = (EventMessage) SpringUtil.getBeanById("scanEventMessage");
					break;
				case EventType.VIEW:
					System.out.println(requestMap.toString());
					break;
				case EventType.TEMPLATESENDJOBFINISH:
					//  处理模板短信任务完成推送
					eventMessage = (EventMessage) SpringUtil.getBeanById("templateSendJobFinishEventMessage");
					break;
				case EventType.MASSSENDJOBFINISH:
					//  处理模板短信任务完成推送
					eventMessage = (EventMessage) SpringUtil.getBeanById("massSendJobFinishEventMessage");
					break;
				default:
					System.out.println(requestMap.toString());
					break;
			}
			
			if (eventMessage != null) {
				eventMessage.processEventMessage(requestMap);
			}
			
		}else {
			//收到其他消息 
			System.out.println("收到其他消息" + requestMap.toString());
		}
	}
	
	/**
	 * 返回数据
	 * @param responseStr
	 * @param outputStream
	 */
	private static void response(String responseStr, OutputStream outputStream) {
		if (responseStr == null) {
			return;
		}
		
		try {
			outputStream.write(responseStr.getBytes("UTF-8"));
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	
}
