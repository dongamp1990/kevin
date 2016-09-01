package org.kevin.kafka;

import java.util.Properties;
import java.util.Random;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducer extends Thread {
	private final Producer<Integer, String> producer;
	private final String topic;
	private final Properties props = new Properties();

	public KafkaProducer(String topic) {
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("metadata.broker.list", "192.168.177.199:9092");
		producer = new kafka.javaapi.producer.Producer<Integer, String>(
				new ProducerConfig(props));
		this.topic = topic;
	}

	@Override
	public void run() {
		int page_num = 100;
		int max_msg_num = 3;
		int max_click_time = 5;
		int max_stay_time = 10;
		int[] like_or_not = new int[]{1,0,-1};
		Random random = new Random();
		while (true) {
			try {
				int msgNum = random.nextInt(max_msg_num);
				for (int i = 0; i < msgNum;i ++) {
					StringBuilder msg = new StringBuilder();
					msg.append("page"+(random.nextInt(page_num) + 1)).append("|");
					msg.append(random.nextInt(max_click_time) + 1).append("|");
					msg.append(random.nextInt(max_stay_time) + random.nextFloat()).append("|");
					msg.append(like_or_not[random.nextInt(3)]);
					System.out.println("msg = "+ msg.toString());
					producer.send(new KeyedMessage<Integer, String>(topic, msg.toString()));
				}
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
