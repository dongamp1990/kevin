package org.kevin.aop;

import java.lang.reflect.Method;

import org.kevin.common.Common;
import org.kevin.util.JSONUtil;
import org.springframework.aop.MethodBeforeAdvice;

public class ControllerRequestLog implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		StringBuffer param = new StringBuffer();
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i ++) {
				param.append("Param").append(i + 1).append(": ").append(JSONUtil.objectToJson(args[i]));
			}
		}
		Common.LOG.info("调用: {}, Params: {}", 
				new Object[]{method.getName(), param.toString()});
	}
	
}
