package org.kevin.qiangshouji;

public class JD {
	public static void main(String[] args) throws InterruptedException {
		  new JDThread("JD1", "https://item.jd.com/5001213.html").start();
		  Thread.sleep(10000L);
		  new JDThread("JD2", "https://item.jd.com/5001213.html").start();
	}
}
