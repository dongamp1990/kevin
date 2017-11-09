package com.alipp.weixin.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipp.weixin.domain.JsWeixinConfig;
import com.alipp.weixin.domain.MassTextMsg;
import com.alipp.weixin.domain.Menu;
import com.alipp.weixin.domain.WxTemplateMsg;
import com.alipp.weixin.param.QrCodeParam;

public class WeixinUtil {

//	private static RedisService redisService;
	
	private static String TOKEN = PropertyUtil.get("weixin.token");
	private static Logger logger = LoggerFactory.getLogger(WeixinUtil.class);
	private static String APPID;
	private static String SECRET;
	private static String GET_ACCESSTOKEN_URL;
	private static String OAUTH2_URL;
	private static String USERINFO_URL = PropertyUtil.get("weixin.userinfo.url");
	private static String SHORT_URL = PropertyUtil.get("weixin.shorturl");
	private static String SEND_TEMPLATE_MESSAGE_URL = PropertyUtil.get("weixin.message_template_send_url");
	private static String GET_MENU_URL = PropertyUtil.get("weixin.get_menu_url");
	private static String MASS_SEND_URL = PropertyUtil.get("weixin_mass_send_url"); //群发消息接口
	private static String WEIXIN_TOKEN_KEY = "Weixin_Token_MaxFun";
	private static String WEIXIN_JSAPI_TICKET_KEY = "WEIXIN_JSAPI_TICKET_MAXFUN";
	private static String JSAPI_TICKET_URL;
	
	private static Map<String, String> tokenMap = new HashMap<String, String>();
	
	static {
		tokenMap.put("access_token", "EthhOXlDv2fjlQNvHHGy1We9gxs-e11PUKLqRg10QAuZOAUAVCQFbaxLprystpl2hmMjiHHMHOA_VgppyGFakKaaB2xhetITCQvD4RT8ZpJ4JG0Ri2jS9xFdZg3WUTZbVQRfAFARMN");
		tokenMap.put("date", System.currentTimeMillis() + "");
		APPID = PropertyUtil.get("weixin.appid");
		SECRET = PropertyUtil.get("weixin.secret");
		GET_ACCESSTOKEN_URL = PropertyUtil.get("weixin.get_accesstoken_url");
		GET_ACCESSTOKEN_URL = GET_ACCESSTOKEN_URL.replace("${appid}", APPID);
		GET_ACCESSTOKEN_URL = GET_ACCESSTOKEN_URL.replace("${secret}", SECRET);
		OAUTH2_URL = PropertyUtil.get("weixin.oauth2.url");
		OAUTH2_URL = OAUTH2_URL.replace("APPID", APPID);
		OAUTH2_URL = OAUTH2_URL.replace("SECRET", SECRET);
		JSAPI_TICKET_URL = PropertyUtil.get("weixin_jsapi_ticket_url");
//		redisService = (RedisService) SpringUtil.getBeanById("redisServiceImpl");
	}
	
	private static String getToken() {
		String token = tokenMap.get("access_token");
		String date = tokenMap.get("date");
		if (token == null) {
			return token;
		}
		Long dateLong = Long.valueOf(date);
		if ((System.currentTimeMillis() - 7200 * 1000) > dateLong) {
			return null;
		}
		return token;
	}

