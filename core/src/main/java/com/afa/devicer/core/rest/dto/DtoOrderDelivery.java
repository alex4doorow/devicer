package com.afa.devicer.core.rest.dto;

import com.afa.devicer.core.model.types.ENDeliveryPrices;
import com.afa.devicer.core.model.types.ENDeliveryTypes;
import com.afa.devicer.core.model.types.ENPaymentDeliveryTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrderDelivery {

    @JsonIgnore
    private DtoOrder parent;

    private BigDecimal price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal factCustomerPrice;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal factSellerPrice;
    private ENDeliveryTypes type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ENDeliveryPrices deliveryPrice;
    private ENPaymentDeliveryTypes paymentDeliveryType;
    private DtoAddress address;
    private String trackCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String annotation;

    public DtoOrderDelivery(DtoOrder parent) {
        this.parent = parent;
    }

    @JsonIgnore
    public LocalDate getDeliveryDate() {
        /*
        if (getAddress() != null && getAddress().getCourierInfo() != null) {
            return getAddress().getCourierInfo().getDeliveryDate();
        } else {
            return null;
        }

        */
        return null;
    }

    @JsonIgnore
    public String timeInterval() {
        return null;
        /*
        if (getAddress() != null && getAddress().getCourierInfo() != null) {
            return getAddress().getCourierInfo().timeInterval();
        } else {
            return null;
        }
        */
    }

    @JsonIgnore
    public String getViewDeliveryInfo() {
        if (this.getAddress() == null || this.getAddress().getAddress() == null) {
            return "";
        }
        /*
        String result = this.getAddress().getAddress().replace("\"", "");
        if (getType().isCourier() && (parent.getStatus() == OrderStatuses.BID || parent.getStatus() == OrderStatuses.APPROVED || parent.getStatus() == OrderStatuses.PAY_WAITING || parent.getStatus() == OrderStatuses.PAY_ON || parent.getStatus() == OrderStatuses.DELIVERING)) {
            result += ", доставляем: " + DateTimeUtils.formatLocalDate(this.getAddress().getCourierInfo().getDeliveryDate(), DateTimeUtils.DATE_FORMAT_HH_mm_EEE) + " " + this.getAddress().getCourierInfo().timeInterval();
        } else if (getType() == DeliveryTypes.YANDEX_MARKET_FBS
                && (parent.getStatus() == OrderStatuses.BID || parent.getStatus() == OrderStatuses.APPROVED || parent.getStatus() == OrderStatuses.DELIVERING)) {
            result += ", отгружаем: " + DateTimeUtils.formatLocalDate(this.getAddress().getCourierInfo().getDeliveryDate(), DateTimeUtils.DATE_FORMAT_HH_mm_EEE) + " " + this.getAddress().getCourierInfo().timeInterval();
        } else if (getType() == DeliveryTypes.OZON_FBS
                && (parent.getStatus() == OrderStatuses.BID || parent.getStatus() == OrderStatuses.APPROVED || parent.getStatus() == OrderStatuses.DELIVERING)) {
            result += ", отгружаем: " + DateTimeUtils.formatLocalDate(this.getAddress().getCourierInfo().getDeliveryDate(), DateTimeUtils.DATE_FORMAT_HH_mm_EEE) + " " + this.getAddress().getCourierInfo().timeInterval();
        }
        return result;
        */
        return "";
    }
}
