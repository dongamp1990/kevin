package com.alipp.weixin.strategy;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.alipp.weixin.constant.EventType;
import com.alipp.weixin.util.CustomMsgUtil;
import com.alipp.weixin.util.StringUtil;

@Component
public class SubscribeEventMessage extends EventMessage {

//	@Inject
//	private CustomerService customerService;
//	
//	@Inject
//	private EnterpriseService enterpriseService;
	
	@Override
	public String processMessage(Map<String, String> requestMap) {
		String eventkey = requestMap.get("EventKey");
		String openId = requestMap.get("FromUserName");
		logger.info("SubscribeEventMessage: {}", requestMap.toString());
		try {
			String content = "欢迎关注。";
			if (StringUtil.isBlank(eventkey)) {
				//普通关注
				CustomMsgUtil.sendTextMsg(openId, content);
				return null;
			}
//			//扫带参数二维码
//			//未关注并扫描了带参数的二维码 关注
//			if (eventkey.startsWith("qrscene_")) {
//				//去掉qrscene_ 得到customerGroupId 获取到商家和 消费者的信息 更新微信id，加入一个二维码扫描码 记录
//				Long relationId = Long.valueOf(eventkey.replace("qrscene_", ""));
//				//查询关系id
//				// 找到表示 可用。进行绑定
//			}
			return MessageProcessFactory.initTestResoXml(openId, requestMap.get("ToUserName"), "欢迎关注");	
		} catch (Exception e) {
			logger.error("SubscribeEventMessage.processEventMessage", e);
			return null;
		}
	}

	@Override
	public String getEventType() {
		return EventType.SUBSCRIBE;
	}

}
