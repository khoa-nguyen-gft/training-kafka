package app.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

public class Producer {
    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    public static final String TOPIC_USER_EVENTS = "user-events";
    public static final String TOPIC_GLOBAL_EVENTS = "global-events";
    public static final String CLIENT_ID = "client.id";
    private static String[] globalEvents = {
            "maintenance_begin",
            "maintenance_end",
            "plan_removed",
            "plan_added",
            "sale_begin",
            "sale_end"
    };

    public static void main(String[] args) throws IOException {
        KafkaProducer<String, String> producer;

        try (var stream = Producer.class.getClassLoader().getResourceAsStream("producer.yaml")) {
            Properties properties = new Properties();
            properties.load(stream);
            properties.setProperty(CLIENT_ID, "02-producer-" + UUID.randomUUID());

            log.info("Kafka producer properties: ");
            log.info(String.valueOf(properties));

            producer = new KafkaProducer(properties);

            System.out.println("Kafka producer Initialized");
        }

        try {
            for (int i = 0; i < 100000; i++) {
                ProducerRecord<String, String> user = new ProducerRecord<>(
                        TOPIC_USER_EVENTS, //topic name
                        "user-id", //key id
                        "some-value-" + System.nanoTime()
                );
                log.info("user message: " + user);
                producer.send(user);

                if (i > 0 && i % 100 == 0) {
                    String event = globalEvents[(int) (Math.random() * globalEvents.length)] + "_" + System.nanoTime();
                    ProducerRecord global = new ProducerRecord<>(
                            TOPIC_GLOBAL_EVENTS, //topic
                            "global-id", //key id
                            event
                    );
                    log.info("Global message: " + global);

                    producer.send(global);
                    log.info("Producing a global message. Message #" + i);
                    producer.flush();
                    log.info("flushed on " + i);
                }
            }
            producer.flush();
            log.info("final flush completed");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
            log.info("KafkaProducer closed");
            System.exit(0);
        }
    }
}
