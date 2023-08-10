package com.love.kafka.producer;

import com.love.kafka.message.DemoMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@Component
public class DemoProducer {

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        DemoMessage message = new DemoMessage(id);
        return kafkaTemplate.send(DemoMessage.TOPIC, message).get();
    }

    public ListenableFuture<SendResult<Object, Object>> asyncSend(Integer id) {
        DemoMessage message = new DemoMessage(id);
        return kafkaTemplate.send(DemoMessage.TOPIC, message);
    }
}
