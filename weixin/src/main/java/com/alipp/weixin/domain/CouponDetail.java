package com.alipp.weixin.domain;

import java.util.Date;

public class CouponDetail {
	private Long detailId;
	private Long customerId;
	private Long couponId;
	private Byte used;
	private Date createDate;
	private Date useDate;
	private String couponNo;

	private String customerName;
	private String phoneNumber;

	private String useDateStr;
	private String createDateStr;
	private Date expirationTime;
	private String expirationTimeStr;
	private String remark;
	private Integer type;
	private Coupon coupon;

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public Long getDetailId() {
		return detailId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setUsed(Byte used) {
		this.used = used;
	}

	public Byte getUsed() {
		return used;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public Date getUseDate() {
		return useDate;
	}

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUseDateStr() {
		return useDateStr;
	}

	public void setUseDateStr(String useDateStr) {
		this.useDateStr = useDateStr;
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getExpirationTimeStr() {
		return expirationTimeStr;
	}

	public void setExpirationTimeStr(String expirationTimeStr) {
		this.expirationTimeStr = expirationTimeStr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
