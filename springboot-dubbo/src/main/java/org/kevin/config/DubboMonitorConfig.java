package org.kevin.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.MonitorConfig;

//@Configuration
//@ConfigurationProperties(prefix = "spring.dubbo")
//@EnableAutoConfiguration
public class DubboMonitorConfig {
	
	private MonitorConfig monitor;

	public MonitorConfig getMonitor() {
		return monitor;
	}

	public void setMonitor(MonitorConfig monitor) {
		this.monitor = monitor;
	}
	
	
	@Bean
	public MonitorConfig requestMonitorConfig() {
		return monitor;
	}
}
