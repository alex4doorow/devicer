package com.afa.devicer.core.services.datalayers;

import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.integration.Msg;
import com.afa.devicer.core.integration.Payload;
import com.afa.devicer.core.integration.Processor;
import com.afa.devicer.core.model.types.ENFormat;
import com.afa.devicer.core.rest.dto.view.DtoOrdersByConditionsResponseModel;
import com.afa.devicer.core.services.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

public class VOrdersByConditionsProcessor implements Processor {

  @Autowired
  private JsonMapper jsonMapper;

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
    /*
    List<Document> documents = new ArrayList<>();
    for (SEDocument seDocument : seDocuments) {
      documents.add(convertSEDocumentToDocument(seDocument));
    }
    VDocumentsResponseModel result = new VDocumentsResponseModel();
    result.setDocuments(documents);
    return result;

    */
    DtoOrdersByConditionsResponseModel result = new DtoOrdersByConditionsResponseModel();
    result.setOrders(new ArrayList<>());
    return result;
  }

  /*

  private Document convertSEDocumentToDocument(SEDocument seDocument) {
    Document document = new Document();
    document.setId(seDocument.getId());
    document.setTrn(seDocument.getTrn());
    document.setCorrelationId(seDocument.getCorrelationId());
    document.setDocTypeId(seDocument.getDocumentType().getId());
    document.setDocTypeCode(seDocument.getDocumentType().getCode());
    document.setStepCode(seDocument.getDocStep());
    document.setStep(monitoringMessageService.findDocStep(seDocument.getDocStep()));
    document.setResultId(seDocument.getResult() == null ? null : seDocument.getResult().getId());
    document.setResultCode(seDocument.getResult() == null ? null : seDocument.getResult().getCode());
    document.setResultInfo(seDocument.getResultInfo());
    document.setSenderUserId(seDocument.getSenderUser().getId());
    document.setSenderUserCode(seDocument.getSenderUser().getCode());
    document.setPreparationDate(seDocument.getPreparationDate());
    document.setCrDt(seDocument.getCrDt());
    document.setUpdDt(seDocument.getUpdDt());
    return document;
  }

  */
}
