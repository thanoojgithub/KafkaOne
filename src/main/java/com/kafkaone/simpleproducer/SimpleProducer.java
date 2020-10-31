package com.kafkaone.simpleproducer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		KafkaProducer<String, Object> kafkaProducer = new KafkaProducer<String, Object>(properties);
		try {
			int i = 1;
			do {
				System.out.println(i);
				kafkaProducer.send(
						new ProducerRecord<String, Object>("eventOne", Integer.toString(i), "test message - " + i));
				i++;
				if (i == 1000) {
					Thread.sleep(10000);
				}
			} while (i > 0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			kafkaProducer.close();
		}
	}

}
