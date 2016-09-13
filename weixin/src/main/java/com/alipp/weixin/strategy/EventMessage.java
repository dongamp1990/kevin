package com.alipp.weixin.strategy;

import java.util.Map;

public interface EventMessage {
	
	public String getEventType();
	
	/**
	 * 
	 * @param requestMap
	 * @return
	 */
	public String processEventMessage(Map<String, String> requestMap);
}
