package org.alipp.api.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.alipp.api.domain.CustomerInfo;
import org.alipp.api.domain.LevelCode;
import org.alipp.api.domain.User;
import org.alipp.api.services.AdminService;
import org.alipp.api.util.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

public class AdminController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	private AdminService adminservice;
	
	@ActionKey("/admin")
	public void main() {
//		render("data_manager.jsp");
		redirect("/admin/data_manager");
		logger.info("admin");
	}
	
	@ActionKey("/admin/data_manager")
	public void dataManager() {
//		render("index.jsp");
		Integer currentPage = getPara("currentPage") == null ? 1 : getParaToInt("currentPage");
		Integer pageSize = getPara("pageSize") == null ? 10 : getParaToInt("pageSize");
		
		String qtName = null;
		try {
			qtName = getPara("qtName") == null ? null : new String(getPara("qtName").getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//查询数据
		Page<CustomerInfo> customerPage = adminservice.getCustomerInfoPage(currentPage, pageSize, qtName);
		setAttr("customerPage", customerPage);
		setAttr("qtName", qtName);
		setAttr("pageSize", pageSize);
		setAttr("currentPage", currentPage);
		render("data_manager.jsp");
	}
	
	@ActionKey("/admin/form")
	public void form() {
		render("form.jsp");
	}
	
	@Clear
	@ActionKey("/admin/login")
	public void login() {
		render("login.jsp");
	}
	
	@ActionKey("/admin/logout")
	public void logout() {
		setSessionAttr("LOGIN_USER", null);
		redirect("/admin/login");
	}
	
	@Clear
	@ActionKey("/admin/loginSubmit")
	public void loginSubmit() {
		//登录
		String userName = getPara("username");
		String password = getPara("password");
		if (userName == null || password == null) {
			setAttr("errMsg", "登录名或密码错误");
			render("login.jsp");
			return;
		}
		
		User u = adminservice.getUserByLoginName(userName);
		if (u == null) {
			setAttr("errMsg", "登录名或密码错误");
			render("login.jsp");
			return;
		}
		
		if (!Encoder.encryptSHA(password).equals(u.get("password"))) {
			setAttr("errMsg", "登录名或密码错误");
			render("login.jsp");
			return;
		}
		setSessionAttr("LOGIN_USER", u);
		redirect("/admin/data_manager");
	}
	
	@ActionKey("/admin/articleDetail")
	public void articleDetail() {
		Integer articleId = getParaToInt("id");
		setAttr("articleId", articleId);
		render("articleDetail.jsp");
	}
	
	@ActionKey("/admin/data_manager_input")
	public void dataManagerInput() {
		//查询levelCode
		List<LevelCode> codes = adminservice.getAllLevelCode();
		setAttr("codes", codes);
		render("data_manager_input.jsp");
	}
	
	@ActionKey("/admin/data_manager_input_submit")
	@Before(Tx.class)
	public void saveCustomerInfo() {
		try {
			CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.set("qt_name", getPara("qtName"));
			customerInfo.set("qq_number", getPara("qqNumber"));
			customerInfo.set("area", getPara("area"));
			customerInfo.set("illegality_record", getPara("illegalityRecord"));
			customerInfo.set("level", getPara("level"));
			customerInfo.set("create_date", new Date());
			customerInfo.set("level_code", getPara("code"));
			customerInfo.set("phone_number", getPara("phoneNumber"));
			customerInfo.set("illegality_punish", getPara("illegalityPunish"));
			
			adminservice.saveCustomerInfo(customerInfo);
		} catch (Exception e) {
			render("500.jsp");
			return;
		}
		redirect("/admin/data_manager");
	}
	
	@ActionKey("/admin/404.jsp")
	public void err404(){
		render("404.jsp");
	}
	
	@ActionKey("/admin/delete_customer_info")
	@Before(Tx.class)
	public void deleteCustomerInfo(){
		adminservice.deleteCustomerInfoById(getParaToInt("id"));
		renderJson("{\"status\":\"ok\"}");
	}
	
	@ActionKey("/admin/user_manager")
	public void userManager() {
		Integer currentPage = getPara("currentPage") == null ? 1 : getParaToInt("currentPage");
		Integer pageSize = getPara("pageSize") == null ? 10 : getParaToInt("pageSize");
		String userName = null;
		try {
			userName = getPara("userName") == null ? null : new String(getPara("userName").getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//查询数据
		Page<User> userPage = adminservice.getUserPage(currentPage, pageSize, userName);
		setAttr("userPage", userPage);
		setAttr("userName", userName);
		setAttr("pageSize", pageSize);
		setAttr("currentPage", currentPage);
		render("user_manager.jsp");
	}
	
	@ActionKey("/admin/user_manager_input")
	public void userManagerInput() {
		render("user_manager_input.jsp");
	}
	
	@ActionKey("/admin/update_user_manager_input")
	public void updateUserManagerInput() {
		Integer id = getParaToInt("id");
		User u = adminservice.getUserById(id);
		setAttr("user", u);
		render("update_user_manager_input.jsp");
	}
	
	@ActionKey("/admin/update_data_manager_input")
	public void updateDataManagerInput() {
		Integer id = getParaToInt("id");
		CustomerInfo customerInfo = adminservice.getCustomerInfoById(id);
		//查询levelCode
		List<LevelCode> codes = adminservice.getAllLevelCode();
		setAttr("codes", codes);
		setAttr("customerInfo", customerInfo);
		render("update_data_manager_input.jsp");
	}
	
	@ActionKey("/admin/update_data_manager_input_submit")
	@Before(Tx.class)
	public void updateCustomerInfo() {
		try {
			CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.set("id", getPara("id"));
			customerInfo.set("qt_name", getPara("qtName"));
			customerInfo.set("qq_number", getPara("qqNumber"));
			customerInfo.set("area", getPara("area"));
			customerInfo.set("illegality_record", getPara("illegalityRecord"));
			customerInfo.set("level", getPara("level"));
			customerInfo.set("level_code", getPara("code"));
			customerInfo.set("phone_number", getPara("phoneNumber"));
			customerInfo.set("illegality_punish", getPara("illegalityPunish"));
			adminservice.updateCustomerInfo(customerInfo);
		} catch (Exception e) {
			render("500.jsp");
			return;
		}
		redirect("/admin/data_manager");
	}
	
	@ActionKey("/admin/update_user_manager_input_submit")
	@Before(Tx.class)
	public void updateUser() {
		try {
			User user = new User();
			user.set("login_name", getPara("loginName"));
			user.set("user_name", getPara("userName"));
			user.set("password", getPara("password"));
			user.set("user_id", getParaToInt("userId"));
			adminservice.updateUser(user);
		} catch (Exception e) {
			render("500.jsp");
			return;
		}
		redirect("/admin/user_manager");
	}
	
	@ActionKey("/admin/user_manager_input_submit")
	@Before(Tx.class)
	public void saveUser() {
		try {
			User user = new User();
			user.set("login_name", getPara("loginName"));
			user.set("user_name", getPara("userName"));
			user.set("password", Encoder.encryptSHA(getPara("password")));
			user.set("create_date", new Date());
			adminservice.saveUser(user);
		} catch (Exception e) {
			render("500.jsp");
			return;
		}
		redirect("/admin/user_manager");
	}
	
	@ActionKey("/admin/delete_user")
	@Before(Tx.class)
	public void deleteUser(){
		adminservice.deleteUserById(getParaToInt("id"));
		renderJson("{\"status\":\"ok\"}");
	}
	
	@ActionKey("/admin/check_login_name")
	public void checkLoginName(){
		String loginName = getPara("loginName");
		User u = adminservice.getUserByLoginName(loginName);
		if (u != null) {
			renderText("false");
		}else {
			renderText("true");
		}
	}
	
	@ActionKey("/admin/check_qq_number")
	public void checkQQNumber(){
		String qqNumber = getPara("qqNumber");
		String oldQQNumber = getPara("oldQQNumber");
		if (qqNumber.equals(oldQQNumber)) {
			renderText("true");
			return;
		}
		CustomerInfo u = adminservice.getCustomerInfoByQQNumber(qqNumber);
		if (u != null) {
			renderText("false");
		}else {
			renderText("true");
		}
	}
}
