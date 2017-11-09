package com.alipp.weixin.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@SuppressWarnings("deprecation")
	@RequestMapping("/index")
	@ResponseBody
	public String index() {
		Date date = new Date();
		return "{\"status\":\"working\",\"date\":\"" + date.toLocaleString() + "\"}";
	}

}
