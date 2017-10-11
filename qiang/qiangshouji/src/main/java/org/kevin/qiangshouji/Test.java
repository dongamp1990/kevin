package org.kevin.qiangshouji;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
	private static Map<String, Integer> bankMap = new HashMap<String, Integer>();

	static {
		bankMap.put("中国工商银行", 1);
		bankMap.put("中国建设银行", 3);
		bankMap.put("中国农业银行", 4);
		bankMap.put("招商银行", 5);
		bankMap.put("中国邮政储蓄银行", 6);
		bankMap.put("上海浦东发展银行", 8);
		bankMap.put("平安银行", 11);
		bankMap.put("中信银行", 13);
		bankMap.put("交通银行", 14);
		bankMap.put("中国民生银行", 15);
		bankMap.put("农村商业银行", 45);
		bankMap.put("上海农商银行", 81);
		bankMap.put("北京农商银行", 361);
	}

	public static void main(String[] args) throws InterruptedException {
		FileReader fr = null;
		BufferedReader br = null;
		int failCount = 0;
		try {
			int successCount = 0;
			int totalCount = 0;
			try {
				fr = new FileReader(new File("H:\\商户基础资料_2.csv"));
				br = new BufferedReader(fr);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("读取文件异常");
				return;
			}

			WebDriver driver = new ChromeDriver();
			String baseurl2 = "https://spay3.swiftpass.cn/spay/eleSign/eleSignUrl2Index?orgSign=3ae56ec78eb9e7d08475f784db54504b";
			String line = "";
			String[] arrs = null;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				try {
					arrs = line.split(",");
					String mchName = arrs[1];
					if (mchName.contains("作废")) {
						continue;
					}
					String formMchid = arrs[2];
					String formName = arrs[14];
					String bankName = arrs[15];
					Integer bankId = bankMap.get(bankName);
					String formCard = arrs[16];
					String formPhone = arrs[21];
					driver.navigate().to(baseurl2);
					System.out.println("starting:");
					driver.findElement(By.id("startSubmit")).click();
					Thread.sleep(300L);
					driver.findElement(By.id("formMchid")).sendKeys(formMchid);
					driver.findElement(By.id("formName")).sendKeys(formName);
					driver.findElement(By.id("formPhone")).sendKeys(formPhone);
					driver.findElement(By.id("formCard")).sendKeys(formCard);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript(" var ele = document.getElementById('formBank'); ele.setAttribute('data-id', '"+ bankId + "');");
					js.executeScript(" var ele = document.getElementById('formSubmit'); ele.setAttribute('class', 'btn btn_blue border b_all');");
					driver.findElement(By.id("formSubmit")).click();
					Thread.sleep(500L);
					//验证提示
					WebElement tipsEle = driver.findElement(By.id("tips-text"));
					if (tipsEle != null && tipsEle.getText() != null && !"".equals(tipsEle.getText())) {
						System.out.println("mchid = " + formMchid + " 失败，原因：" + tipsEle.getText());
						failCount ++;
						totalCount++;
						Thread.sleep(500L);
						continue;
					}
					driver.findElement(By.id("protocolSubmit")).click();
					Thread.sleep(400L);
					tipsEle = driver.findElement(By.id("tips-text"));
					if (tipsEle != null && tipsEle.getText() != null && !"".equals(tipsEle.getText())) {
						System.out.println("mchid = " + formMchid + " 失败，原因：" + tipsEle.getText());
						failCount ++;
						totalCount++;
						Thread.sleep(500L);
						continue;
					}
					
					WebElement element = driver.findElement(By
							.id("resultSubmit"));
					if (element != null) {
						System.out.println("mchid = " + formMchid + "成功");
						successCount ++;
					} else {
						System.out.println("mchid = " + formMchid + "失败");
						failCount ++;
					}
				} catch (Exception e) {
					System.out.println(line + " 失败");
					failCount ++;
				}
				totalCount++;
				Thread.sleep(500L);
			}
			System.out.println("成功：" +successCount);
			System.out.println("失败：" +failCount);
			System.out.println("总共：" +totalCount);
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
			failCount ++;
		}finally {
			try {
				if (fr != null) {
					fr.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
