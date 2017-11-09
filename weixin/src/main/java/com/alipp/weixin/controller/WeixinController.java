package com.alipp.weixin.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipp.weixin.constant.CommonConstant;
import com.alipp.weixin.constant.MsgType;
import com.alipp.weixin.domain.Menu;
import com.alipp.weixin.strategy.MessageProcessFactory;
import com.alipp.weixin.util.HttpUtil;
import com.alipp.weixin.util.JacksonUtils;
import com.alipp.weixin.util.WeixinUtil;

@Controller
@SuppressWarnings("unchecked")
public class WeixinController {

	Logger logger = CommonConstant.LOGGER;
	
	@Autowired
	private MessageProcessFactory messageProcessFactory;
	private static byte[] white_bytes = "".getBytes(); 
	//
	// @Inject
	// private CustomerService customerService;

	// @Inject
	// private RedisService redisService;

	@RequestMapping(value = "/weixin", method = RequestMethod.GET)
	public void weixinGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if (signature != null && timestamp != null && nonce != null
				&& echostr != null) {
			Boolean success = WeixinUtil.checkSignature(signature,
					timestamp, nonce, echostr);
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
	}

	@RequestMapping(value = "/weixin", method = RequestMethod.POST)
	public void weixinPost(HttpServletRequest request, HttpServletResponse response) {
		// 正常的微信处理流程
		try {
			String requestStr = HttpUtil.getResultStr(request.getInputStream());
			Map<String, String> requestMap = JacksonUtils.xmlToBean(requestStr, HashMap.class);
			logger.debug("resultMap :", requestMap.toString());
			String msgType = requestMap.get("MsgType");
			String result = messageProcessFactory.processMessage(MsgType.getMsgType(msgType), requestMap );
			if (result != null) {
				response.getOutputStream().write(result.getBytes("utf-8"));
				response.getOutputStream().close();
			} else {
				response.getOutputStream().write(white_bytes);
				response.getOutputStream().close();
			}
		} catch (Exception e) {
			logger.error("process weixinPost error", e);
		}
	}

	// @RequestMapping(value = "/index")
	// public void index(HttpServletRequest request,
	// HttpServletResponse response, Model model) throws IOException {
	// String code = request.getParameter("code");
	// String state = request.getParameter("state");
	//
	// //获取用户信息
	// String openId;
	// try {
	// openId = WeixinUtil.getWeixinOpenId(code, state);
	// putToRedis(openId);
	// // response.sendRedirect(UrlConstant.INDEX_URL + openId);
	// } catch (UtilityException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// private void putToRedis(String openId) {
	// redisService.set(openId, false);
	// }
	//
	// @RequestMapping(value = "/coupon")
	// public void coupon(HttpServletRequest request,
	// HttpServletResponse response, Model model) throws IOException {
	// String code = request.getParameter("code");
	// String state = request.getParameter("state");
	//
	// //获取用户信息
	// String openId;
	// try {
	// openId = WeixinUtil.getWeixinOpenId(code, state);
	// putToRedis(openId);
	// // response.sendRedirect(UrlConstant.COUPON_URL + openId);
	// } catch (UtilityException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// @RequestMapping(value = "/test")
	// public void test(HttpServletRequest request,
	// HttpServletResponse response, Model model) throws IOException {
	// String code = request.getParameter("code");
	// String state = request.getParameter("state");
	//
	// //获取用户信息
	// String openId;
	// try {
	// openId = WeixinUtil.getWeixinOpenId(code, state);
	// response.getWriter().write(openId);
	// } catch (UtilityException e) {
	// e.printStackTrace();
	// }
	// }

	@RequestMapping(value = "/createWxMenu", method = { RequestMethod.POST })
	public void createWxMenu(@RequestBody Menu menu) {
		WeixinUtil.createMenu(menu);
	}

	@RequestMapping(value = "/getShortUrl", method = { RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getShortUrl(@RequestParam("url") String url) {
		Map<String, Object> map = WeixinUtil.getShortUrl(url);

		return map;
	}
}
