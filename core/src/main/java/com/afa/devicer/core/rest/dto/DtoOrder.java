package com.afa.devicer.core.rest.dto;

import com.afa.devicer.core.model.types.ENCustomerTypes;
import com.afa.devicer.core.model.types.ENOrderStatuses;
import com.afa.devicer.core.model.types.ENOrderTypes;
import com.afa.devicer.core.rest.dto.view.DtoViewOrderStatus;
import com.afa.devicer.core.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoOrder {
    private Long id;
    private Long orderNum;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_FORMAT_yyyy_MM_dd)
    private LocalDate orderDate;

    private DtoCustomer customer;

    private ENOrderTypes type;
    private ENOrderStatuses status;

    private DtoProductCategory productCategory;

    private DtoOrderDelivery delivery;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String annotation;

    @JsonIgnore
    public DtoViewOrderStatus getViewStatus() {
        return DtoViewOrderStatus.createViewOrderStatus(this);
    }

    @JsonIgnore
    public String getViewDateInfo() {
        String result = this.getType().getAnnotation() + ", " + this.getStatus().getAnnotation();
        return result;
        /*
        String expiredDate = this.getExpiredDate();
        if (StringUtils.isEmpty(expiredDate)) {
            return result;
        } else {
            return result + ", " + expiredDate;
        }
        */
    }
}
