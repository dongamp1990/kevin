package org.alipp.qt.dao;

import java.util.List;

import org.alipp.qt.domain.CustomerInfo;
import org.alipp.qt.domain.LevelCode;
import org.alipp.qt.domain.User;
import org.alipp.qt.util.StringUtil;

import com.jfinal.plugin.activerecord.Page;

public class AdminDao {
	
	public static Page<CustomerInfo> getCustomerInfoPage(Integer currentPage, Integer pageSize, String qqNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append("from customer_info where 1 = 1 ");
		if (StringUtil.isNotBlank(qqNumber)) {
			sql.append(" and qq_number like ");
			sql.append("'%").append(qqNumber).append("%'");
		}
		Page<CustomerInfo> page = CustomerInfo.dao.paginate(currentPage, pageSize, "select * ", sql.toString());
		return page;
	}

	public static User getUserByLoginName(String userName) {
		List<User> users = User.dao.find("select * from user where login_name = ? ", userName);
		return users == null ? null : users.isEmpty() ? null : users.get(0);
	}

	public static Boolean saveCustomerInfo(CustomerInfo customerInfo) {
		return customerInfo.save();
	}

	public static Page<User> getUserPage(Integer currentPage, Integer pageSize,
			String userName) {
		StringBuilder sql = new StringBuilder();
		sql.append("from user where 1 = 1 ");
		if (StringUtil.isNotBlank(userName)) {
			sql.append(" and user_name like ");
			sql.append("'%").append(userName).append("%'");
		}
		Page<User> page = User.dao.paginate(currentPage, pageSize, "select * ", sql.toString());
		return page;
	}

	public static Boolean saveUser(User user) {
		return user.save();
	}

	public static User getUserById(Integer id) {
		return User.dao.findById(id);
	}

	public static void updateUser(User user) {
		user.update();
	}

	public static CustomerInfo getCustomerInfoById(Integer id) {
		return CustomerInfo.dao.findById(id);
	}

	public static void updateCustomerInfo(CustomerInfo customerInfo) {
		customerInfo.update();	
	}

	public static List<LevelCode> getAllLevelCode() {
		return LevelCode.dao.find("select * from level_code ");
	}

	public static CustomerInfo getCustomerInfoByQQNumber(String qqNumber) {
		List<CustomerInfo> infos = CustomerInfo.dao.find("select * from customer_info where qq_number = ? ", qqNumber);
		if (infos == null || infos.isEmpty()) {
			return null;
		}
		return infos.get(0);
	}
}
