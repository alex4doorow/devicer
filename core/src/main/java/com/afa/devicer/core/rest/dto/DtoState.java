package com.afa.devicer.core.rest.dto;

import com.afa.devicer.core.errors.ErrorResponse;
import com.afa.devicer.core.model.types.MessageActions;
import com.afa.devicer.core.model.types.MessageStatuses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoState {
    private MessageActions action = MessageActions.UNKNOWN;
    private MessageStatuses status = MessageStatuses.UNKNOWN;
    private ErrorResponse error;
}
