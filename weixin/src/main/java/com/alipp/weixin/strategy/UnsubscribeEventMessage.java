package com.alipp.weixin.strategy;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.alipp.weixin.constant.EventType;

@Component
public class UnsubscribeEventMessage extends EventMessage {

//	@Inject
//	private CustomerService customerService;
	@Override
	public String processMessage(Map<String, String> requestMap) {
		logger.info("UnsubscribeEventMessage:{}",requestMap);
//		String openId = requestMap.get("FromUserName");
//		try {
//			this.customerService.unSubscribeWeixin(openId);
//		} catch (ServiceException e) {
//			logger.error("processEventMessage", e.getErrorVO());
//		}
		return null;
	}

	@Override
	public String getEventType() {
		return EventType.UNSUBSCRIBE;
	}
}
