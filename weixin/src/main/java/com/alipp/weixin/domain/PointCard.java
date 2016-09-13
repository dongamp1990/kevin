package com.alipp.weixin.domain;

public class PointCard {
	private String enterpriseName;
	private Integer points;
	private Long enterpriseId;
	private Long enterpriseGroupId;
	private Double prePaidAmount;
	private String logoUrl;
	private Integer rewardPoints;

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
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

	public Double getPrePaidAmount() {
		return prePaidAmount;
	}

	public void setPrePaidAmount(Double prePaidAmount) {
		this.prePaidAmount = prePaidAmount;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public Integer getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
}
