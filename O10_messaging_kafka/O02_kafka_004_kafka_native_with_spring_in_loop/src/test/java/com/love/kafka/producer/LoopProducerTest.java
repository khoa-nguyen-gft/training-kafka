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
public class LoopProducerTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DemoProducer producer;

    @Test
    public void testLoopProcedure() throws ExecutionException, InterruptedException {

        int i = 1;
        SendResult sendResult;

        while (true) {
            sendResult = producer.syncSend(i, i + ": message content");
            Thread.sleep(5 * 1000); // send message every 10 seconds
            logger.info("[onMessage][Thread number:{} Message content：{}]", i, sendResult);
            i++;
        }

    }


}
