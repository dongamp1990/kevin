package com.alipp.weixin.domain;

import java.util.Date;

public class Coupon {
	private Long couponId;
	private String couponPrefix;
	private Long enterpriseId;
	private Long enterpriseGroupId;
	private String couponName;
	private Double couponAmt;
	private Date expirationTime;
	private Byte available;
	private Double minBuyPrice;
	private Byte couponType;
	private Byte deleted;
	private String description;
	private Date createDate;
	private Long useCount; // 使用数
	private Long totalCount; // 总数
	private Byte expirationType; //"1"指固定过期时间;"2"指动态过期时间,指定间隔
	private String expirationTimeStr;
	private int expirationInterval;
	private String createDateStr;
	private String rule;
	private String enterpriseName;
	
	public Byte getExpirationType() {
		return expirationType;
	}

	public void setExpirationType(Byte expirationType) {
		this.expirationType = expirationType;
	}

	public int getExpirationInterval() {
		return expirationInterval;
	}

	public void setExpirationInterval(int expirationInterval) {
		this.expirationInterval = expirationInterval;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseGroupId(Long enterpriseGroupId) {
		this.enterpriseGroupId = enterpriseGroupId;
	}

	public Long getEnterpriseGroupId() {
		return enterpriseGroupId;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponAmt(Double couponAmt) {
		this.couponAmt = couponAmt;
	}

	public Double getCouponAmt() {
		return couponAmt;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setAvailable(Byte available) {
		this.available = available;
	}

	public Byte getAvailable() {
		return available;
	}

	public void setMinBuyPrice(Double minBuyPrice) {
		this.minBuyPrice = minBuyPrice;
	}

	public Double getMinBuyPrice() {
		return minBuyPrice;
	}

	public void setCouponType(Byte couponType) {
		this.couponType = couponType;
	}

	public Byte getCouponType() {
		return couponType;
	}

	public void setDeleted(Byte deleted) {
		this.deleted = deleted;
	}

	public Byte getDeleted() {
		return deleted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCouponPrefix() {
		return couponPrefix;
	}

	public void setCouponPrefix(String couponPrefix) {
		this.couponPrefix = couponPrefix;
	}

	public Long getUseCount() {
		return useCount;
	}

	public void setUseCount(Long useCount) {
		this.useCount = useCount;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public String getExpirationTimeStr() {
		return expirationTimeStr;
	}

	public void setExpirationTimeStr(String expirationTimeStr) {
		this.expirationTimeStr = expirationTimeStr;
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
}
