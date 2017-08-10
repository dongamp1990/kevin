package org.kevin.dubboprovider;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LuncherProvider {

	void start() {
		PropertyConfigurator
				.configure("src/main/resources/config/log4j.properties");
		String configLocation = "spring/dubbo-provider.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				configLocation);
		context.start();
		String[] names = context.getBeanDefinitionNames();
		System.out.print("Beans:");
		for (String string : names)
			System.out.print(string + ",");
		System.out.println();
	}

	public static void main(String[] args) throws InterruptedException,
			IOException {
		LuncherProvider luncher = new LuncherProvider();
		luncher.start();
		System.in.read();// 为保证服务一直开着，利用输入流的阻塞来模拟  
	}
}
