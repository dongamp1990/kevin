package org.kevin.dao;

import java.util.List;

import org.kevin.dubboapi.domain.User;
import org.kevin.exception.DaoException;

public interface TestDAO {

	List<User> getUserList() throws DaoException;

	Long saveUser(User user) throws DaoException;
}
