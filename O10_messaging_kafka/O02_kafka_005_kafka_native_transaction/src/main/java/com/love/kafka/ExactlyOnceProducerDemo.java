package com.love.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.util.Properties;

public class ExactlyOnceProducerDemo {
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String TRANSACTIONAL_ID = "my_transactional_id";
    private static final String TOPIC = "my_topic";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("enable.idempotence", "true"); // Enable idempotent producer
        props.put("transactional.id", TRANSACTIONAL_ID);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        producer.initTransactions();

        int i = 1;

        try {
            while (true) {

                producer.beginTransaction();
                // Sending message within the transaction
                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, "key", i + ": Message with exactly-once semantics");
                producer.send(record);
                producer.commitTransaction();
                Thread.sleep(5 * 1000);
                i++;
            }
        } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
            e.printStackTrace();
            producer.close();
        } catch (KafkaException | InterruptedException e) {
            e.printStackTrace();
            producer.abortTransaction();
        }

        producer.close();
    }
}
