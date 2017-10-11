package org.kevin.config;

import org.kevin.filter.RequestLogginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
	/**
	 * 配置过滤器
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean someFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(requestLogginFilter());
		registration.addUrlPatterns("/*");
		// registration.addInitParameter("paramName", "paramValue");
		registration.setName("commonsRequestLoggingFilter");
		return registration;
	}
	
//	@Bean
//	public FilterRegistrationBean responseFilterRegistration() {
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter(new ResponseLogginFilter());
//		registration.addUrlPatterns("/*");
//		// registration.addInitParameter("paramName", "paramValue");
//		registration.setName("responseLogginFilter");
//		return registration;
//	}

	@Bean
	public RequestLogginFilter requestLogginFilter() {
		return new RequestLogginFilter();
	}
}
