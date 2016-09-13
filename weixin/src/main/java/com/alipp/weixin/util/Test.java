package com.alipp.weixin.util;

import org.codehaus.jackson.annotate.JsonProperty;

public class Test {
	@JsonProperty("AppId")
	private String appId;
	@JsonProperty("CreateTime")
	private String createTime;
	@JsonProperty("InfoType")
	private String infoType;
	@JsonProperty("AuthorizerAppid")
	private String authorizerAppid;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getAuthorizerAppid() {
		return authorizerAppid;
	}

	public void setAuthorizerAppid(String authorizerAppid) {
		this.authorizerAppid = authorizerAppid;
	}

}
