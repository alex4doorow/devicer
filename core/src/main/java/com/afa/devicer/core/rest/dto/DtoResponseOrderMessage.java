package com.afa.devicer.core.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoResponseOrderMessage {
    private Long offset;
    DtoOrderMessage request;
}
