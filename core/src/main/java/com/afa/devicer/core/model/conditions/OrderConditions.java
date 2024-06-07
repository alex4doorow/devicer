package com.afa.devicer.core.model.conditions;

import com.afa.devicer.core.model.types.ENPeriodTypes;
import com.afa.devicer.core.utils.DateTimeUtils;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderConditions extends AnyConditions {

    private int customerId;
    private String no;
    private String crmNo;
    private CustomerConditions customerConditions;
    private String trackCode;
    private String deliveryAddress;
    //	private Product product;
    private boolean periodExist;
    private boolean trackCodeNotExist;

    public OrderConditions(ENPeriodTypes reportPeriodType, LocalDate inputDate) {
        super(reportPeriodType, inputDate);
        customerConditions = new CustomerConditions();

//		this.product = Product.createEmpty();
        periodExist = true;
        trackCodeNotExist = false;

    }

    public OrderConditions(ENPeriodTypes periodType) {
        this(periodType, DateTimeUtils.sysDate());
    }

    public OrderConditions() {
        this(ENPeriodTypes.CURRENT_MONTH, DateTimeUtils.sysDate());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((crmNo == null) ? 0 : crmNo.hashCode());
        result = prime * result + ((customerConditions == null) ? 0 : customerConditions.hashCode());
        result = prime * result + customerId;
        result = prime * result + ((deliveryAddress == null) ? 0 : deliveryAddress.hashCode());
        result = prime * result + ((no == null) ? 0 : no.hashCode());
        result = prime * result + (periodExist ? 1231 : 1237);
//        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + ((trackCode == null) ? 0 : trackCode.hashCode());
        result = prime * result + (trackCodeNotExist ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderConditions other = (OrderConditions) obj;
        if (crmNo == null) {
            if (other.crmNo != null)
                return false;
        } else if (!crmNo.equals(other.crmNo))
            return false;
        if (customerConditions == null) {
            if (other.customerConditions != null)
                return false;
        } else if (!customerConditions.equals(other.customerConditions))
            return false;
        if (customerId != other.customerId)
            return false;
        if (deliveryAddress == null) {
            if (other.deliveryAddress != null)
                return false;
        } else if (!deliveryAddress.equals(other.deliveryAddress))
            return false;
        if (no == null) {
            if (other.no != null)
                return false;
        } else if (!no.equals(other.no))
            return false;
        if (periodExist != other.periodExist)
            return false;
        /*
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
         */
        if (trackCode == null) {
            if (other.trackCode != null)
                return false;
        } else if (!trackCode.equals(other.trackCode))
            return false;
        if (trackCodeNotExist != other.trackCodeNotExist)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "OrderConditions [reportPeriodType=" + this.getPeriodType() + ", reportPeriodMonth= " + this.getPeriodMonth() + ", reportPeriodYear=" + this.getPeriodYear()
                + ", no=" + no + ", customerConditions=" + customerConditions + ", trackCode=" + trackCode
                + ", period=" + this.getPeriod() + ", statuses=" + this.getStatuses() + "]";
    }


}
