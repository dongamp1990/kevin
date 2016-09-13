package com.alipp.weixin.constant;


public enum MsgType {
	NEWS("news"), TEXT("text"), IMAGE("image"), VOICE("voice"), VIDEO("video"), MUSIC("music"), EVENT("event");
	
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
		
		throw new Exception("cant not found MsgType with " + type);
	}
}
