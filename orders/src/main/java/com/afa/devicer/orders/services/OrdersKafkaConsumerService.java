package com.afa.devicer.orders.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class OrdersKafkaConsumerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //@KafkaListener(topics = "dispatcher.orders", groupId = "groupId")
    public void ordersListener(String message) {
        log.info("ORDERS Received Message in group groupId: {}", message);
    }

    //@KafkaListener(id = "transactions-async", topics = "dispatcher.orders", groupId = "groupId")
    public void dispatcherOrdersHandleMessage1(@Payload String message,
                                               @Header(KafkaHeaders.OFFSET) Long offset,
                                               @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        log.info("[partition={},offset={}] Starting Async: {}", partition, offset, message);
        kafkaTemplate.send("orders.dispatcher", message, "Response message with offset: " + offset);
        //executorService.submit(() -> processor.process(order));
    }

    @KafkaListener(topics = "dispatcher.orders", groupId = "groupId")
    public void dispatcherOrdersHandleMessage(String message, @Headers Map<String, Object> headers) {

        log.info("Received message: {}", message);
        log.info("Headers:");
        headers.forEach((key, value) -> log.info("{}:{}", key, value));
    }
}