	public static String getAccessToken() {
		String token = getToken();
		if (token != null) {
			return token;
		}
		try {
			//{"access_token":"ACCESS_TOKEN","expires_in":7200} 正常情况返回
			//{"errcode":40013,"errmsg":"invalid appid"} 错误时微信会返回错误码等信息
			String result = HttpUtil.get(GET_ACCESSTOKEN_URL);
			Map<String, String> map = JacksonUtils.json2Object(result, Map.class);
			if (map == null) {
				throw new Exception("getAccessToken fail");
			}
			if (map.containsKey("errcode")) {
				throw new Exception(map.toString());
			}
			token = map.get("access_token").toString();
			System.out.println(token);
			tokenMap.put("access_token", token);
			tokenMap.put("date", System.currentTimeMillis() + "");
			
		} catch (Exception e) {
			logger.error("getAccessToken", e.getMessage());
			e.printStackTrace();
		}
		
		return token;
	}
	
//	public static String getAccessToken() {
//		String token = null;
//		try {
//			Object tokenObj = redisService.get(WEIXIN_TOKEN_KEY);
//			
//			
//			if (tokenObj == null) {
//				//{"access_token":"ACCESS_TOKEN","expires_in":7200} 正常情况返回
//				//{"errcode":40013,"errmsg":"invalid appid"} 错误时微信会返回错误码等信息
//				String result = HttpUtil.get(GET_ACCESSTOKEN_URL);
//				
//				Map<String, Object> map = convertResult(result, Map.class);
//				if (map == null) {
//					throw new Exception("getAccessToken fail");
//				}
//				if (map.containsKey("errcode")) {
//					throw new Exception(map.toString());
//				}
//				token = map.get("access_token").toString();
////				redisService.set(WEIXIN_TOKEN_KEY, token, 7200);
//			}else {
//				token = tokenObj.toString();
//			}
//		} catch (Exception e) {
//			logger.error("getAccessToken", e.getMessage());
//			e.printStackTrace();
//		}
//		return token;
//	}
	
	public static Boolean checkSignature(String signature, String timestamp, String nonce,
			String echostr) {
		StringBuilder builder = new StringBuilder();
		String[] arr = { TOKEN, timestamp, nonce };
		Arrays.sort(arr);
		for (String string : arr) {
			builder.append(string);
		}
		String str = EncoderHandler.encode("SHA1", builder.toString());
		return str.equals(signature);
	}
	
