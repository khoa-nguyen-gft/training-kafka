package app.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class Consumer {
    public static final String TOPIC_USER_EVENTS = "user-events";
    public static final String TOPIC_GLOBAL_EVENTS = "global-events";
    public static final String CLIENT_ID = "client.id";

    public static void main(String[] args) throws IOException {
        KafkaConsumer<String, String> consumer;
        try (var stream = Consumer.class.getClassLoader().getResourceAsStream("consumer.yaml")) {
            Properties properties = new Properties();
            properties.load(stream);
            properties.setProperty(CLIENT_ID, "02-consumer-" + UUID.randomUUID());
            properties.setProperty("group.instance.id", properties.getProperty("client.id"));
            System.out.println("properties: " + properties);

            consumer = new KafkaConsumer(properties);
        }
        try {
            Duration duration = Duration.ofMillis(100);
            consumer.subscribe(List.of(TOPIC_USER_EVENTS, TOPIC_GLOBAL_EVENTS));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(duration);
                for (ConsumerRecord record : records) {
                    switch (record.topic()) {
                        case TOPIC_USER_EVENTS:
                            System.out.println("Received user-events message - key: " + record.key()
                                    + " value: " + record.value());
                            break;
                        case TOPIC_GLOBAL_EVENTS:
                            System.out.println("Received global-events message - value: " + record.value());
                            break;
                        default:
                            throw new IllegalStateException(
                                    "Shouldn't be possible to get message on topic " + record.topic());
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace(System.err);
        }
    }
}
