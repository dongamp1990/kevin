package org.kevin.spark.learning;

import java.util.List;
import java.util.function.Consumer;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.storage.StorageLevel;
import org.kevin.domain.Order;
import org.kevin.domain.User;

public class SparkSqlTest {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("SparkSqlTest");
		JavaSparkContext context = new JavaSparkContext(conf);
		JavaRDD<User> userRDD = context.textFile(args[0]).map((Function<String, User>) x -> {
			String[] dataArr = x.split(" "); 
//			ID, 性别, 年龄, 注册日期, 角色 (从事行业), 所在区域)
			User u = new User();
			u.setUserId(dataArr[0]);
			u.setGender(dataArr[1]);
			u.setAge(Integer.parseInt(dataArr[2]));
			u.setRegisterDate(dataArr[3]);
			u.setRole(dataArr[4]);
			u.setRegion(dataArr[5]);
			return u;
		});
		SQLContext sqlContext = new SQLContext(context);
		DataFrame userDF = sqlContext.createDataFrame(userRDD, User.class);
		userDF.registerTempTable("user");
		JavaRDD<Order> orderRDD = context.textFile(args[1]).map((Function<String, Order>) x -> {
			String[] dataArr = x.split(" "); 
//			交易单号, 交易日期, 产品种类, 价格, 用户 ID
			Order u = new Order();
			u.setOrderId(dataArr[0]);
			u.setOrderDate(dataArr[1]);
			u.setProductId(Integer.parseInt(dataArr[2]));
			u.setPrice(Double.parseDouble(dataArr[3]));
			u.setUserId(dataArr[4]);
			return u;
		});
		DataFrame orderDF = sqlContext.createDataFrame(orderRDD, Order.class);
		orderDF.registerTempTable("orders");
		//持久化到内存
//		userDF.persist(StorageLevel.MEMORY_ONLY());
//		orderDF.persist(StorageLevel.MEMORY_ONLY());
		//过滤统计 2015年来消费的用户数量
		long count = orderDF.filter(orderDF.col("orderDate").contains("2015")).join(userDF, orderDF.col("userId").equalTo(userDF.col("userId"))).count();
		long orderCount2014 = sqlContext.sql("select * from orders where orderDate like '2014%' ").count();
		DataFrame priceDF = sqlContext.sql("SELECT max(o.price) as maxPrice, min(o.price) as minPrice,avg(o.price) as avgPrice,u.userId "
										 + "FROM orders o, user u where u.userId = 10 and u.userId = o.userId group by u.userId");
		List<Row> rows = priceDF.collectAsList();
		int i = 0;
		System.out.println("2015年来消费的用户数量: " + count);
		System.out.println("2014年订单总数:" + orderCount2014);
		System.out.println("priveDF size = " + rows.size());
		if (rows != null && rows.size() > 0) {
			for (Row row : rows) {
				System.out.println("maxPrice = " + row.getDouble(0));
				System.out.println("minPrice = " + row.getDouble(1));
				System.out.println("avgPrice = " + row.getDouble(2));
				System.out.println("userID = " + row.getString(3));
				if (i >= 10) {
					break;
				}
			}
		}
		context.stop();
	}
}
