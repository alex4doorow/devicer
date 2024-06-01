package com.afa.devicer.core.services.datalayers;

import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.bl.repositories.OrderRepository;
import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.integration.Consumer;
import com.afa.devicer.core.integration.Msg;
import com.afa.devicer.core.integration.Payload;
import com.afa.devicer.core.model.types.ENFormat;
import com.afa.devicer.core.rest.dto.view.DtoOrdersByConditionsRequestModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class VOrdersByConditionsConsumer implements Consumer {

  @Autowired
  private OrderRepository orderRepository;


  @Override
  public void consume(Payload payload) throws CoreException {
    DtoOrdersByConditionsRequestModel request = (DtoOrdersByConditionsRequestModel) payload.msgIn.getBody();






    /*
    List<SEDocument> seDocuments = docBL.findDocumentsByParams(request.getParams().getStDtm(), request.getParams().getEndDtm(),
        request.getParams().getDocTrn());

     */
    List<SEOrder> seOrders = orderRepository.findAll();

    payload.msgOut = new Msg<>(ENFormat.JSON,
        VOrdersByConditionsProducer.outType,
        payload.msgIn.getRequestId(),
        payload.msgIn.getReciever(),
        payload.msgIn.getSender(),
        null,
        seOrders);
  }
}