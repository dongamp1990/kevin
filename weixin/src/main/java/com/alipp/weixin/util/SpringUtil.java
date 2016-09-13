package com.alipp.weixin.util;
/**
 *=====================================================================
 * ACP Spring Bean Loading Utility 
 *
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Provide relative services for spring.
 *
 */
public class SpringUtil {


	/**
	 * Initiate the spring context.
	 */
//	private static void init(){
//		if(ctx==null){
//			ctx = new ClassPathXmlApplicationContext(
//					new String[] { "classpath*:spring/**/applicationContext*.xml" });
//		}
//	}
	
	/**
	 * Get a bean through beanId manually. 
	 * 
	 * @param id
	 * @return
	 */
	public static Object getBeanById(String beanId){
		if(StringUtil.isBlank(beanId)){
			return null;
		}
		WebApplicationContext webApplCtx = ContextLoader.getCurrentWebApplicationContext();
		if (webApplCtx != null)
			return webApplCtx.getBean(beanId);
		return null;
	}
	
}
