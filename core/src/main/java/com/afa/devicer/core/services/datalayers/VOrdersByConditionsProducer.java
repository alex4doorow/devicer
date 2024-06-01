package com.afa.devicer.core.services.datalayers;

import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.errors.ErrorResponse;
import com.afa.devicer.core.integration.Msg;
import com.afa.devicer.core.integration.Payload;
import com.afa.devicer.core.integration.Producer;
import com.afa.devicer.core.model.types.ENFormat;
import com.afa.devicer.core.model.types.ENResult;
import com.afa.devicer.core.rest.dto.view.DtoOrdersByConditionsRequestModel;
import com.afa.devicer.core.services.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class VOrdersByConditionsProducer extends Producer<DtoOrdersByConditionsRequestModel, String> {

    public static final String inType = "VOrdersByConditions";
    public static final String outType = "VOrdersByConditionsResponse";

    @Autowired
    private JsonMapper jsonMapper;

    @Override
    public void verifySignature(Msg<DtoOrdersByConditionsRequestModel> inMsg) throws SecurityException {

    }

    @SuppressWarnings("unchecked")
    @Override
    public Msg<String> reply(Payload payload) throws CoreException {
        return (Msg<String>) payload.msgOut;
    }

    @Override
    public Msg<String> replyError(Payload payload, CoreException e) {
        ErrorResponse response = new ErrorResponse(e.getRespCode(), e.getRespDesc());

        try {
            return new Msg<>(ENFormat.JSON,
                    ENResult.ERR,
                    e.getRespCode(), e.getRespDesc(),
                    jsonMapper.toJSON(response, CoreException.THROWS));
        } catch (CoreException ex) { //we are not able to do nothing with this error
            log.error("replyError failed", ex);
            throw new RuntimeException(ex);
        }
    }
}
