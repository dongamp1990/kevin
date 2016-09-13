package com.alipp.weixin.util;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipp.weixin.constant.CommonConstant;
import com.alipp.weixin.constant.CustomMsgType;
import com.alipp.weixin.domain.Article;

public class CustomMsgUtil {

	private static String customeMsgSendUrl = null;
	private static Logger logger = LoggerFactory.getLogger(CustomMsgUtil.class);
	
	static {
		customeMsgSendUrl = PropertyUtil.get("weixin_custom_msg_send_url");
	}
	
	public static void sendTextMsg(String touser, String content) {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("touser", touser);
			jsonObject.put("msgtype", CustomMsgType.TEXT);
			jsonObject.put("text", new JSONObject().put("content", content));
			
			String url = customeMsgSendUrl.replace("ACCESS_TOKEN", WeixinUtil.getAccessToken());
			String requestStr = jsonObject.toString();
			String result = HttpUtil.post(url, requestStr, CommonConstant.APPLICATION_JSON);
			logger.info("sendTextMsg param = {}", requestStr);
			logger.info("sendTextMsg result = {}", result);
		} catch (JSONException e) {
			logger.error("put json value fail.", e);
		}
	}
	
	public static void sendTextMsg(String touser, String content, String accessToken) {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("touser", touser);
			jsonObject.put("msgtype", CustomMsgType.TEXT);
			jsonObject.put("text", new JSONObject().put("content", content));
			
			String url = customeMsgSendUrl.replace("ACCESS_TOKEN", accessToken);
			String requestStr = jsonObject.toString();
			String result = HttpUtil.post(url, requestStr, CommonConstant.APPLICATION_JSON);
			logger.info("sendTextMsg param = {}", requestStr);
			logger.info("sendTextMsg result = {}", result);
		} catch (JSONException e) {
			logger.error("put json value fail.", e);
		}
	}
	
	public static void sendNewsMsg(String touser, List<Article> articles) {
		try {
			
			List<JSONObject> articleJsons = new ArrayList<JSONObject>();
			for (Article item : articles) {
				JSONObject jsonItem = new JSONObject();
				jsonItem.put("title", item.getTitle());
				jsonItem.put("description", item.getDescription());
				jsonItem.put("url", item.getUrl());
				jsonItem.put("picurl", item.getPicUrl());
				articleJsons.add(jsonItem);
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("touser", touser);
			jsonObject.put("msgtype", CustomMsgType.NEWS);
			jsonObject.put("news", new JSONObject().put("articles", articleJsons));
			
			String url = customeMsgSendUrl.replace("ACCESS_TOKEN", WeixinUtil.getAccessToken());
			String requestStr = jsonObject.toString();
			HttpUtil.post(url, requestStr, CommonConstant.APPLICATION_JSON);
			logger.info("sendNewsMsg param = {}", requestStr);
		} catch (JSONException e) {
			logger.error("put json value fail.", e);
		}
	}
	
	public static void sendNewsMsg(String touser, List<Article> articles, String accessToken) {
		try {
			
			List<JSONObject> articleJsons = new ArrayList<JSONObject>();
			for (Article item : articles) {
				JSONObject jsonItem = new JSONObject();
				jsonItem.put("title", item.getTitle());
				jsonItem.put("description", item.getDescription());
				jsonItem.put("url", item.getUrl());
				jsonItem.put("picurl", item.getPicUrl());
				articleJsons.add(jsonItem);
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("touser", touser);
			jsonObject.put("msgtype", CustomMsgType.NEWS);
			jsonObject.put("news", new JSONObject().put("articles", articleJsons));
			
			String url = customeMsgSendUrl.replace("ACCESS_TOKEN", accessToken);
			String requestStr = jsonObject.toString();
			HttpUtil.post(url, requestStr, CommonConstant.APPLICATION_JSON);
			logger.info("sendNewsMsg param = {}", requestStr);
		} catch (JSONException e) {
			logger.error("put json value fail.", e);
		}
	}
	
	public static void sendMpNewsMsg(String touser, String mediaId, String accessToken) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("touser", touser);
			jsonObject.put("msgtype", CustomMsgType.MPNEWS);
			jsonObject.put("mpnews", new JSONObject().put("media_id", mediaId));
			String url = customeMsgSendUrl.replace("ACCESS_TOKEN", accessToken);
			String requestStr = jsonObject.toString();
			String result = HttpUtil.post(url, requestStr, CommonConstant.APPLICATION_JSON);
			logger.info("sendMpNewsMsg result = {}", result);
		} catch (JSONException e) {
			logger.error("put json value fail.", e);
		} catch (Exception e) {
			logger.error("sendMpNewsMsg Error.", e);
		}
	}
}
