package com.afa.devicer.core.rest.dto;

public interface DtoAnyMessage<DATA> {
    Long getOffset();
    DATA getBody();
    DtoState getState();
}
