package com.afa.devicer.dispatcher.services;

import com.afa.devicer.core.services.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DispatcherKafkaConsumerService {

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private DispatcherKafkaProducerService kafkaProducerService;

    @KafkaListener(topics = "orders.dispatcher", groupId = "orders")
    public void ordersDispatcherHandleMessage(String message, @Header(KafkaHeaders.OFFSET) Long offset) {

        log.info("orders.dispatcher, offset={}, message: {}", offset, message);

        try {
            kafkaProducerService.sendMessage("dispatcher.web", message);
            // ...
        } catch (Exception e) {
            log.error("", e);
            throw new RuntimeException(e);
        }
    }
}
