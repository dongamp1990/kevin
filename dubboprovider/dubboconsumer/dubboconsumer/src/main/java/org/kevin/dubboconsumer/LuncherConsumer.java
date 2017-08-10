package org.kevin.dubboconsumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.kevin.dubboapi.DemoService;
import org.kevin.dubboapi.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LuncherConsumer {
	public static void main(String[] args) throws InterruptedException, IOException {
		LuncherConsumer luncher = new LuncherConsumer();
		luncher.start();
		System.in.read();
	}	

	void start() {
		PropertyConfigurator.configure("src/main/resources/config/log4j.properties");
		String configLocation = "spring/dubbo-consumer.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				configLocation);
		context.start();
		String[] names = context.getBeanDefinitionNames();
		System.out.print("Beans:");
		for (String string : names) {
			System.out.print(string); 
			
			System.out.print(",");
		}
		System.out.println();
		DemoService ds = (DemoService) context.getBean("demoService");
//		System.out.println("================== call remote result :" + ds.getWord());
//		ds.saveParam("jack tom jreey...");
//		CallbackService callbackService = (CallbackService) context.getBean("callbackService");
//		callbackService.addListener("127.0.0.01", new CallbackListener() {
//			public void changed(String msg) {
//				System.out.println("收到回调信息: " + msg);
//			}
//		});
		List<User> list = new ArrayList<User>();
		User user = new User();
		user.setAge(1);
		user.setName("aaa");
		list.add(user);
		user = new User();
		user.setAge(2);
		user.setName("bbb");
		list.add(user);
		ds.saveUser(list);
		
		List<User> list2 = ds.getUserList();
		for (User user2 : list2) {
			System.out.println("user_name: " + user2.getName() + " age: " + user2.getAge());
		}
	}
}
