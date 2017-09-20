package org.kevin.qiangshouji;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       new XiaoMiThread("1", "https://item.mi.com/product/10000070.html").start();
//       new XiaoMiThread("2", "https://item.mi.com/product/10000070.html").start();
//       new JDThread("2", "https://item.jd.com/5001213.html").start();
    }
}
