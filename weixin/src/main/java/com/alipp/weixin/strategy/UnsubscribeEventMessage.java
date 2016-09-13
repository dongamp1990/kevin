package com.alipp.weixin.strategy;

import java.util.Map;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipp.weixin.constant.EventType;

@Named
public class UnsubscribeEventMessage implements EventMessage {

//	@Inject
//	private CustomerService customerService;
	private static Logger logger = LoggerFactory.getLogger(UnsubscribeEventMessage.class);

	@Override
	public String processEventMessage(Map<String, String> requestMap) {
		logger.info("{}",requestMap);
		String openId = requestMap.get("FromUserName");

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
