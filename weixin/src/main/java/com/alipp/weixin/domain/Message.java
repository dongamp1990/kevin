package com.alipp.weixin.domain;

import java.util.Date;

public class Message {

	private Long messageId;

	private Long customerId;

	private String customerName;

	private Long enterpriseId;

	private String phoneNumber;

	private String message;

	private Date sentTime;

	private String sentTimeStr;

	private Date scheduledTime;

	private boolean isSent;

	private String smsAgent;

	private Byte type;

	public String getSmsAgent() {
		return smsAgent;
	}

	public void setSmsAgent(String smsAgent) {
		this.smsAgent = smsAgent;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getSentTimeStr() {
		return sentTimeStr;
	}

	public void setSentTimeStr(String sentTimeStr) {
		this.sentTimeStr = sentTimeStr;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public Date getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public boolean isSent() {
		return isSent;
	}

	public void setSent(boolean isSent) {
		this.isSent = isSent;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

}
