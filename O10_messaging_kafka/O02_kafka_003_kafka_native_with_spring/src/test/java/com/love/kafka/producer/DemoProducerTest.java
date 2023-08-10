package com.love.kafka.producer;


import com.love.kafka.KafkaApplication;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;


@SpringBootTest(classes = KafkaApplication.class)
public class DemoProducerTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DemoProducer producer;

    @Test
    public void testSynchronous() throws ExecutionException, InterruptedException {
        int id = (int) System.currentTimeMillis() / 1000;

        SendResult sendResult = producer.syncSend(id);
        logger.info("[onMessage][Thread number:{} Message content：{}]", id, sendResult);
    }

    @Test
    public void testAsync() throws ExecutionException, InterruptedException {
        int id = (int) System.currentTimeMillis() / 1000;

        ListenableFuture<SendResult<Object, Object>> asyncSend = producer.asyncSend(id);

        asyncSend.addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {

            @Override
            public void onSuccess(SendResult<Object, Object> result) {
                logger.info("[testSyncSend][Send number：[{}] Send result：[{}]]", id, result);

            }

            @Override
            public void onFailure(Throwable ex) {
                logger.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, ex);
            }
        });

    }


}
