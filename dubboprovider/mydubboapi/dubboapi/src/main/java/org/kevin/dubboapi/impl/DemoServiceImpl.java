package org.kevin.dubboapi.impl;

import java.util.ArrayList;
import java.util.List;

import org.kevin.dubboapi.DemoService;
import org.kevin.dubboapi.domain.User;

public class DemoServiceImpl implements DemoService {

	public void sayHi() {
		System.out.println("caall my sayhi method");
	}

	public String getWord() {
		return "Hello, i'm service provider";
	}

	public Integer saveParam(String param) {
		System.out.println("server provider receive param: " + param);
		return 1;
	}

	public List<User> getUserList() {
		List<User> list = new ArrayList<User>();
		User user = new User();
		user.setAge(1);
		user.setName("tom");
		list.add(user);
		user = new User();
		user.setAge(2);
		user.setName("jack");
		list.add(user);
		return list;
	}
	
	
	public void saveUser(List<User> users) {
		for (User user : users) {
			System.out.println("user_name: " + user.getName() + " age: " + user.getAge());
		}
	}
	
	
}
