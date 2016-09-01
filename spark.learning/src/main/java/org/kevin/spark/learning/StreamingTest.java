package org.kevin.spark.learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.spark.HashPartitioner;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.api.java.function.VoidFunction2;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.kevin.kafka.KafkaProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;

import scala.Tuple2;

public class StreamingTest {
	private static Logger logger = LoggerFactory.getLogger("test");
	public static void main(String[] args) throws InterruptedException {
		long timemills = 5 * 1000;
		
		SparkConf conf = new SparkConf().setMaster("spark://192.168.177.199:7077").setAppName("streamingTest");
		JavaStreamingContext sc = new JavaStreamingContext(conf, new Duration(timemills));
		sc.checkpoint("hdfs://master:9000/user/stream_test");
//		sc.checkpoint("/opt/stream_test");
		Map<String, Integer> topicMap = new HashMap<>();
		topicMap.put("test2", 1);
		//从kafka构建stream
		JavaPairReceiverInputDStream<String, String> reviveStream = KafkaUtils.createStream(sc, KafkaProperties.zkConnect, 
				KafkaProperties.groupId, topicMap);
		//取得string stream, 第二个参数是kafka的msg内容
		JavaDStream<String> msgStream = reviveStream.map((Function<Tuple2<String,String>, String>) tuple -> {
			logger.info("收到的kafka消息 = {}", tuple._2);
//			logger.info("@@1 = {}", tuple._1);
//			logger.info("@@2 = {}", tuple._2);
			return tuple._2;
		});
//		对于某一个访问论坛的用户，我们需要对他的行为数据做一个抽象，以便于解释网页话题热度的计算过程。
//		首先，我们通过一个向量来定义用户对于某个网页的行为即点击的网页，停留时间，以及是否点赞，可以表示如下：
//		(page001.html, 1, 0.5, 1)
//		向量的第一项表示网页的 ID，第二项表示从进入网站到离开对该网页的点击次数，第三项表示停留时间，以分钟为单位，第四项是代表是否点赞，1 为赞，-1 表示踩，0 表示中立。
//		其次，我们再按照各个行为对计算网页话题热度的贡献，给其设定一个权重，在本文中，我们假设点击次数权重是 0.8，因为用户可能是由于没有其他更好的话题，所以再次浏览这个话题。停留时间权重是 0.8，因为用户可能同时打开多个 tab 页，但他真正关注的只是其中一个话题。是否点赞权重是 1，因为这一般表示用户对该网页的话题很有兴趣。
//		最后，我们定义用下列公式计算某条行为数据对于该网页热度的贡献值。
//		f(x,y,z)=0.8x+0.8y+z
//		那么对于上面的行为数据 (page001.html, 1, 0.5, 1)，利用公式可得：
//		H(page001)=f(x,y,z)= 0.8x+0.8y+z=0.8*1+0.8*0.5+1*1=2.2
		//把msgStream计算出kv的DStream
		//取得k是page，v是score的kvStream
		JavaPairDStream<String, Double> scoreStream = msgStream.mapToPair(
			(PairFunction<String, String, Double>) str -> {
			logger.info("mapToPair的str = {}", str);
			if (str != null) {
				String[] arr = str.split("\\|");
				Double score =Double.parseDouble(arr[1]) * 0.8 +  Double.parseDouble(arr[2]) * 0.8+Double.parseDouble(arr[3]);
				return new Tuple2<String, Double>(arr[0], score);
			}else {
				return new Tuple2<>("", 0D);
			}
		});
		//初始化一个kvRDD
		JavaPairRDD<String, Double> initRdd = sc.sparkContext().parallelizePairs(new ArrayList<Tuple2<String, Double>>());
		
		// 对相同的Key，进行Value的累计（包括Local和Reducer级别同时Reduce）
		JavaPairDStream<String, Double> stateStream = scoreStream.updateStateByKey(
				(Function2<List<Double>, Optional<Double>, Optional<Double>>) (list, state) -> {
					Double updateValue = 0D;
					if (state.isPresent()) {
						updateValue = state.get();
					}
					for (Double value : list) {
						updateValue += value;
					}
					return Optional.of(updateValue);
				}, new HashPartitioner(sc.sparkContext().defaultMinPartitions()), initRdd);
		
		//定期检查checkpoint
		stateStream.checkpoint(new Duration(timemills));
		
		//遍历RDD
		stateStream.foreachRDD((VoidFunction<JavaPairRDD<String, Double>>) rdd -> {
			//转换成double的pairRdd再排序
			JavaPairRDD<Double, String> sortRDD = rdd.mapToPair(
					(PairFunction<Tuple2<String,Double>, Double, String>) t -> new Tuple2<>(t._2, t._1)).sortByKey(false);
			//取前面第10个，遍历打印
			sortRDD.take(10).forEach((Consumer<Tuple2<Double, String>>) x -> System.out.println(x._2 + ", score = " + x._1));
			sortRDD.saveAsTextFile("hdfs://master:9000/user/page_hotCount_out");
		});
		sc.start();
		sc.awaitTermination();
	}
}
	