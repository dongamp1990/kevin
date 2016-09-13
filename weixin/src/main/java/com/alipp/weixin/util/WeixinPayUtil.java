package com.alipp.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.alipp.weixin.domain.CashRedPacket;
import com.alipp.weixin.domain.UnifiedOrder;
	
public class WeixinPayUtil {
	private static final Logger logger = LoggerFactory.getLogger(WeixinPayUtil.class);
	public static final String APPID = PropertyUtil.get("weixin.appid"); // 公众号id
	public static final String MCHID = PropertyUtil.get("weixin_mch_id"); // 服务号支付商户号
	private static String SEND_RED_PACK_URL = PropertyUtil.get("weixin_send_redpacket_url");
	public static final String SIGNMEDHOD_MD5 = "MD5";
	private static final String KEY = "zgNxpBVDrZgAV54olfoVDI7Gg2qOcBwO";
	private static SSLSocketFactory socketFactory;
	private static String UNIFIEDORDER_URL = PropertyUtil.get("weixin_unifiedorder_url"); //统一下单接口
	private static String PAY_NOTIFY_URL = PropertyUtil.get("weixin_pay_notify_url");
	
	static {
		initSSLConnectionSocketFactory();
	}

	private static void initSSLConnectionSocketFactory(){
		InputStream instream = null;
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			instream = new ClassPathResource("cert\\apiclient_cert.p12").getInputStream();
			keyStore.load(instream, MCHID.toCharArray());
			SSLContext context = SSLContexts.custom().loadKeyMaterial(keyStore, MCHID.toCharArray()).build();
			socketFactory = context.getSocketFactory();
		} catch (Exception e) {
			logger.error("initSSLConnectionSocketFactory error.");
			e.printStackTrace();
		} finally {
			if (instream != null) {
				try {
					instream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/***
	 *  @param cashRedPacket
	 *  @return 返回报文
	 *	<xml>
	 *	<return_code><![CDATA[SUCCESS]]></return_code>
	 *	<return_msg><![CDATA[发放成功.]]></return_msg>
	 *	<result_code><![CDATA[SUCCESS]]></result_code>
	 *	<err_code><![CDATA[0]]></err_code>
	 *	<err_code_des><![CDATA[发放成功.]]></err_code_des>
	 *	<mch_billno><![CDATA[0010010404201411170000046545]]></mch_billno>
	 *	<mch_id>10010404</mch_id>
	 *	<wxappid><![CDATA[wx6fa7e3bab7e15415]]></wxappid>
	 *	<re_openid><![CDATA[onqOjjmM1tad-3ROpncN-yUfa6uI]]></re_openid>
	 *	<total_amount>1</total_amount>
	 *	<send_listid>100000000020150520314766074200</send_listid>
	 *	<send_time>20150520102602</send_time>
	 *	</xml>
	 */
	public static Map<String, Object> sendRedPacket(CashRedPacket cashRedPacket) {
		try {
			cashRedPacket.setMch_id(MCHID);
			cashRedPacket.setNonce_str(StringUtil.generatorId(32));
			cashRedPacket.setClient_ip("183.39.138.157");
			cashRedPacket.setWxappid(APPID);
			
			try {
				Map<String, Object> paramMap = JSONUtil.object2Map(cashRedPacket);
				String sign = signParameters(paramMap, SIGNMEDHOD_MD5);
				cashRedPacket.setSign(sign);
			} catch (Exception e) {
				logger.error("convert to map error", e);
			} 
			String requestStr = MessageUtil.beanToXml(cashRedPacket);
			System.out.println(requestStr);
			String result = HttpUtil.postWithSecurity(SEND_RED_PACK_URL, requestStr, socketFactory);
			
			return XMLParser.getMapFromXML(result);
		}  catch (Exception e) {
			logger.error("ERROR", e);
			return null;
		}
	}
	
	/***
	 *  @param 公众号支付 unifiedorder 统一下单接口
	 *  @return 返回报文 
	 * <xml>
	 * 	<return_code><![CDATA[SUCCESS]]></return_code>
	 *  <return_msg><![CDATA[OK]]></return_msg>
	 *  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
	 *  <mch_id><![CDATA[10000100]]></mch_id>
	 *  <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>
	 *  <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>
	 *  <result_code><![CDATA[SUCCESS]]></result_code>
	 *  <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>
	 *  <trade_type><![CDATA[JSAPI]]></trade_type>
	 *</xml>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> unifiedorder(UnifiedOrder order) {
		try {
			order.setAppid("APPID");
			order.setNonce_str(StringUtil.generatorId(32));
			order.setNotify_url(PAY_NOTIFY_URL);
			Map<String, Object> paramMap = JSONUtil.readValueToTargetObj(order, HashMap.class);
			String sign = signParameters(paramMap, SIGNMEDHOD_MD5);
			order.setSign(sign);
			String requestStr = MessageUtil.beanToXml(order);
			String result = HttpUtil.postWithSecurity(UNIFIEDORDER_URL, requestStr, socketFactory);
//				return XMLParser.getMapFromXML(result);
			return XmlUtil.xml2Map(result);
		} catch (Exception e) {
			logger.error("XMLParser.getMapFromXML error.", e);
			return null;
		}
	}
	
	private static String signParameters(Map<String, Object> params,
			String signMethod) {
		logger.debug("Start calling sign parameters");
		
		String signedParams = "";
		String paramString  = joinMapValue(params);
		String paramsToStr = paramString + "&key=" +KEY;
		
		if (signMethod.equalsIgnoreCase(SIGNMEDHOD_MD5)) {
			signedParams = DigestUtils.md5Hex(paramsToStr).toUpperCase();
			logger.debug("sign parameters - signedParams: {}",	signedParams);
		} else {
			logger.warn("sign parameters - unsupported sign method, signMethod: {}", signMethod);
		}
		
		return signedParams;
	}
	
	private static String joinMapValue(Map<String, Object> map) {
		List<String> list = new ArrayList<String>(map.keySet());
		Collections.sort(list);
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < list.size(); i++){
			String key = list.get(i);
			Object value = map.get(key);
			
			if (map.get(key) != null) {
				if(i == list.size() - 1){//拼接时，不包括最后一个&字符
					 sb.append(key).append("=").append(value);
				} else {
					 sb.append(key).append("=").append(value).append("&");
				}
			}
		}
		String strRet = sb.toString();
		return strRet;
	}
	
	
	/**
	 * 检查回调内容签名是否正确
	 * @param params
	 * @return
	 */
	public static boolean checkPayNotifyIsSignValid(Map<String, Object> params) {
		String remotoSign = params.get("sign").toString();
		if (remotoSign == null || "".equals(remotoSign)) {
			return false;
		}
		params.put("sign", "");
		String sign = signParameters(params, SIGNMEDHOD_MD5);
		if (!remotoSign.equals(sign)) {
			return false;
		}
		return true;
	}
	
	public static Map<String, String> send(String url, Map<String, Object> paramMap) {
		try {
			String sign = signParameters(paramMap, SIGNMEDHOD_MD5);
			paramMap.put("sign", sign);
			String requestStr = JacksonUtils.beanToXml(paramMap);
			requestStr = requestStr.replace("HashMap", "xml");
			String result = HttpUtil.postWithSecurity(url, requestStr, socketFactory);
			return XmlUtil.xml2Map(result);
		} catch (Exception e) {
			logger.error("XMLParser.getMapFromXML error.", e);
			return null;
		}
	}
}
