package com.afa.devicer.dispatcher.sagas;

import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.rest.dto.DtoOrderMessage;
import com.afa.devicer.core.services.JsonMapper;
import com.afa.devicer.dispatcher.services.DispatcherKafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddOrderSaga {

    private DispatcherKafkaProducerService dispatcherKafkaProducerService;
    private JsonMapper jsonMapper;

    @Autowired
    public void setDispatcherKafkaProducerService(DispatcherKafkaProducerService dispatcherKafkaProducerService) {
        this.dispatcherKafkaProducerService = dispatcherKafkaProducerService;
    }

    @Autowired
    public void setJsonMapper(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public void pending(DtoOrderMessage dtoOrderMessage) throws CoreException {
        // validate customer
        // save customer or get customer
        // save order state=pending
        dtoOrderMessage.getState().setStatus("PENDING");
        String kafkaMessage = jsonMapper.toJSON(dtoOrderMessage, CoreException.THROWS);

        //dispatcherKafkaProducerService.sendMessage("dispatcher.orders", kafkaMessage);

    }

    public void approve() {

    }


}
