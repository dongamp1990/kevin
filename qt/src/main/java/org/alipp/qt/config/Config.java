package org.alipp.qt.config;

import org.alipp.qt.controller.AdminController;
import org.alipp.qt.controller.IndexController;
import org.alipp.qt.domain.CustomerInfo;
import org.alipp.qt.domain.LevelCode;
import org.alipp.qt.domain.User;
import org.alipp.qt.interceptor.AuthInterceptor;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

public class Config extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		me.setViewType(ViewType.JSP);
		me.setDevMode(false);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/", IndexController.class);
		me.add("/admin/", AdminController.class, "WEB-INF/admin/");
	}

	@Override
	public void configPlugin(Plugins me) {
		loadPropertyFile("jdbc.properties");
		C3p0Plugin cp = new C3p0Plugin(getProperty("jdbc_url"), getProperty("db_user"), getProperty("db_password"));
		me.add(cp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp); me.add(arp);
		arp.addMapping("user", "user_id", User.class);
		arp.addMapping("level_code", "code_id", LevelCode.class);
		arp.addMapping("customer_info", CustomerInfo.class); 
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.addGlobalActionInterceptor(new AuthInterceptor()); //检查是否登录拦截器
	}

	@Override
	public void configHandler(Handlers me) {

	}
	
	

}
