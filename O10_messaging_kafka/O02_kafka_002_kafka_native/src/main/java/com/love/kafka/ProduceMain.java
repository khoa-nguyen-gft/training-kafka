package com.love.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;


public class ProduceMain {
    public static String TOPIC_NAME = "hello_world";

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Producer<String, String> producer = createProducer();
        RecordMetadata recordMetaData = null;

        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> message = new ProducerRecord<>(TOPIC_NAME,
                    "key_message",
                    i + "_message_value");
            recordMetaData = producer.send(message).get();

            System.out.println("message sent to " + recordMetaData.topic() +
                    ", partition " + recordMetaData.partition() +
                    ", offset " + recordMetaData.offset());
        }


    }

    private static Producer<String, String> createProducer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("acks", "1");
        properties.put("retries", 3);
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());

        return new KafkaProducer<>(properties);
    }


}
