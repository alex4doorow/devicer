package com.afa.devicer.core.rest.dto;

import com.afa.devicer.core.model.types.*;
import com.afa.devicer.core.rest.dto.view.DtoViewOrderStatus;
import com.afa.devicer.core.utils.DateTimeUtils;
import com.afa.devicer.core.utils.helpers.OrderHelper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Map;

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
    private ENPaymentTypes payment;

    private DtoProductCategory productCategory;

    private DtoOrderDelivery delivery;
    private Map<ENOrderAmountTypes, BigDecimal> amounts;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String annotation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_FMT_ISO8601)
    private OffsetDateTime dateAdded;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_FMT_ISO8601)
    private OffsetDateTime dateModified;

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

    @JsonIgnore
    public boolean isPrepayment() {
        return OrderHelper.isPrepayment(payment);
    }

    @JsonIgnore
    public BigDecimal getAmountBill() {
        return amounts.get(ENOrderAmountTypes.BILL);
    }

    @JsonIgnore
    public BigDecimal getAmountSupplier() {
        return amounts.get(ENOrderAmountTypes.SUPPLIER);
    }

    @JsonIgnore
    public BigDecimal getAmountMargin() {
        return amounts.get(ENOrderAmountTypes.MARGIN);
    }

    @JsonIgnore
    public BigDecimal getAmountPostpay() {
        return amounts.get(ENOrderAmountTypes.POSTPAY);
    }

    @Override
    @JsonIgnore
    public String toString() {
        return "DtoOrder {" +
                "id=" + id +
                ", orderNum=" + orderNum +
                ", orderDate=" + orderDate +
                ", type=" + type +
                // ", customer=" + customer +
                ", productCategory=" + productCategory +
//                ", source=" + source +
//                ", advert=" + advert +
//                ", payment=" + payment +
//                ", store=" + store +
                ", status=" + status +
//                ", emailStatus=" + emailStatus +
                // ", delivery=" + delivery +
//                ", externalCrms=" + externalCrms +
//                ", amounts=" + amounts +
//                ", items=" + items +
//                ", statuses=" + statuses +
                ", dateAdded=" + dateAdded +
                ", dateModified=" + dateModified +
                ", annotation='" + annotation + '\'' +
                '}';
    }
}
