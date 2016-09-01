package org.kevin.run;

import org.kevin.kafka.KafkaProducer;

public class KafkaRun {
	public static void main(String[] args) {
		KafkaProducer producer = new KafkaProducer("test2");
		producer.start();
	}
}
