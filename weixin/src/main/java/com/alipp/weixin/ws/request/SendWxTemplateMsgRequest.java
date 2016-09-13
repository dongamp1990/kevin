package com.alipp.weixin.ws.request;

import java.io.IOException;
import java.util.Map;

import com.alipp.weixin.util.JSONUtil;

public class SendWxTemplateMsgRequest {
	private Long customerId;
	private Long templateId;
	private Long enterpriseId;
	private Map<String, String> paramData;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Map<String, String> getParamData() {
		return paramData;
	}

	public void setParamData(Map<String, String> paramData) {
		this.paramData = paramData;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public SendWxTemplateMsgRequest valueOf(String requestStr) throws IOException {
		return JSONUtil.jsonToObject(requestStr, SendWxTemplateMsgRequest.class);
	}

}
