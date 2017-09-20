package org.kevin.qiangshouji;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class XiaoMiThread extends Thread {
	
	private String productUrl;
	
	/**
	 * @param name 线程名称
	 * @param url 商品页面url
	 */
	public XiaoMiThread(String name, String url) {
		setName(name);
		this.productUrl = url;
	}

	public void run() {
		// 小米官网抢购红米note3页面
		// 小米官网登录信息
		String baseurl2 = "https://account.xiaomi.com/pass/serviceLogin?callback=http%3A%2F%2Forder.mi.com%2Flogin%2Fcallback%3Ffollowup%3Dhttp%253A%252F%252Fitem.mi.com%252Fbuyphone%252Fnote3%26sign%3DZmYyZmU3NmE0NWNmMjZkYzk5YmU5ZWJjMzc2Zjg1YzNhMjY3NTJjMg%2C%2C&sid=mi_eshop";
		WebElement loginElement = null;
		List<WebElement> element = null;
		List<WebElement> taocan = null;
		WebElement btn = null;
		WebDriver driver = new ChromeDriver();
		System.out.println("starting:");
		driver.get(baseurl2);
		driver.findElement(By.id("username")).sendKeys("18520869180"); // 输入自己小米官网登录账号
		driver.findElement(By.id("pwd")).sendKeys("aa12530123"); // 输入小米官网登录密码
		driver.findElement(By.id("login-button")).click();
		System.out.println(driver.getCurrentUrl());

		try {
			sleep(8000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		driver.navigate().to(productUrl);
		// 产品版本
		element = driver.findElements(By.id("J_list"));
		if (element == null || element.size() == 0) {
			driver.close();
		}
		try {
			WebElement verEle = element.get(0);
			List<WebElement> verEleChiled = verEle.findElements(By
					.className("btn-biglarge"));
			verEleChiled.get(1).click();
		} catch (Exception e) {
			System.out.println("选版本未成功~~");
		}
		while (true) {
			try {
				Thread.sleep(200L);
				// 版本
//				System.out.println("选择版本...");
				//点击购买
				List<WebElement> bytBtnBox = driver.findElements(By.id("J_buyBtnBox"));
				bytBtnBox.get(0).findElement(By.id("J_proAddcart")).click();
				System.out.println("抢购成功并且已下单！！");
//				break;
			} catch (Exception e) {
				System.out.println("未抢购成功~~");
			}
		}
	}
}
