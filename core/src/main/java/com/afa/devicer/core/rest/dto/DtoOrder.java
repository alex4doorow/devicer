package com.afa.devicer.core.rest.dto;

import com.afa.devicer.core.model.types.*;
import com.afa.devicer.core.utils.DateTimeUtils;
import com.afa.devicer.core.utils.helpers.OrderHelper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class DtoOrder implements Cloneable {
    private Long id;
    private Long orderNum;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_FORMAT_yyyy_MM_dd)
    private LocalDate orderDate;

    private DtoCustomer customer;

    private ENOrderTypes type;
    private ENOrderSourceTypes source;
    private ENOrderAdvertTypes advert;
    private ENPaymentTypes payment;
    private ENStores store;

    private ENOrderEmailStatuses emailStatus;
    private ENOrderStatuses status;

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

    @JsonIgnore
    public String getExpiredDate() {
        return "";
        /*
        if (this.getOffer().getCountDay() <= 0) {
            return result;
        }
        if ((this.getOrderType() == OrderTypes.BILL || this.getOrderType() == OrderTypes.KP) && this.getStatus() == ENOrderStatuses.BID) {
            result = DateTimeUtils.defaultFormatDate(this.getOffer().getExpiredDate());
        }
        */
    }

    public boolean isBillAmount() {
        if (this.getType() == ENOrderTypes.ORDER) {
            if (this.getStatus() == ENOrderStatuses.APPROVED) {
                return true;
            } else if (this.getStatus() == ENOrderStatuses.PAY_WAITING) {
                return true;
            } else if (this.getStatus() == ENOrderStatuses.PAY_ON) {
                return true;
            } else if (this.getStatus() == ENOrderStatuses.DELIVERING) {
                return true;
            } else if (this.getStatus() == ENOrderStatuses.READY_GIVE_AWAY) {
                return true;
            } else if (this.getStatus() == ENOrderStatuses.READY_GIVE_AWAY_TROUBLE) {
                return true;
            } else if (this.getStatus() == ENOrderStatuses.DELIVERED) {
                return true;
            } else if (this.getStatus() == ENOrderStatuses.FINISHED) {
                return true;
            } else if (this.getStatus() == ENOrderStatuses.DOC_NOT_EXIST) {
                return true;
            }
        } else if (this.getType() == ENOrderTypes.BILL) {
            if (this.isPrepayment()) {
                if (this.getStatus() == ENOrderStatuses.APPROVED) {
                    return false;
                } else if (this.getStatus() == ENOrderStatuses.PAY_WAITING) {
                    return false;
                } else if (this.getStatus() == ENOrderStatuses.PAY_ON) {
                    return true;
                } else if (this.getStatus() == ENOrderStatuses.DELIVERING) {
                    return true;
                } else if (this.getStatus() == ENOrderStatuses.READY_GIVE_AWAY) {
                    return true;
                } else if (this.getStatus() == ENOrderStatuses.READY_GIVE_AWAY_TROUBLE) {
                    return true;
                } else if (this.getStatus() == ENOrderStatuses.DELIVERED) {
                    return true;
                } else if (this.getStatus() == ENOrderStatuses.FINISHED) {
                    return true;
                } else if (this.getStatus() == ENOrderStatuses.DOC_NOT_EXIST) {
                    return true;
                }
            } else if (this.getPayment() == ENPaymentTypes.POSTPAY) {

                if (this.getStatus() == ENOrderStatuses.APPROVED) {
                    return true;
                } else if (this.getStatus() == ENOrderStatuses.PAY_WAITING) {
                    return true;
                } else if (this.getStatus() == ENOrderStatuses.PAY_ON) {
                    return true;
                } else if (this.getStatus() == ENOrderStatuses.DELIVERING) {
                    return true;
                } else if (this.getStatus() == ENOrderStatuses.READY_GIVE_AWAY) {
                    return true;
                } else if (this.getStatus() == ENOrderStatuses.READY_GIVE_AWAY_TROUBLE) {
                    return true;
                } else if (this.getStatus() == ENOrderStatuses.DELIVERED) {
                    return true;
                } else if (this.getStatus() == ENOrderStatuses.FINISHED) {
                    return true;
                } else if (this.getStatus() == ENOrderStatuses.DOC_NOT_EXIST) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    @JsonIgnore
    public String toString() {
        return "DtoOrder{" +
                "id=" + id +
                ", orderNum=" + orderNum +
                ", orderDate=" + orderDate +
                ", type=" + type +
                ", customer=" + customer +
                ", productCategory=" + productCategory +
                ", source=" + source +
                ", advert=" + advert +
                ", payment=" + payment +
                ", store=" + store +
                ", status=" + status +
                ", emailStatus=" + emailStatus +
                // ", delivery=" + delivery +
//                ", externalCrms=" + externalCrms +
                ", amounts=" + amounts +
//                ", items=" + items +
//                ", statuses=" + statuses +
                ", dateAdded=" + dateAdded +
                ", dateModified=" + dateModified +
                ", annotation='" + annotation + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();

        DtoOrder result = (DtoOrder) obj;
        result.setId(this.getId());
        result.setOrderNum(this.getOrderNum());
        result.setOrderDate(this.getOrderDate());
        result.setCustomer(this.getCustomer());
        result.setType(this.getType());
        result.setSource(this.getSource());
        result.setAdvert(this.getAdvert());
        result.setPayment(this.getPayment());
        result.setStore(this.getStore());
        result.setEmailStatus(this.getEmailStatus());
        result.setStatus(this.getStatus());
        result.setProductCategory(this.getProductCategory());
        result.setDelivery(this.getDelivery());
        result.setAmounts(this.getAmounts());
        result.setAnnotation(this.getAnnotation());
        result.setDateAdded(this.getDateAdded());
        result.setDateModified(this.getDateModified());

        return result;

    }
}
