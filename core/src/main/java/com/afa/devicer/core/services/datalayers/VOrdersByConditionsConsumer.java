package com.afa.devicer.core.services.datalayers;

import com.afa.devicer.core.bl.OrdersBL;
import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.bl.repositories.OrderRepository;
import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.integration.Consumer;
import com.afa.devicer.core.integration.Msg;
import com.afa.devicer.core.integration.Payload;
import com.afa.devicer.core.model.types.ENFormat;
import com.afa.devicer.core.rest.dto.view.DtoOrdersByConditionsRequestModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

public class VOrdersByConditionsConsumer implements Consumer {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrdersBL ordersBL;

    @Override
    public void consume(Payload payload) throws CoreException {
        DtoOrdersByConditionsRequestModel request = (DtoOrdersByConditionsRequestModel) payload.msgIn.getBody();

        Collection<SEOrder> seOrders = loadByConditions(request);

        payload.msgOut = new Msg<>(ENFormat.JSON,
                VOrdersByConditionsProducer.outType,
                payload.msgIn.getRequestId(),
                payload.msgIn.getReciever(),
                payload.msgIn.getSender(),
                null,
                seOrders);
    }

    private Collection<SEOrder> loadByConditions(DtoOrdersByConditionsRequestModel request) throws CoreException {
        return ordersBL.findOrdersByParams(request.getParams().getStDtm().toLocalDate(), request.getParams().getEndDtm().toLocalDate());
        //return orderRepository.findAll();
    }
}
