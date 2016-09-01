package org.kevin.kafka;

public class KafkaProperties {
	public final static String zkConnect = "192.168.177.199:2181";
	public final static String groupId = "group1";
	public final static String topic = "test";
	public final static String kafkaServerURL = "192.168.177.199";
	public final static int kafkaServerPort = 9092;
	public final static int kafkaProducerBufferSize = 64 * 1024;
	public final static int connectionTimeOut = 20000;
	public final static int reconnectInterval = 10000;
	public final static String topic2 = "topic2";
	public final static String topic3 = "topic3";
	public final static String clientId = "SimpleConsumerDemoClient";
}
