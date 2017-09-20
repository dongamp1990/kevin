package org.alipp.api.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ParseUrlUtil {
	private static String _720PKey = "720p";
	private static String _720PKey_2 = "720P";
	private static String _HDTV = "高清TV";
	private static String _HDTV__2 = "HDTV";
	private static String _1080P = "1080p";
	private static String _1080P_2 = "1080P";
	
	public static void main(String[] args) {
//		parseLOLDYTTURL("http://www.loldytt.com/Zuixinmeiju/ZPWDWJ/");
		parsePIAOVUrl("http://www.piaov.com/html/496/");
	}
	
	/**
	 * 解析LOL电影天堂下载地址
	 * @param urlStr 页面地址
	 */
	public static void parseLOLDYTTURL(String urlStr) {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setRequestProperty("Accept-Charset", "gb2312");
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gb2312"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			StringBuilder _720p = new StringBuilder();
			StringBuilder tv = new StringBuilder();
			String hrefStart = "<a href=\"";
			String titleStart = "\" title";
			StringBuilder other = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				buffer.append(line).append("\r\n");
				if (line.contains("thunder")) {
					int urlIndex = line.indexOf(hrefStart) + hrefStart.length();
					int urlEnd = line.indexOf(titleStart);
					String downUrl = line.substring(urlIndex, urlEnd);
					if (line.contains(_720PKey) || line.contains(_720PKey_2)) {
						_720p.append(downUrl).append("\r");
					}else if(line.contains(_HDTV) || line.contains(_HDTV__2)){
						tv.append(downUrl).append("\r");
					}else if(line.contains(_1080P) || line.contains(_1080P_2)){
						other.append(downUrl).append("\r");
					}
				}
			}
			reader.close();
			System.out.println("=====================高清tv下载========================\r");
			System.out.println(tv.toString());
			System.out.println("=====================720p高清下载======================\r");
			System.out.println(_720p.toString());
			System.out.println("=====================其他下载======================\r");
			System.out.println(other.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (connection != null) {
				connection.disconnect();
			} 
		}
	}
	
	/**
	 * 解析飘V网下载地址
	 * @param urlStr
	 */
	public static void parsePIAOVUrl(String urlStr) {
		try (WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
			webClient.getOptions().setThrowExceptionOnScriptError(false);
	        HtmlPage page = webClient.getPage(urlStr);
	        DomNodeList<DomElement> nodeList = page.getElementsByTagName("a");
	        StringBuilder buffer = new StringBuilder();
	        StringBuilder _720p = new StringBuilder();
	        if (nodeList != null) {
				for (DomElement domElement : nodeList) {
					String href = domElement.getAttribute("href");
					String textContent = domElement.getTextContent();
					if (href != null && href.startsWith("thunder")) {
						if (textContent.contains(_720PKey)) {
							_720p.append(href).append("\r");
						}else {
							buffer.append(href).append("\r");
						}
					}
				}
			}
	        System.out.println("=====================720p======================");
	        System.out.println(_720p.toString());
	        System.out.println("=====================tv========================");
	        System.out.println(buffer.toString());
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}
