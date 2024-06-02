package com.afa.devicer.core.rest.dto;

import com.afa.devicer.core.model.types.ENAddressTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DtoAddress {
    private Long id;
    private ENAddressTypes type;
    //private Countries country;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String city;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pvz;
    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    //private CourierInfo courierInfo;

    @JsonIgnore
    public String getViewAddress() {
        if (address != null) {
            final int MAX_VIEW_LENGTH = 25;
            int index = Math.min(address.length(), MAX_VIEW_LENGTH);
            if (index < address.length()) {
                return address.substring(0, index) + "...";
            } else {
                return address.substring(0, index);
            }
        }
        return null;
    }

}
