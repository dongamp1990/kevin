package org.kevin.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kevin.dao.UserDAO;
import org.kevin.das.jdbc.impl.AbstractJdbcDaoImpl;
import org.kevin.dubboapi.domain.User;
import org.kevin.exception.BaseException;
import org.kevin.exception.DaoException;
import org.kevin.util.BeanUtil;
import org.kevin.util.QueryDefinitionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl extends AbstractJdbcDaoImpl implements UserDAO {
	@Autowired
	private QueryDefinitionBean namedQueryBean;
	
	@Autowired
	public UserDAOImpl(JdbcTemplate jdbcTemplate) {
		setJdbcTemplate(jdbcTemplate);
	}
	
	@Override
	public List<User> getUserList() throws DaoException {
		String sql = null;
		try {
			sql = namedQueryBean.getQueryByName("GET_USER_LIST");
		} catch (BaseException e) {
			e.printStackTrace();
			throw new DaoException(e.getExceptionType(), e.getErrCd(), e.getErrMsg());
		}
		List<Map<String, Object>> res = this.executeJdbcQuery(sql, null);
		List<User> list = new ArrayList<User>();
		for (Map<String, Object> map : res) {
			list.add(BeanUtil.convertEntity(map, User.class));
		}
		return list;
	}
	
	@Override
	public Long saveUser(User user) throws DaoException {
		String sql = null;
		try {
			sql = namedQueryBean.getQueryByName("SAVE_USER");
		} catch (BaseException e) {
			e.printStackTrace();
			throw new DaoException(e.getExceptionType(), e.getErrCd(), e.getErrMsg());
		}
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", user.getName());
		this.executeJdbcUpdate(sql, param, keyHolder);
		return (Long) keyHolder.getKey();
	}
}
