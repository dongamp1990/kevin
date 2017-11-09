package com.alipp.weixin.constant;


public enum MsgType {
	NEWS("news"), TEXT("text"), IMAGE("image"), VOICE("voice"), VIDEO("video"), MUSIC("music"), EVENT("event"), LOCATION("location");
	
	private String type;
	
	private MsgType(String type) {
		this.type = type;
	}
	
	public String getTypeStr() {
		return type;
	}
	
	public static MsgType getMsgType(String type) throws Exception {
		for (MsgType msgType : MsgType.values()) {
			if (msgType.getTypeStr().equalsIgnoreCase(type)) {
				return msgType;
			}
		}
		CommonConstant.LOGGER.warn("cant not found MsgType with {}", type);
		throw new Exception("cant not found MsgType with " + type);
	}
}
