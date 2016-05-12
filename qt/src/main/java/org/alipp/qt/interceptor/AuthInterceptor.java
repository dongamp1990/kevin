package org.alipp.qt.interceptor;

import org.alipp.qt.domain.User;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class AuthInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		User user = inv.getController().getSessionAttr("LOGIN_USER");
		if (user == null) {
			inv.getController().redirect("/admin/login");
			return;
		}
		inv.invoke();
	}

}
