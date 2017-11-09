package org.kevin.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.kevin.common.Common;
import org.kevin.util.JSONUtil;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOPConfig {
	// 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("execution(* org.kevin.controller.*.*(..))")
	public void aspect() {

	}

	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@Before("aspect()")
	public void before(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();

		StringBuffer param = new StringBuffer();
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				param.append("Param").append(i + 1).append(": ").append(JSONUtil.objectToJson(args[i])).append(" ");
			}
		}
		Common.LOG.info("收到请求: {}, 参数: {}", new Object[] {
				joinPoint.getSignature().toShortString(), param.toString() });
	}

	// 配置后置返回通知,使用在方法aspect()上注册的切入点
	//returning 的值绑定到方法同名参数中
	@AfterReturning(value = "aspect()", returning = "result")
	public Object around(JoinPoint joinPoint, Object result) throws Throwable {
		Object[] args = joinPoint.getArgs();
		StringBuffer param = new StringBuffer();
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i ++) {
				param.append("Param").append(i + 1).append(": ").append(JSONUtil.objectToJson(args[i])).append(" ");
			}
		}
		try {
			// 这里是记录日志的
			Common.LOG.info("请求: {}, 参数: {}, 返回结果: {}", 
					new Object[]{joinPoint.getSignature().toShortString(), param.toString(), JSONUtil.objectToJson(result)});
			return result;
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
}
