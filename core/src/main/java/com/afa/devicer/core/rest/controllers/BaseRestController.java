package com.afa.devicer.core.rest.controllers;

import com.afa.devicer.core.services.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
public abstract class BaseRestController {

    protected ResponseEntity<Object> errorResponse(String msgInType, Exception exception) {
        log.error(msgInType, exception);
        return JsonMapper.errorResponse(msgInType, exception);
    }
}
