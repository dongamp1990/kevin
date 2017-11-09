package com.alipp.weixin.strategy;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.alipp.weixin.constant.EventType;

@Component
public class ScanEventMessage extends EventMessage {

//	@Inject
//	private CustomerService customerService;
//	
//	@Inject
//	private EnterpriseService enterpriseService;
	
	@Override
	public String processMessage(Map<String, String> requestMap) {
//		String eventkey = requestMap.get("EventKey"); //参数二维码设定的id 
//		String openId = requestMap.get("FromUserName");
		logger.info("ScanEventMessage: {}", requestMap.toString());
		try {
			String responseStr = null;
			return responseStr;
		} catch (Exception e) {
			logger.error("ScanEventMessage.processEventMessage", e);
			return null;
		}
	}

	@Override
	public String getEventType() {
		return EventType.SCAN;
	}

}
