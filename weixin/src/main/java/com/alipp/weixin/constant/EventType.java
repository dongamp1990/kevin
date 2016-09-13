package com.alipp.weixin.constant;

public interface EventType {

	public final String SUBSCRIBE = "subscribe";//订阅
	public final String UNSUBSCRIBE = "unsubscribe";//取消订阅
	public final String SCAN = "SCAN";//用户已关注时扫二维码的事件推送
	public final String LOCATION = "LOCATION";//上报地理位置事件
	public final String CLICK = "CLICK"; //点击菜单拉取消息时的事件推送 
	public final String VIEW = "VIEW"; //点击菜单跳转链接时的事件推送 
	public final String TEMPLATESENDJOBFINISH = "TEMPLATESENDJOBFINISH";//模板短信任务完成事件推送
	public final String MASSSENDJOBFINISH = "MASSSENDJOBFINISH"; //群发消息结果事件推送
	
}
