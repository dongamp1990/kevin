package org.alipp.api.services;

import java.util.List;

import org.alipp.api.dao.AdminDao;
import org.alipp.api.domain.CustomerInfo;
import org.alipp.api.domain.LevelCode;
import org.alipp.api.domain.User;
import org.alipp.api.exception.BizException;
import org.alipp.api.util.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.plugin.activerecord.Page;

public class AdminService extends BaseService{
	private static Logger logger = LoggerFactory.getLogger(AdminService.class);
	public User getUserByLoginName(String userName){
		try {
			User user = AdminDao.getUserByLoginName(userName);
			return user;
		} catch (Exception e) {
			logger.error("getUserByLoginName Error", e);
			throw new BizException(e);
		}
	}
	public Page<CustomerInfo> getCustomerInfoPage(Integer currentPage,
			Integer pageSize, String qtName) {
		try {
			Page<CustomerInfo> page = AdminDao.getCustomerInfoPage(currentPage, pageSize, qtName);
			return page;
		} catch (Exception e) {
			logger.error("getCustomerInfoPage Error", e);
			throw new BizException(e);
		}
	}
	
	public void saveCustomerInfo(CustomerInfo customerInfo) {
		try {
			Boolean success = AdminDao.saveCustomerInfo(customerInfo);
		} catch (Exception e) {
			logger.error("saveCustomerInfo Error", e);
			throw new BizException(e);
		}
	}
	public void deleteCustomerInfoById(Integer id) {
		try {
			CustomerInfo.dao.deleteById(id);
		} catch (Exception e) {
			logger.error("deleteCustomerInfoById Error", e);
			throw new BizException(e);
		}
	}
	public Page<User> getUserPage(Integer currentPage, Integer pageSize,
			String userName) {
		try {
			Page<User> page = AdminDao.getUserPage(currentPage, pageSize, userName);
			return page;
		} catch (Exception e) {
			logger.error("getUserPage Error", e);
			throw new BizException(e);
		}
	}
	public void saveUser(User user) {
		try {
			Boolean success = AdminDao.saveUser(user);
		} catch (Exception e) {
			logger.error("saveUser Error", e);
			throw new BizException(e);
		}
	}
	public void deleteUserById(Integer id) {
		try {
			User.dao.deleteById(id);
		} catch (Exception e) {
			logger.error("deleteUserById Error", e);
			throw new BizException(e);
		}
	}
	public User getUserById(Integer id) {
		try {
			User user = AdminDao.getUserById(id);
			return user;
		} catch (Exception e) {
			logger.error("getUserById Error", e);
			throw new BizException(e);
		}
	}
	public void updateUser(User user) {
		try {
			User u = getUserById(user.getInt("user_id"));
			if (u != null) {
				if (!u.get("password").equals(user.get("password"))) {
					user.set("password", Encoder.encryptSHA(user.getStr("password")));
				}
			}
			AdminDao.updateUser(user);
		} catch (Exception e) {
			logger.error("updateUser Error", e);
			throw new BizException(e);
		}
	}
	
	public CustomerInfo getCustomerInfoById(Integer id) {
		try {
			CustomerInfo customerInfo = AdminDao.getCustomerInfoById(id);
			return customerInfo;
		} catch (Exception e) {
			logger.error("getCustomerInfoById Error", e);
			throw new BizException(e);
		}
	}
	
	public void updateCustomerInfo(CustomerInfo customerInfo) {
		try {
			AdminDao.updateCustomerInfo(customerInfo);
		} catch (Exception e) {
			logger.error("updateCustomerInfo Error", e);
			throw new BizException(e);
		}
	}
	public List<LevelCode> getAllLevelCode() {
		try {
			return AdminDao.getAllLevelCode();
		} catch (Exception e) {
			logger.error("getAllLevelCode Error", e);
			throw new BizException(e);
		}
	}
	public CustomerInfo getCustomerInfoByQQNumber(String qqNumber) {
		try {
			return AdminDao.getCustomerInfoByQQNumber(qqNumber);
		} catch (Exception e) {
			logger.error("getCustomerInfoByQQNumber Error", e);
			throw new BizException(e);
		}
	}
}
