package com.afa.devicer.core.rest.dto;

import com.afa.devicer.core.errors.ErrorResponse;
import com.afa.devicer.core.model.types.ENMessageActions;
import com.afa.devicer.core.model.types.ENMessageStatuses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoState {
    private ENMessageActions action = ENMessageActions.UNKNOWN;
    private ENMessageStatuses status = ENMessageStatuses.UNKNOWN;
    private ErrorResponse error;
}
