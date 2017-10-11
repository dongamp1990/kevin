package org.kevin.qiangshouji;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JDThread extends Thread {
	
	private String productUrl;
	
	/**
	 * @param name 线程名称
	 * @param url 商品页面url
	 */
	public JDThread(String name, String url) {
		setName(name);
		this.productUrl = url;
	}

	public void run() {
		// 小米官网抢购红米note3页面
		// 小米官网登录信息
		String baseurl2 = "https://passport.jd.com/new/login.aspx?";
		WebElement loginElement = null;
		List<WebElement> element = null;
		List<WebElement> taocan = null;
		WebElement btn = null;
		WebDriver driver = new ChromeDriver();
		System.out.println("starting:");
		driver.get(baseurl2);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/div[2]/a")).click();
		driver.findElement(By.id("loginname")).sendKeys("18520869180"); // 输入自己小米官网登录账号
		driver.findElement(By.id("nloginpwd")).sendKeys("dong478106"); // 输入小米官网登录密码
		driver.findElement(By.className("login-btn")).click();
		System.out.println(driver.getCurrentUrl());

		try {
			sleep(8000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		driver.navigate().to(productUrl);
		while (true) {
			try {
				driver.findElements(By.id("choose-btn-ko")).get(0).click();
				Thread.sleep(500L);
				driver.findElements(By.id("saveConsigneeTitleDiv")).get(0).click();
				Thread.sleep(500L);
				driver.findElements(By.id("order-submit")).get(0).click();
				Thread.sleep(500L);
				if (driver.findElement(By.id("tryBtn")) != null) {
					System.out.println("已售罄，抢购失败。");
					driver.close();
					System.exit(1);
				}
				System.out.println("抢购成功并且已下单！！");
				break;
			} catch (Exception e) {
				driver.navigate().to(productUrl);
				System.out.println("未抢购成功~~");
			}
		}
	}
	
	public static void main(String[] args) {
		 new JDThread("JD2", "https://item.jd.com/5001213.html").start();
	}
}
