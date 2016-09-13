package com.alipp.weixin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipp.weixin.constant.MsgType;
import com.alipp.weixin.domain.Menu;
import com.alipp.weixin.strategy.MessageProcessFactory;
import com.alipp.weixin.util.HttpUtil;
import com.alipp.weixin.util.JSONUtil;
import com.alipp.weixin.util.WeixinUtil;

@Named
@Controller 
public class WeixinController {

	Logger logger = LoggerFactory.getLogger(WeixinController.class);
//	
//	@Inject
//	private CustomerService customerService; 
	
//	@Inject
//	private RedisService redisService;
	
	@RequestMapping(value = "/weixin")
	public void weixin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String method = request.getMethod();
		
		if ("get".equalsIgnoreCase(method)) {
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String echostr = request.getParameter("echostr");

			if (signature != null && timestamp != null && nonce != null
					&& echostr != null) {
				Boolean success = WeixinUtil.checkSignature(signature, timestamp,
						nonce, echostr);

				if (success) {
					try {
						response.getWriter().print(echostr);
					} catch (IOException e) {
						e.printStackTrace();
					}

					logger.debug("接入微信成功..");
				} else {
					logger.debug("接入微信失败..");
				}
			}
		} else if ("post".equalsIgnoreCase(method)) {
			// 正常的微信处理流程
			String requestStr = HttpUtil.getResultStr(request.getInputStream());
			try {
				manageMessage(requestStr, request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return
	 * @exception ServletException , IOException
	 * @param
	 * 
	 */
	private void manageMessage(String requestStr, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			@SuppressWarnings("unchecked")
			Map<String, String> requestMap = JSONUtil.jsonToObject(requestStr, HashMap.class);
			logger.debug("resultMap :", requestMap.toString());
			String msgType = requestMap.get("MsgType");
			MessageProcessFactory.processMessage(MsgType.getMsgType(msgType), requestMap, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@RequestMapping(value = "/index")
//	public void index(HttpServletRequest request,
//			HttpServletResponse response, Model model) throws IOException {
//		String code = request.getParameter("code");
//		String state = request.getParameter("state");
//		
//		//获取用户信息
//		String openId;
//		try {
//			openId = WeixinUtil.getWeixinOpenId(code, state);
//			putToRedis(openId);
////			response.sendRedirect(UrlConstant.INDEX_URL + openId);
//		} catch (UtilityException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private void putToRedis(String openId) {
//		redisService.set(openId, false);
//	}
//
//	@RequestMapping(value = "/coupon")
//	public void coupon(HttpServletRequest request,
//			HttpServletResponse response, Model model) throws IOException {
//		String code = request.getParameter("code");
//		String state = request.getParameter("state");
//		
//		//获取用户信息
//		String openId;
//		try {
//			openId = WeixinUtil.getWeixinOpenId(code, state);
//			putToRedis(openId);
////			response.sendRedirect(UrlConstant.COUPON_URL + openId);
//		} catch (UtilityException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@RequestMapping(value = "/test")
//	public void test(HttpServletRequest request,
//			HttpServletResponse response, Model model) throws IOException {
//		String code = request.getParameter("code");
//		String state = request.getParameter("state");
//		
//		//获取用户信息
//		String openId;
//		try {
//			openId = WeixinUtil.getWeixinOpenId(code, state);
//			response.getWriter().write(openId);
//		} catch (UtilityException e) {
//			e.printStackTrace();
//		}
//	}

	@RequestMapping(value = "/createWxMenu", method = {RequestMethod.POST})
	public void createWxMenu(@RequestBody Menu menu) {
		WeixinUtil.createMenu(menu);
	}
	
	@RequestMapping(value = "/getShortUrl", method = {RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> getShortUrl(@RequestParam("url") String url) {
		Map<String, Object> map = WeixinUtil.getShortUrl(url);
		
		return map;
	}
 }
