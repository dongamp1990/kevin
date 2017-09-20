package org.kevin.dubboapi2;

import java.util.List;

import org.kevin.dubboapi.domain.User;

public interface DemoService {
	public void sayHi();

	public String getWord();

	public Integer saveParam(String param);

	public void saveUser(List<User> users);

	public List<User> getUserList();

	public Long saveUser(User user) throws Exception;

}
