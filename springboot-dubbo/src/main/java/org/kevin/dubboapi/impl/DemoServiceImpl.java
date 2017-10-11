package org.kevin.dubboapi.impl;

import java.util.List;

import org.kevin.dao.UserDAO;
import org.kevin.dubboapi.domain.User;
import org.kevin.dubboapi2.DemoService;
import org.kevin.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("demoService")
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	private UserDAO userDAO;
	
	public void sayHi() {
		System.out.println("caall my sayhi method");
	}

	public String getWord() {
		return "Hello, i'm service provider, time = " + System.currentTimeMillis();
	}

	public Integer saveParam(String param) {
		System.out.println("server provider receive param: " + param);
		return 1;
	}

	public List<User> getUserList() {
		try {
			return userDAO.getUserList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveUser(List<User> users) {
		for (User user : users) {
			System.out.println("user_name: " + user.getName() + " age: " + user.getAge());
		}
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long saveUser(User user) throws Exception {
		try {
			Long id = this.userDAO.saveUser(user);
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("ServiceException", "S01", "saveUser Error");
		}
	}
}
