package com.alipp.weixin.strategy;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipp.weixin.domain.WeixinInfo;
import com.alipp.weixin.domain.WeixinTemplateMsg;
import com.alipp.weixin.domain.WxTemplateMsg;
import com.alipp.weixin.domain.WxTemplateMsgValue;
import com.alipp.weixin.util.JSONUtil;
import com.alipp.weixin.util.WeixinUtil;
import com.alipp.weixin.ws.request.SendWxTemplateMsgRequest;

public class WxTemplateMsgProcess {

	private static Logger logger = LoggerFactory.getLogger(WxTemplateMsgProcess.class);
	
	public static Map<String, Object> processMessage(SendWxTemplateMsgRequest param, WeixinInfo weixinInfo,
			WeixinTemplateMsg weixinTemplateMsg) throws Exception {
		
		try {
			@SuppressWarnings("unchecked")
			Map<String, String> contentMap = JSONUtil.jsonToObject(weixinTemplateMsg.getTemplateContent(), Map.class);
			Map<String, WxTemplateMsgValue> sendParam = new HashMap<String, WxTemplateMsgValue>();
			
			//循环微信模板消息配置的key
			for (String key : contentMap.keySet()) {
				String contentValue = contentMap.get(key); 
				
				//循环传入参数key
				for (String paramKey : param.getParamData().keySet()) {
					String containsVal = "{" + paramKey + "}";
					
					//如果微信模板消息配置value 包含 参数key 就替换为参数value
					if (contentValue.contains(containsVal)) {
						String paramVal = param.getParamData().get(paramKey);
						if (paramVal != null) {
							contentValue = contentValue.replace(containsVal, param.getParamData().get(paramKey));
						}
					}
				}
				
				sendParam.put(key, new WxTemplateMsgValue(contentValue));
			}
			
			WxTemplateMsg msg = new WxTemplateMsg();
			msg.setTemplateId(weixinTemplateMsg.getWxTemplateId());
			msg.setTouser(weixinInfo.getOpenId());
			msg.setData(sendParam);
			msg.setUrl(weixinTemplateMsg.getUrl());
			Map<String, Object> map = WeixinUtil.sendTemplateMsg(msg);
			
			if (map != null) {
				map.put("requestContent", msg);
			}
			
			return map;
		} catch (Exception e) {
			logger.error("WxTemplateMsgProcess.processMessage Error.",e);
			return null;
		}
	}

}
