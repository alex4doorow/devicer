package com.afa.devicer.web.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebKafkaConsumerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private OrdersWebSocketSender webSocketSender;

    @KafkaListener(topics = "dispatcher.web", groupId = "orders")
    public void dispatcherOrdersHandleMessage(String message, @Header(KafkaHeaders.OFFSET) Long offset) {

        log.info("dispatcher.web, offset={}, message: {}", offset, message);
        //webSocketSender.sendOrderUpdate(message);
    }
}
