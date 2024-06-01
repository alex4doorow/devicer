package com.afa.devicer.core.services.datalayers;

import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.integration.Msg;
import com.afa.devicer.core.integration.Payload;
import com.afa.devicer.core.integration.Processor;
import com.afa.devicer.core.model.types.ENFormat;
import com.afa.devicer.core.rest.dto.DtoOrder;
import com.afa.devicer.core.rest.dto.view.DtoOrdersByConditionsResponseModel;
import com.afa.devicer.core.services.JsonMapper;
import com.afa.devicer.core.services.converters.out.OutDtoOrdersConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class VOrdersByConditionsProcessor implements Processor {

  @Autowired
  private JsonMapper jsonMapper;
  @Autowired
  private OutDtoOrdersConverter outDtoOrdersConverter;

  @Override
  public void process(Payload payload) throws CoreException {

    DtoOrdersByConditionsResponseModel response = convertSEOrdersToResponseModel((Collection<SEOrder>) payload.msgOut.getBody());

    String jsonResponse = jsonMapper.toJSON(response, CoreException.THROWS);

    payload.msgOut = new Msg<>(ENFormat.JSON,
        VOrdersByConditionsProducer.outType,
        payload.msgIn.getRequestId(),
        payload.msgIn.getReciever(),
        payload.msgIn.getSender(),
        null,
        jsonResponse);
  }



  private DtoOrdersByConditionsResponseModel convertSEOrdersToResponseModel(Collection<SEOrder> seOrders) {

    Collection<DtoOrder> dtoOrders = outDtoOrdersConverter.convertTo(seOrders);
    DtoOrdersByConditionsResponseModel result = new DtoOrdersByConditionsResponseModel();
    result.setOrders(dtoOrders);
    return result;
  }
}
