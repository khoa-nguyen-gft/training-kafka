package com.love.kafka.consumer;

import com.love.kafka.entities.MessageEntity;
import com.love.kafka.message.DemoMessage;
import com.love.kafka.repositories.MessageRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.beans.Transient;


@Component
public class DemoConsumer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageRepository repository;


    @KafkaListener(topics = DemoMessage.TOPIC, groupId = "consumer-group-" + DemoMessage.TOPIC)
    @Transactional
    public void onMessage(DemoMessage record) {
        try {
            if (!repository.existsById(record.getId())) {
                logger.info("[ADD][onMessage][Thread number:{} Message content：{}]", record.getId(), record);
                var message = new MessageEntity(record.getId(), record.getContent());
                repository.save(message);
            } else {
                logger.info("[UPDATE][onMessage][Thread number:{} Message content：{}]",record.getId(), record);
                MessageEntity entity = repository.getReferenceById(record.getId());
                entity.setContent(record.getContent());
                repository.save(entity);
            }
        } catch (Exception e) {
            logger.error("Error processing message: {}", e.getMessage(), e);
        }
    }
}
