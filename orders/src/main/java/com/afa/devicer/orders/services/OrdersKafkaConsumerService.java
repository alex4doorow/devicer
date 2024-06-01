package com.afa.devicer.orders.services;

import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.model.types.ENMessageActions;
import com.afa.devicer.core.rest.dto.DtoResponseOrderMessage;
import com.afa.devicer.core.services.JsonMapper;
import com.afa.devicer.core.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

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

    @KafkaListener(topics = "dispatcher.orders", groupId = "orders")
    public void dispatcherOrdersHandleMessage(String message,
                                              @Header(KafkaHeaders.OFFSET) Long offset) {

        log.info("dispatcher.orders, offset={}, message: {}", offset, message);

        try {
            DtoResponseOrderMessage dtoOrderMessage = jsonMapper.fromJSON(message, DtoResponseOrderMessage.class);
            dtoOrderMessage.setOffset(offset);
            if (dtoOrderMessage.getRequest().getState().getAction() == ENMessageActions.CREATE) {
                Long orderId = ordersService.add(dtoOrderMessage.getRequest(), userService.getCurrentUser());
                dtoOrderMessage.getRequest().getOrder().setId(orderId);
                log.info("dtoOrderMessage ORDER CREATE: {}", jsonMapper.toJSON(dtoOrderMessage, CoreException.THROWS));
            }
        } catch (CoreException e) {
            log.error("", e);
            throw new RuntimeException(e);
        }
    }
}