	/**
	 * 请求微信 生成二维码
	 * @param codeParam
	 * @return
	 */
	public static String getWeixinQrCodeUrl(QrCodeParam codeParam) {
		try {
			String token = getAccessToken();
			String requestUrl = PropertyUtil.get("weixin.qrcode.url");
			requestUrl = requestUrl.replace("TOKEN", token);
			String result = HttpUtil.post(requestUrl, codeParam);
			Map<String, Object> map = JSONUtil.object2Map(result);
			String ticket = map.get("ticket").toString();
			String url = PropertyUtil.get("weixin.showqrcode_url");
			url = url.replaceAll("TICKET", ticket);
			return url;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void createMenu(Menu menu) {
		try {
			String token = getAccessToken();
			String requestUrl = PropertyUtil.get("weixin.create_menu.url");
			requestUrl = requestUrl.replace("TOKEN", token);
			String result = HttpUtil.post(requestUrl, menu);
			Map<String, Object> map = JSONUtil.object2Map(result);
			System.out.println(map.values().toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取oauth2 access token
	 * @param code
	 * @return
	 * @throws Exception 
	 */
	public static Map<String, Object> getOauth2AccessToken(String code) throws Exception {
		String url = new String(OAUTH2_URL);
		url = url.replace("CODE", code);
		String result = HttpUtil.post(url);
		Map<String, Object> resultMap = JSONUtil.object2Map(result);
		return resultMap;
	}
	
	/**
	 * oauth2
	 * @param oauth2 code
	 * @param state
	 * @return
	 * @throws Exception 
	 */
	public static String getWeixinOpenId(String code,
			String state) throws Exception {
		Map<String, Object> resultMap = getOauth2AccessToken(code);
		if (resultMap == null) {
			return null;
		}
		String openid = resultMap.get("openid") == null ? null : resultMap.get("openid").toString();
		return openid;
	}
	
	/**
	 * 获取微信用户信息
	 * @param openId
	 * @return
	 * {
	   "subscribe": 1, 
	    "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M", 
	    "nickname": "Band", 
	    "sex": 1, 
	    "language": "zh_CN", 
	    "city": "广州", 
	    "province": "广东", 
	    "country": "中国", 
	    "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0", 
	   "subscribe_time": 1382694957,
	   "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
	   "remark": "",
	   "groupid": 0
	}
	 * @throws Exception 
	 */
	public static Map<String, Object> getUserInfo(String openId) throws Exception {
		String url = USERINFO_URL;
		url = url.replace("ACCESS_TOKEN", getAccessToken());
		url = url.replace("OPENID", openId);
		String result = HttpUtil.get(url);
		Map<String, Object> userInfo = JSONUtil.object2Map(result);
		return userInfo;
	}
	
	/**
	 * 生成短域名
	 * @param url
	 * @return
	 */
	
	public static Map<String, Object> getShortUrl(String url) {
		
		String requestUrl = SHORT_URL;
		requestUrl = requestUrl.replace("ACCESS_TOKEN", getAccessToken());
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("long_url", url);
		paramMap.put("action", "long2short");
		try {
			String result = HttpUtil.post(requestUrl, paramMap);
			return JSONUtil.object2Map(result);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getAPPID() {
		return APPID;
	}

	
	public static Map<String, Object> sendTemplateMsg(WxTemplateMsg msg) {
		String requestUrl = SEND_TEMPLATE_MESSAGE_URL.replace("ACCESS_TOKEN",
				getAccessToken());
		try {
			String result = HttpUtil.post(requestUrl, msg);
			return JSONUtil.object2Map(result);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查询菜单
	 * @return
	 */
	
	public static Map<String, Object> getMenu() {
		try {
			String url = GET_MENU_URL.replace("ACCESS_TOKEN", getAccessToken());
			String menuStr = HttpUtil.get(url);
			return JSONUtil.object2Map(menuStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 群发文字短信
	 * @param msg
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> massSendTextMsg(MassTextMsg msg) {
		try {
			String url = MASS_SEND_URL.replace("ACCESS_TOKEN", getAccessToken());
			String result = HttpUtil.post(url, msg);
			return JSONUtil.object2Map(result);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JsWeixinConfig getJsWeixinConfig(String url) {
		JsWeixinConfig config = new JsWeixinConfig();
		config.setAppId(APPID);
		config.setNonceStr(StringUtil.generatorId(32));
		config.setTimestamp((System.currentTimeMillis() / 1000) + "");
		signJsWeixinConfig(config, url	);
		return config;
	}
	
//	public static String getJsApiTicket() {
//		String ticket = null;
//		try {
//			Object ticketObj = redisService.get(WEIXIN_JSAPI_TICKET_KEY);
//		
//			if (ticketObj == null) {
//				String requestUrl = JSAPI_TICKET_URL.replace("ACCESS_TOKEN", getAccessToken());
//				//获取ticket
//				String resultStr;
//				resultStr = HttpUtil.post(requestUrl);
//				Map<String, Object> map = convertResult(resultStr, Map.class);
//				if (map == null) {
//					throw new Exception("getJsApiTicket fail.");
//				}
//				if (Integer.valueOf(map.get("errcode").toString()) == 0) {
//					ticket = map.get("ticket").toString();
//					redisService.set(WEIXIN_JSAPI_TICKET_KEY, ticket, 7200);
//				}
//			}else {
//				ticket = ticketObj.toString();
//			}
//		} catch (Exception e) {
//			logger.error("getJsApiTicket", e);
//		}
//		return ticket;
//	}

	//生成签名
	private static void signJsWeixinConfig(JsWeixinConfig config, String url) {
//		String ticket = getJsApiTicket();
		String ticket = null;
		StringBuilder builder = new StringBuilder();
		builder.append("jsapi_ticket").append("=").append(ticket).append("&");
		builder.append("noncestr").append("=").append(config.getNonceStr()).append("&");
		builder.append("timestamp").append("=").append(config.getTimestamp()).append("&");
		builder.append("url").append("=").append(url);
		config.setSignature(EncoderHandler.encode("SHA-1", builder.toString()));
	}
}
