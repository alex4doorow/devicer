package com.afa.devicer.core.rest.dto.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrdersByConditionsRequestModel {
    @JsonProperty("params")
    private DtoOrdersByConditionsParams params;
}
