package com.alipp.weixin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
	
	private static final String DEFAULT_CHARSET = "UTF-8";
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	private static String APPLICATION_JSON = "application/json";
	public static String post(String requestUrl) {
		String result = postSend(requestUrl, null);
		return result;
	}
	
	public static String post(String requestUrl, Object param) {
		// 设置请求参数
		String result =  postSend(requestUrl, JSONUtil.objectToJson(param), APPLICATION_JSON, null);
		return result;
	}
	
	public static String post(String requestUrl, String requestParam,
			String contentType) {
		// 设置请求参数
		String result = postSend(requestUrl, requestParam, contentType, null);
		return result;
	}
	
	public static String postWithAuth(String url, Object param, String authorizationToken) {
		// 设置请求参数
		String result = postSend(url, JSONUtil.objectToJson(param), APPLICATION_JSON, authorizationToken);
		return result;
	}
	
	public static String postWithSecurity(String requestUrl, String requestStr,
			SSLSocketFactory socketFactory) {
		logger.info("requestUrl = {}, requestParam={}", requestUrl, requestStr);
		HttpsURLConnection connection = null;
		try {
			HostnameVerifier verifier = new HostnameVerifier() {
				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			};
			HttpsURLConnection.setDefaultSSLSocketFactory(socketFactory);
			HttpsURLConnection.setDefaultHostnameVerifier(verifier);
			Map<String,String> requestProperty = new HashMap<String, String>();
			requestProperty.put("Content-Type", APPLICATION_JSON);
			connection = builderHttpsConnection(requestUrl, "POST", requestProperty);
			connection.connect();
			// POST方法时使用
			if (requestStr != null) {
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), DEFAULT_CHARSET); // utf-8编码
				out.append(requestStr);
				out.flush();
				out.close();
			}
			return getResultStr(connection.getInputStream());
		} catch (IOException e) {
			logger.error("post IOException", e);
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static String get(String requestUrl) {
		String result = getSend(requestUrl);
		return result;
	}
	
	private static String postSend(String strUrl, String param, String contentType, String authorizationToken) {
		logger.info("postSend requestUrl = {}", strUrl);
		logger.info("postSend param = {}, contentType = {}", param, contentType);
		HttpURLConnection connection = null;
		try {
			Map<String,String> requestProperty = new HashMap<String, String>();
			requestProperty.put("Content-Type", contentType);
			if (authorizationToken != null) {
				requestProperty.put("Authorization", "bearer " + authorizationToken);
			}
			connection = builderConnection(strUrl, "POST", requestProperty);
			connection.connect();
			// POST方法时使用
			if (param != null) {
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), DEFAULT_CHARSET); // utf-8编码
				out.append(param);
				out.flush();
				out.close();
			}
			return getResultStr(connection.getInputStream());
		} catch (IOException e) {
			logger.error("postSend IOException", e);
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static HttpURLConnection builderConnection(String strUrl, String method,
			Map<String, String> requestProperty) throws MalformedURLException, IOException, ProtocolException {
		URL url = new URL(strUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod(method);
		connection.setUseCaches(false);
		connection.setRequestProperty("Accept-Charset", DEFAULT_CHARSET);
		if (requestProperty != null && !requestProperty.isEmpty()) {
			for (String property : requestProperty.keySet()) {
				connection.setRequestProperty(property, requestProperty.get(property));
			}
		}
		return connection;
	}
	
	private static String postSend(String strUrl, String param) {
		logger.info("postSend requestUrl = {}", strUrl);
		logger.info("postSend param = {}", param);
		HttpURLConnection connection = null;
		try {
			Map<String,String> requestProperty = new HashMap<String, String>();
			requestProperty.put("Content-Type", APPLICATION_JSON);
			connection = builderConnection(strUrl, "POST", requestProperty);
			connection.connect();
			// POST方法时使用
			if (param != null) {
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), DEFAULT_CHARSET); // utf-8编码
				out.append(param);
				out.flush();
				out.close();
			}
			return getResultStr(connection.getInputStream());
		} catch (IOException e) {
			logger.error("postSend IOException", e);
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	public static String getSendWithToken(String strUrl,String Token) {
		logger.info("getSend requestUrl = {}", strUrl);
		HttpURLConnection connection = null;
		Map<String,String> requestProperty = new HashMap<String, String>();
		if (Token != null) {
			requestProperty.put("Authorization", "bearer " + Token);
		}
		try {
			connection = builderConnection(strUrl, "GET", requestProperty);;
			connection.connect();
			return getResultStr(connection.getInputStream());
		} catch (IOException e) {
			logger.error("getSend IOException", e);
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	private static String getSend(String strUrl) {
		logger.info("getSend requestUrl = {}", strUrl);
		HttpURLConnection connection = null;

		try {
			connection = builderConnection(strUrl, "GET", null);;
			connection.connect();
			return getResultStr(connection.getInputStream());
		} catch (IOException e) {
			logger.error("getSend IOException", e);
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static String getResultStr(InputStream inputStream)
			throws UnsupportedEncodingException, IOException {
		if (NumberUtil.isEqual(inputStream.available(),0)) {
			return null;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		return buffer.toString();
	}
	
	public static HttpsURLConnection builderHttpsConnection(String strUrl, String method,
			Map<String, String> requestProperty) throws MalformedURLException, IOException, ProtocolException {
		URL url = new URL(strUrl);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod(method);
		connection.setUseCaches(false);
		connection.setRequestProperty("Accept-Charset", DEFAULT_CHARSET);
		if (requestProperty != null && !requestProperty.isEmpty()) {
			for (String property : requestProperty.keySet()) {
				connection.setRequestProperty(property, requestProperty.get(property));
			}
		}
		return connection;
	}
	
	/**
	 * https post
	 * @param url
	 * @param param
	 * @param authorizationToken 可有可无
	 * @return
	 */
	public static String httpsPost(String url, Object param, String authorizationToken) {
		HttpsURLConnection connection = null;
		try {
			Map<String,String> requestProperty = new HashMap<String, String>();
			requestProperty.put("Content-Type", APPLICATION_JSON);
			if (authorizationToken != null) {
				requestProperty.put("Authorization", "bearer " + authorizationToken);
			}
			connection = builderHttpsConnection(url, "POST", requestProperty);
			if (param != null) {
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), DEFAULT_CHARSET); // utf-8编码
				out.append(JSONUtil.objectToJson(param));
				out.flush();
				out.close();
			}
			return getResultStr(connection.getInputStream());
		} catch (Exception e) {
			logger.error("httpsPost Error", e);
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
