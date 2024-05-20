package com.afa.devicer.dispatcher.services;

import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.rest.dto.DtoOrderMessage;
import com.afa.devicer.core.services.JsonMapper;
import com.afa.devicer.dispatcher.sagas.AddOrderSaga;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class DispatcherKafkaConsumerService {

    private JsonMapper jsonMapper;
    private AddOrderSaga addOrderSaga;

    @Autowired
    public void setJsonMapper(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Autowired
    public void setAddOrderSaga(AddOrderSaga addOrderSaga) {
        this.addOrderSaga = addOrderSaga;
    }

    // d -> o dispatcher.orders.order.add
    // d -> o dispatcher.orders.order.edit
    // d -> o dispatcher.orders.order.validate

    // d -> s dispatcher.stock.order-item.product.validate
    // d -> s dispatcher.stock.order-item.product.sell

    // o -> d orders.dispatcher.order.add

    // stock.dispatcher



    @KafkaListener(topics = "dispatcher.orders", groupId = "groupId")
    public void ordersDispatcherHandleMessage(String message, @Headers Map<String, Object> headers,
                                              @Header(KafkaHeaders.OFFSET) Long offset) {
        log.info("Received message: {}", message);
        log.info("Headers:");
        headers.forEach((key, value) -> log.info("{}:{}", key, value));

        try {
            DtoOrderMessage dtoOrderMessage = jsonMapper.fromJSON(message, DtoOrderMessage.class);
            dtoOrderMessage.setOffset(offset);
            addOrderSaga.pending(dtoOrderMessage);



        } catch (CoreException e) {
            log.error("", e);
        }
    }

    //@KafkaListener(topics = "orders.dispatcher", groupId = "groupId")
    public void stockListener(String message) {
        log.info("DISPATCHER Received Message in group groupId: {}", message);

/*
        try {
            DtoOrderMessage dtoOrderMessage = jsonMapper.fromJSON(message, DtoOrderMessage.class);
            if (dtoOrderMessage.getAction().equals("PENDING")) {
                addOrderSaga.pending(dtoOrderMessage);
            }




        } catch(CoreException ex) {
            log.error("", ex);
        }
 */
    }
}

