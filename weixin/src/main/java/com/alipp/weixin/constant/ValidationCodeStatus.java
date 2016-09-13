package com.alipp.weixin.constant;

public enum ValidationCodeStatus {
	NOT_FOUND(0, "未找到验证码"),
	SUCCESS(1, "验证码正确"),
	FAIL(-1, "验证码错误"),
	EXPIRATION(-2, "验证码过期");
	
	
	private Integer value;
	private String msg;
	
	private ValidationCodeStatus(Integer value, String msg) {
		this.value = value;
		this.msg = msg;
	}
 
	public String getMsg() {
		return msg;
	}
	
	public Integer getValue() {
		return value;
	}
	
}
