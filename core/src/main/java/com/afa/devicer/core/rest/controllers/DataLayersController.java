package com.afa.devicer.core.rest.controllers;

import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.integration.Msg;
import com.afa.devicer.core.integration.Producer;
import com.afa.devicer.core.model.types.ENFormat;
import com.afa.devicer.core.model.types.ENResult;
import com.afa.devicer.core.rest.dto.view.DtoOrdersByConditionsRequestModel;
import com.afa.devicer.core.services.JsonMapper;
import com.afa.devicer.core.services.datalayers.VOrdersByConditionsProducer;
import com.afa.devicer.core.utils.Defaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/rest/v1")
public class DataLayersController extends BaseRestController {

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    @Qualifier(value = "vOrdersByConditionsProducer")
    private Producer<DtoOrdersByConditionsRequestModel, String> vOrdersByConditionsProducer;


    @PostMapping("/orders/by-conditions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> ordersByConditions(
            @RequestHeader(value = Defaults.X_Request_ID, required = false)
            String requestId,
            @RequestHeader(value = Defaults.X_Client_ID, required = false)
            String user,
            @RequestHeader(value = Defaults.X_Token, required = false)
            String token,
            @RequestBody
            String body) throws CoreException {

        final String inType = VOrdersByConditionsProducer.inType;
        log.info("[START] {} request:\n{}", inType, body);

        Msg<DtoOrdersByConditionsRequestModel> msg;
        try {
            msg = new Msg<>(ENFormat.JSON,
                    inType,
                    requestId,
                    null, //TODO: sender from basic AUTH
                    user,
                    token,
                    jsonMapper.fromJSON(body, DtoOrdersByConditionsRequestModel.class));
        } catch (CoreException e) {
            //String errBody = jsonMapper.toJSON(new ErrorResponse(e.getRespCode(), e.getRespDesc()), CoreException.NOT_THROWS);
            return errorResponse(inType, e);
        }
        Msg<String> out = vOrdersByConditionsProducer.produce(msg);
        return response(inType, requestId, out.getBody(), out.getType() == ENResult.ERR);
    }

    private ResponseEntity<Object> response(String msgInType, String xRequestID, String response, boolean isError) {
        log.info("[END] {} response:\n{}", msgInType, response);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(Defaults.X_Request_ID, xRequestID);

        return new ResponseEntity<>(response, httpHeaders, (isError) ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
}
