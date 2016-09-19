package org.kevin.spark.learning;

import java.util.ArrayList;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class FilterLog {
	public static void main(String[] args) {
		if (args == null || args.length < 1) {
			System.out.println("useag FilterLog <file>");
			System.exit(0);
		}
		SparkConf conf = new SparkConf().setMaster("spark://master:7077").setAppName("logFilter");
		JavaSparkContext context = new JavaSparkContext(conf);
		JavaRDD<String> logRDD = context.textFile(args[0]);
		JavaRDD<String> errRDD = logRDD.filter((Function<String, Boolean>) x -> x.contains("ERROR"));
		JavaRDD<String> infoRDD = logRDD.filter((Function<String, Boolean>) x -> x.contains("INFO"));
		errRDD.saveAsTextFile("hdfs://master:9000/user/logFilter/errLog" + System.currentTimeMillis());
		infoRDD.saveAsTextFile("hdfs://master:9000/user/logFilter/infoLog" + System.currentTimeMillis());
		context.parallelize(new ArrayList<String>());
		context.stop();
	}
}
