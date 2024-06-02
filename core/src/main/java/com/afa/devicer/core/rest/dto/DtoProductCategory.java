package com.afa.devicer.core.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DtoProductCategory {
    private Long id;
    private String name;
    private String group;
}
