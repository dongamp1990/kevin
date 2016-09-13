package com.alipp.weixin.ws.request;

public class CheckMassSendMsgRequest {
	private Long historyId;
	private Integer msgStatus;

	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public Integer getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(Integer msgStatus) {
		this.msgStatus = msgStatus;
	}

}
