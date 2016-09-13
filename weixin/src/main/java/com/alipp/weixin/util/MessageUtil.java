package com.alipp.weixin.util;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alipp.weixin.constant.MessageType;
import com.alipp.weixin.domain.Article;
import com.alipp.weixin.domain.NewsMessage;
import com.alipp.weixin.domain.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class MessageUtil {

	private static XStream xStream;
	
	static {
		xStream = new XStream(new XppDriver() { 
		    public HierarchicalStreamWriter createWriter(Writer out) { 
		        return new PrettyPrintWriter(out) { 
		            // 对所有xml节点的转换都增加CDATA标记 
		            boolean cdata = true; 
		 
		            public void startNode(String name) { 
		                super.startNode(name); 
		            } 
		 
		            protected void writeText(QuickWriter writer, String text) { 
		                if (cdata) { 
		                    writer.write("<![CDATA["); 
		                    writer.write(text); 
		                    writer.write("]]>"); 
		                } else { 
		                    writer.write(text); 
		                } 
		            } 
		        }; 
		    } 
		});
	}
	
	/**
	 * 对象转换成xml
	 * @param object
	 * @return
	 */
	public static String beanToXml(Object object) {
		xStream.alias("xml", object.getClass()); 
		return xStream.toXML(object);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T xmlToBean(String xmlStr, Class<T> cls) {
		return (T) xStream.fromXML(xmlStr);
	}
	
	/**
	 * 图文消息 转还成xml
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		xStream.alias("xml", newsMessage.getClass()); 
		xStream.alias("item", Article.class);
		return xStream.toXML(newsMessage);
	}
	
	public static String getSubscriptionMsg(Map<String, String> requestMap) {
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setCreateTime(System.currentTimeMillis() + "");
		newsMessage.setFromUserName(requestMap.get("ToUserName"));
		newsMessage.setToUserName(requestMap.get("FromUserName"));
		
		List<Article> articles = new ArrayList<Article>();
		
		Article article = new Article();
		
		article.setDescription("备注a");
		article.setPicUrl("http://xx.com/a.png");
		article.setUrl("http://baidu.com");
		articles.add(article);
		
		newsMessage.setArticles(articles);
		newsMessage.setArticleCount("1");
		
		xStream.alias("xml", NewsMessage.class); 
		xStream.alias("item", Article.class);

		String xml = xStream.toXML(newsMessage);
		
		return xml;
	}
	
	public static void main(String[] args) {
//		TextMessage textMessage = new TextMessage();
//		textMessage.setContent("欢迎使用。");
//		textMessage.setCreateTime(System.currentTimeMillis() + "");
//		textMessage.setFromUserName("aa");
//		textMessage.setToUserName("bb");
//		textMessage.setMsgType(MessageType.TEXT);
//		String responseStr = MessageUtil.beanToXml(textMessage);
		
		System.out.println(getSubscriptionMsg(new HashMap()));
	}
}
