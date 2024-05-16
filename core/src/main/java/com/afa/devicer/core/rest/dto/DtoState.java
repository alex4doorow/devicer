package com.afa.devicer.core.rest.dto;

import com.afa.devicer.core.errors.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoState {
    private String action;
    private String status;
    private ErrorResponse error;
}
