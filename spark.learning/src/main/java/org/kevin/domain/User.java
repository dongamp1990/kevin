package org.kevin.domain;

public class User {
	String userId;
	String gender;
	Integer age;
	String registerDate;
	String role;
	String region;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}
