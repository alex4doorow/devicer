package com.afa.devicer.orders.services;

import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.rest.dto.DtoOrderMessage;
import com.afa.devicer.core.services.JsonMapper;
import com.afa.devicer.core.services.UserService;
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
    @Autowired
    private JsonMapper jsonMapper;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private UserService userService;

    //@KafkaListener(topics = "dispatcher.orders", groupId = "groupId")
    /*
    public void ordersListener(@Payload String message,
                               @Header(KafkaHeaders.OFFSET) Long offset) {
        log.info("[offset={}], message: {}", offset, message);
        log.info("ORDERS received message: {}", message);

    }
    */

    //@KafkaListener(id = "transactions-async", topics = "dispatcher.orders", groupId = "groupId")
    /*
    public void dispatcherOrdersHandleMessage1(@Payload String message,
                                               @Header(KafkaHeaders.OFFSET) Long offset,
                                               @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        log.info("[partition={},offset={}] Starting Async: {}", partition, offset, message);
        kafkaTemplate.send("orders.dispatcher", message, "Response message with offset: " + offset);
        //executorService.submit(() -> processor.process(order));
    }
    */

    @KafkaListener(topics = "dispatcher.orders", groupId = "orders")
    public void dispatcherOrdersHandleMessage(String message, @Header(KafkaHeaders.OFFSET) Long offset) {

        log.info("[offset={}], message: {}", offset, message);

        try {
            DtoOrderMessage dtoOrderMessage = jsonMapper.fromJSON(message, DtoOrderMessage.class);
            dtoOrderMessage.setOffset(offset);
            if (dtoOrderMessage.getState().getAction().equals("CREATE")) {
                Long orderId = ordersService.add(dtoOrderMessage, userService.getCurrentUser());
                dtoOrderMessage.getOrder().setId(orderId);
                log.info("dtoOrderMessage ORDER CREATE: {}", jsonMapper.toJSON(dtoOrderMessage, CoreException.THROWS));
            }
        } catch (CoreException e) {
            log.error("", e);
            throw new RuntimeException(e);
        }
    }
}
