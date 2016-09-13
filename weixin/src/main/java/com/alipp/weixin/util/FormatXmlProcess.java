package com.alipp.weixin.util;

import java.util.Date;
import java.util.List;
/**
 * 封装最终的xml格式结果
 *
 */
public class FormatXmlProcess {
	/**
	 * 封装文字类的返回消息
	 * @param to
	 * @param from
	 * @param content
	 * @return
	 */
	public static String getTextMsg(String to, String from, String content) {
		StringBuffer sb = new StringBuffer();
		Date date = new Date();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(to);
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(from);
		sb.append("]]></FromUserName><CreateTime>");
		sb.append(date.getTime());
		sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
		sb.append(content);
		sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");
		return sb.toString();
	}
	
	/**
	 * 发送图文消息
	 * @param to
	 * @param form
	 * @param datas
	 * @return
	 */
	public static String getNewsMsg(String to, String form, List datas) {
		StringBuffer buffer = new StringBuffer();
		Date date = new Date();
		buffer.append("<xml>")
			  .append("<ToUserName><![CDATA[").append(to).append("]]></ToUserName>")
			  .append("<FromUserName><![CDATA[").append(form).append("]]></FromUserName>")
			  .append("<CreateTime>").append(date.getTime()).append("</CreateTime>")
			  .append("<MsgType><![CDATA[news]]></MsgType>");
		
		buffer.append("<ArticleCount>").append(datas.size()).append("</ArticleCount>");
		buffer.append("<Articles>");
		
		for (Object object : datas) {
			buffer.append("<item>")
				  .append("<Title><![CDATA[").append("title").append("]]></Title>")
				  .append("<Description><![CDATA[").append("Description").append("]]></Description>")
				  .append("<PicUrl><![CDATA[").append("PicUrl").append("]]></PicUrl>")
				  .append("<Url><![CDATA[").append("Url").append("]]></Url>");
			buffer.append("</item>");
		}
		
		buffer.append("</Articles>");
		buffer.append("</xml>");
		
		return buffer.toString();
	} 
}
