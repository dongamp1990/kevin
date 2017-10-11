package org.kevin.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.kevin.common.Common;
import org.kevin.util.JSONUtil;

public class ControllerResponseResultLog {

	public Object printResultLog(ProceedingJoinPoint joinpoint) throws Throwable {
		Object[] args = joinpoint.getArgs();
		StringBuffer param = new StringBuffer();
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i ++) {
				param.append("Param").append(i + 1).append(": ").append(JSONUtil.objectToJson(args[i]));
			}
		}
		Object reslut = joinpoint.proceed();
		// 这里是记录日志的
		joinpoint.getArgs();
		Common.LOG.info("Call: {}, Params: {}, Response: {}", 
				new Object[]{joinpoint.getSignature().toShortString(), param.toString(), JSONUtil.objectToJson(reslut)});
		return reslut;
	}
}
