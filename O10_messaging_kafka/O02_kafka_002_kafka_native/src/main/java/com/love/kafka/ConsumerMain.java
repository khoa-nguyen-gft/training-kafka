package com.love.kafka;


import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerMain {
    public static void main(String[] args) {
        Consumer<String, String> consumer = createConsumer();

        consumer.subscribe(Collections.singleton(ProduceMain.TOPIC_NAME));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));
            records.forEach(new java.util.function.Consumer<ConsumerRecord<String, String>>() {
                @Override
                public void accept(ConsumerRecord<String, String> stringStringConsumerRecord) {
                    System.out.println(stringStringConsumerRecord.key() + "\t" + stringStringConsumerRecord.value());
                }
            });

        }
    }

    private static Consumer<String, String> createConsumer() {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "demo-consumer-group");
        properties.put("auto.offset.reset", "earliest");

        // Whether to automatically submit consumption progress
        // properties.put("enable.auto.commit", true);

        // Automatic submission of consumption progress frequency
        // properties.put("auto.commit.interval.ms", "1000");

        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());

        return new KafkaConsumer<>(properties);
    }

}
