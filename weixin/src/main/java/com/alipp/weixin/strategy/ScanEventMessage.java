package com.alipp.weixin.strategy;

import java.util.Map;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipp.weixin.constant.EventType;

@Named
public class ScanEventMessage implements EventMessage {

//	@Inject
//	private CustomerService customerService;
//	
//	@Inject
//	private EnterpriseService enterpriseService;
	
	private static Logger logger = LoggerFactory.getLogger(ScanEventMessage.class);

	@Override
	public String processEventMessage(Map<String, String> requestMap) {
		String eventkey = requestMap.get("EventKey"); //参数二维码设定的id 
		String openId = requestMap.get("FromUserName");
		System.out.println("ScanEventMessage: " + requestMap.toString());
		
		try {
			String responseStr = null;
			String content = "欢迎关注。";
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
