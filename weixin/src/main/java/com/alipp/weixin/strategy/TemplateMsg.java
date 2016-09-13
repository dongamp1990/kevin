package com.alipp.weixin.strategy;

import java.util.Map;

import com.alipp.weixin.domain.WeixinInfo;
import com.alipp.weixin.domain.WeixinTemplateMsg;

public interface TemplateMsg {

	public Map<String, Object> sendTemplateMsg(Integer msgType,
			Map<String, String> paramMap, WeixinInfo weixinInfo,
			WeixinTemplateMsg weixinTemplateMsg);

}