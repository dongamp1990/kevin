package com.alipp.weixin.domain;

public class EnterpriseInfo {
	private String enterpriseName;
	private Long enterpriseId;
	private Integer points;
	private String phoneNumber;
	private String address;
	private Long customerId;
	private Integer exchangeCount;// 通兑分店数量
	private Long enterpriseGroupId;
	private String workTime; //工作时间
	private String backgroundUrl;//背景图
	private String rewardDesc; //兑换说明
	private String logoUrl;//logo图
	private String waimaiTime; //外卖时间
	
	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getExchangeCount() {
		return exchangeCount;
	}

	public void setExchangeCount(Integer exchangeCount) {
		this.exchangeCount = exchangeCount;
	}

	public Long getEnterpriseGroupId() {
		return enterpriseGroupId;
	}

	public void setEnterpriseGroupId(Long enterpriseGroupId) {
		this.enterpriseGroupId = enterpriseGroupId;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getBackgroundUrl() {
		return backgroundUrl;
	}

	public void setBackgroundUrl(String backgroundUrl) {
		this.backgroundUrl = backgroundUrl;
	}

	public String getRewardDesc() {
		return rewardDesc;
	}

	public void setRewardDesc(String rewardDesc) {
		this.rewardDesc = rewardDesc;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getWaimaiTime() {
		return waimaiTime;
	}

	public void setWaimaiTime(String waimaiTime) {
		this.waimaiTime = waimaiTime;
	}
}
