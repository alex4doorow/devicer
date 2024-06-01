package com.afa.devicer.core.rest.dto.view;

import com.afa.devicer.core.rest.dto.DtoOrder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrdersByConditionsResponseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(required = true)
    private String errorCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String info;
    private Collection<DtoOrder> orders;
}