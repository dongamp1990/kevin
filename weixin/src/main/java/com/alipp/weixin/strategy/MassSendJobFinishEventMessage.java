package com.alipp.weixin.strategy;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alipp.weixin.constant.EventType;
import com.alipp.weixin.domain.WeixinMassMsgSendHistory;

@Component
public class MassSendJobFinishEventMessage extends EventMessage {

//	@Inject
//	private WeixinService weixinService;
    private static Logger logger = LoggerFactory.getLogger(MassSendJobFinishEventMessage.class);

	@Override
	public String processMessage(Map<String, String> requestMap) {
		logger.info("{}",requestMap);
		//报文格式
		//<xml>
//		<ToUserName><![CDATA[gh_3e8adccde292]]></ToUserName>
//		<FromUserName><![CDATA[oR5Gjjl_eiZoUpGozMo7dbBJ362A]]></FromUserName>
//		<CreateTime>1394524295</CreateTime>
//		<MsgType><![CDATA[event]]></MsgType>
//		<Event><![CDATA[MASSSENDJOBFINISH]]></Event>
//		<MsgID>1988</MsgID>
//		<Status><![CDATA[sendsuccess]]></Status>
//		<TotalCount>100</TotalCount>
//		<FilterCount>80</FilterCount>
//		<SentCount>75</SentCount>
//		<ErrorCount>5</ErrorCount>
//		</xml>
		
		// status 群发的结构，为“send success”或“send fail”或“err(num)”。但send success时，
		// 也有可能因用户拒收公众号的消息、系统错误等原因造成少量用户接收失败。err(num)是审核失败的具体原因，可能的情况如下：
		// err(10001), //涉嫌广告 err(20001), //涉嫌政治 err(20004), //涉嫌社会 err(20002), //涉嫌色情 err(20006), 
		//涉嫌违法犯罪 err(20008), //涉嫌欺诈 err(20013), //涉嫌版权 err(22000), //涉嫌互推(互相宣传) err(21000), //涉嫌其他 
		
		//没发送成功的，变更状态
			
		try {
			WeixinMassMsgSendHistory history = new WeixinMassMsgSendHistory();
			history.setMsgId(requestMap.get("MsgID") == null ? null : requestMap.get("MsgID"));
			history.setErrorCount(requestMap.get("ErrorCount") == null ? null : Integer.valueOf(requestMap.get("ErrorCount")));
			history.setTotalCount(requestMap.get("TotalCount") == null ? null : Integer.valueOf(requestMap.get("TotalCount")));
			history.setFilterCount(requestMap.get("FilterCount") == null ? null : Integer.valueOf(requestMap.get("FilterCount")));
			history.setSentCount(requestMap.get("SentCount") == null ? null : Integer.valueOf(requestMap.get("SentCount")));
			history.setStatus(requestMap.get("Status") == null ? null : requestMap.get("Status"));
//			this.weixinService.updateWeixinMassMsgSendHistory(history);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	@Override
	public String getEventType() {
		return EventType.TEMPLATESENDJOBFINISH;
	}

}
