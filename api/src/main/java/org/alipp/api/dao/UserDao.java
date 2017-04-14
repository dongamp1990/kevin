package org.alipp.api.dao;

import java.util.List;

import org.alipp.api.domain.User;

public class UserDao {
	private static final User dao = new User();
	
	public static User getUserById(Long userId) {
		return dao.findById(userId);
	}
	
	public static User getUserByLoginName(String userName) {
		List<User> users = User.dao.find("select * from user where login_name = ? ", userName);
		return users == null ? null : users.isEmpty() ? null : users.get(0);
	}
}
