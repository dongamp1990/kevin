package com.alipp.weixin.strategy;

import java.util.Map;

public interface Message {
	public String getMessageType();
	
	/**
	 * @param requestMap
	 * @return
	 */
	public String processMessage(Map<String, String> requestMap);
}
