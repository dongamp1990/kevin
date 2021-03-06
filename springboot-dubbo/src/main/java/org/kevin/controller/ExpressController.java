package org.kevin.controller;

import java.util.List;

import org.kevin.dubboapi.domain.User;
import org.kevin.dubboapi2.DemoService;
import org.kevin.response.GenericResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpressController {

	@Autowired
	private DemoService demoService;
	
//	@Reference(version = "1.0.0")
//	private OrderService orderService;
	
	@RequestMapping(path = "/express/user", method = {RequestMethod.POST}, consumes = "application/json", produces = "application/json")
	public GenericResponseVO saveUser(@RequestBody User user) {
		try {
			Long id = demoService.saveUser(user);
			return new GenericResponseVO(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new GenericResponseVO("fail");
	}
	
	@RequestMapping(path = "/express/user", method = {RequestMethod.GET}, produces = "application/json")
	public GenericResponseVO getUser() {
		List<User> userList = demoService.getUserList();
		return new GenericResponseVO(userList);
	}
	
	@RequestMapping(path = "/express/test2/{version}", method = {RequestMethod.GET}, produces = "application/json")
	public GenericResponseVO test2(@PathVariable Long version, @RequestParam String name) {
		System.out.println(version);
		System.out.println(name);
		return new GenericResponseVO("success");
	}
}
