package com.alipp.weixin.domain;

public class EnterpriseSetting {
	private Long settingId;
	private Long enterpriseId;
	private Boolean hasPrepay;
	private Boolean isSimplifiedReg;
	private Boolean dailyReportFlag;
	private Boolean remindMessageFlag;
	private Double customerDiscount;
	private String backgroundUrl;
	private String rewardDesc;
	private String workTimeDesc;
	private String logoUrl;
	private Boolean showCouponDialogFlag;
	private String waimaiPhoneNumber;
	private String waimaiTime;
	private Boolean redPacketFlag;
	
	public Long getSettingId() {
		return settingId;
	}

	public void setSettingId(Long settingId) {
		this.settingId = settingId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Boolean getIsSimplifiedReg() {
		return isSimplifiedReg;
	}

	public void setIsSimplifiedReg(Boolean isSimplifiedReg) {
		this.isSimplifiedReg = isSimplifiedReg;
	}

	public Boolean getDailyReportFlag() {
		return dailyReportFlag;
	}

	public void setDailyReportFlag(Boolean dailyReportFlag) {
		this.dailyReportFlag = dailyReportFlag;
	}

	public Boolean getRemindMessageFlag() {
		return remindMessageFlag;
	}

	public void setRemindMessageFlag(Boolean remindMessageFlag) {
		this.remindMessageFlag = remindMessageFlag;
	}

	public Double getCustomerDiscount() {
		return customerDiscount;
	}

	public void setCustomerDiscount(Double customerDiscount) {
		this.customerDiscount = customerDiscount;
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

	public String getWorkTimeDesc() {
		return workTimeDesc;
	}

	public void setWorkTimeDesc(String workTimeDesc) {
		this.workTimeDesc = workTimeDesc;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public Boolean getShowCouponDialogFlag() {
		return showCouponDialogFlag;
	}

	public void setShowCouponDialogFlag(Boolean showCouponDialogFlag) {
		this.showCouponDialogFlag = showCouponDialogFlag;
	}

	public String getWaimaiPhoneNumber() {
		return waimaiPhoneNumber;
	}

	public void setWaimaiPhoneNumber(String waimaiPhoneNumber) {
		this.waimaiPhoneNumber = waimaiPhoneNumber;
	}

	public Boolean getHasPrepay() {
		return hasPrepay;
	}

	public void setHasPrepay(Boolean hasPrepay) {
		this.hasPrepay = hasPrepay;
	}

	public String getWaimaiTime() {
		return waimaiTime;
	}

	public void setWaimaiTime(String waimaiTime) {
		this.waimaiTime = waimaiTime;
	}

	public Boolean getRedPacketFlag() {
		return redPacketFlag;
	}

	public void setRedPacketFlag(Boolean redPacketFlag) {
		this.redPacketFlag = redPacketFlag;
	}

}
