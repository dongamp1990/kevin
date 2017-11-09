package com.alipp.weixin.strategy;

import org.slf4j.Logger;

import com.alipp.weixin.constant.CommonConstant;
import com.alipp.weixin.constant.MsgType;

public abstract class EventMessage implements Message {
	protected Logger logger = CommonConstant.LOGGER;
	
	public abstract String getEventType();
	
	@Override
	public String getMessageType() {
		return MsgType.EVENT.getTypeStr();
	}
}
