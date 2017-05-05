package org.alipp.api.controller;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;

public class MoveDownUrlParseController extends BaseController {
	
	@ActionKey("/mv/mvdownload")
	@Clear
	public void parseDownloadUrlBefore() {
		render("mvdownload.jsp");
	}
	
	@ActionKey("/mv/parseDownloadUrl")
	@Clear
	public void parseDownloadUrl() {
		String url = getPara("url");
		if (url == null || url.isEmpty()) {
			redirect("/mv/mvdownload");
			return;
		}
		long time = System.currentTimeMillis();
		try (WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
			webClient.getOptions().setThrowExceptionOnScriptError(false);
	        HtmlPage page = webClient.getPage(url);
	        DomNodeList<DomElement> nodeList = page.getElementsByTagName("a");
	        StringBuffer buffer = new StringBuffer();
	        StringBuffer _720p = new StringBuffer();
	        String _r = "\r";
	        String title = page.getTitleText();
	        if (nodeList != null) {
				for (DomElement domElement : nodeList) {
					String href = domElement.getAttribute("href");
					String textContent = domElement.getTextContent();
					if (href != null && href.startsWith("thunder")) {
						if (textContent.contains("720p")) {
							_720p.append(href.trim()).append(_r);
						}else if(!textContent.contains("迅雷下载")){
							buffer.append(href).append(_r);
						}
					}
				}
			}
//	        System.out.println("720p======================");
//	        System.out.println(_720p.toString());
//	        System.out.println("tv=================");
//	        System.out.println(buffer.toString());
	        setAttr("_720p", _720p.toString());
	        setAttr("tv", buffer.toString());
	        setAttr("title", title);
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
		System.out.println("解析耗时: " + (System.currentTimeMillis() - time) + "ms");
		render("showMvDownload.jsp");
	}
	
	
}
