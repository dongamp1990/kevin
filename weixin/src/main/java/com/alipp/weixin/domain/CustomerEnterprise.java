package com.alipp.weixin.domain;

import java.util.Date;

/**
 * 
 * customer_enterprise 表和 customer_enterprise_group
 * 
 */
public class CustomerEnterprise {

	private Long customerId;
	private Long enterpriseId;
	private Long enterpriseGroupId;
	private Integer points;
	private Date createdDate;
	private Integer createdBy;
	private Integer updateBy;
	private Date updatedDate;
	private Double prePaidAmount;
	private String phoneNumber;
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getEnterpriseGroupId() {
		return enterpriseGroupId;
	}

	public void setEnterpriseGroupId(Long enterpriseGroupId) {
		this.enterpriseGroupId = enterpriseGroupId;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Double getPrePaidAmount() {
		return prePaidAmount;
	}

	public void setPrePaidAmount(Double prePaidAmount) {
		this.prePaidAmount = prePaidAmount;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
