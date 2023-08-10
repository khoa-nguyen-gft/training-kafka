package com.love.kafka.consumer;

import com.love.kafka.message.DemoMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class DemoConsumer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = DemoMessage.TOPIC, groupId = "consumer-group-" + DemoMessage.TOPIC)
    public void onMessage(DemoMessage record) {
        logger.info("[onMessage][Thread number:{} Message content：{}]", Thread.currentThread().getId(), record);
    }
}
