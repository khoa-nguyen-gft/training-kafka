package com.love.kafka;

import org.apache.kafka.clients.consumer.*;

import java.util.Collections;
import java.util.Properties;

public class ExactlyOnceConsumerDemo {
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String GROUP_ID = "my_consumer_group";
    private static final String TOPIC = "my_topic";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", GROUP_ID);
        props.put("isolation.level", "read_committed"); // Read committed to avoid consuming transactional aborts

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Collections.singletonList(TOPIC));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    // Process the message here
                    System.out.println(">>>>>>> Received: " + record.value());
                }
                // Commit offsets only after processing the message successfully

                consumer.commitSync();
                Thread.sleep(5 * 1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
