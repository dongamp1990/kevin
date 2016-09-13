package com.alipp.weixin.constant;

public interface MenuType {
	public final String CLICK = "click"; //点击推时间
	public final String VIEW = "view"; //跳转url
	public final String SCANCODE_WAITMSG = "scancode_waitmsg"; // 扫码带提示
	public final String SCANCODE_PUSH = "scancode_push"; // 扫码推事件
	public final String PIC_SYSPHOTO = "pic_sysphoto"; //系统拍照发图
	public final String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album"; //拍照或者相册发图
	public final String PIC_WEIXIN = "pic_weixin"; //微信相册发图
	public final String LOCATION_SELECT = "location_select"; //发送位置
	public final String MEDIA_ID = "media_id"; //图片
	public final String VIEW_LIMITED = "view_limited"; //图文消息
}
