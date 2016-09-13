package com.alipp.weixin.strategy;

import java.util.Map;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipp.weixin.constant.EventType;

@Named
public class TemplateSendJobFinishEventMessage implements EventMessage {

	private static Logger logger = LoggerFactory.getLogger(TemplateSendJobFinishEventMessage.class);
//	@Inject
//	private WeixinService weixinService;
	
	@Override
	public String processEventMessage(Map<String, String> requestMap) {
		logger.info("{}",requestMap);
		//报文格式{ToUserName=gh_8539b6707b8a, FromUserName=o6n9fwotYMwrc9xscbfdS0_SLTvs, CreateTime=1441771471, MsgType=event, Event=TEMPLATESENDJOBFINISH, MsgID=209335564, Status=success}
		// Status=success || failed: system failed || failed:user block
		String statusStr = requestMap.get("Status");
		
		//没发送成功的，变更状态
		if (!"success".equals(statusStr)) {
			
			Byte status = 3;
			
			if (statusStr.contains("user")) {
				status = 2;
			}
			
//			try {
//				this.weixinService.updateWeixinTemplateMsgSendHistoryStatus(
//						Integer.valueOf(requestMap.get("MsgID")), status);
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
		}
		
		return null;
	}
	
	@Override
	public String getEventType() {
		return EventType.TEMPLATESENDJOBFINISH;
	}

}
