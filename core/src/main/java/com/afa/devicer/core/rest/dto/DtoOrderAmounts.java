package com.afa.devicer.core.rest.dto;

import com.afa.devicer.core.model.types.ENOrderAmountTypes;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DtoOrderAmounts {

    private DtoOrder parent;
    private Map<ENOrderAmountTypes, BigDecimal> amounts;

    public DtoOrderAmounts() {
        super();
        this.amounts = new HashMap<>();
    }

    public DtoOrderAmounts(DtoOrder order) {
        this();
        this.parent = order;
        init();
    }

    public void setValue(ENOrderAmountTypes key, BigDecimal value) {
        amounts.put(key, value);
    }

    public BigDecimal getValue(ENOrderAmountTypes key) {
        return amounts.get(key);
    }

    public void setParent(DtoOrder parent) {
        this.parent = parent;
    }

    public void setPostpay(BigDecimal value) {
        amounts.put(ENOrderAmountTypes.POSTPAY, value);
    }

    public BigDecimal getPostpay() {
        return amounts.get(ENOrderAmountTypes.POSTPAY);
    }

    public void setTotalWithDelivery(BigDecimal value) {
        amounts.put(ENOrderAmountTypes.TOTAL_WITH_DELIVERY, value);
    }

    public BigDecimal getTotalWithDelivery() {
        return amounts.get(ENOrderAmountTypes.TOTAL_WITH_DELIVERY);
    }

    public void setTotal(BigDecimal value) {
        amounts.put(ENOrderAmountTypes.TOTAL, value);
    }

    public BigDecimal getTotal() {
        return amounts.get(ENOrderAmountTypes.TOTAL);
    }

    public void setDelivery(BigDecimal value) {
        amounts.put(ENOrderAmountTypes.DELIVERY, value);
    }

    public BigDecimal getDelivery() {
        return amounts.get(ENOrderAmountTypes.DELIVERY);
    }

    public void setCashOnDelivery(BigDecimal value) {
        amounts.put(ENOrderAmountTypes.CASH_ON_DELIVERY, value);
    }

    public BigDecimal getCashOnDelivery() {
        return amounts.get(ENOrderAmountTypes.CASH_ON_DELIVERY);
    }

    public void setBill(BigDecimal value) {
        amounts.put(ENOrderAmountTypes.BILL, value);
    }

    public BigDecimal getBill() {
        return amounts.get(ENOrderAmountTypes.BILL);
    }

    public void setSupplier(BigDecimal value) {
        amounts.put(ENOrderAmountTypes.SUPPLIER, value);
    }

    public BigDecimal getSupplier() {
        return amounts.get(ENOrderAmountTypes.SUPPLIER);
    }

    public void setMargin(BigDecimal value) {
        amounts.put(ENOrderAmountTypes.MARGIN, value);
    }

    public BigDecimal getMargin() {
        return amounts.get(ENOrderAmountTypes.MARGIN);
    }

    public Map<ENOrderAmountTypes, BigDecimal> copyValues(DtoOrderAmounts source) {
        amounts.put(ENOrderAmountTypes.TOTAL, source.getValue(ENOrderAmountTypes.TOTAL));
        amounts.put(ENOrderAmountTypes.TOTAL_WITH_DELIVERY, source.getValue(ENOrderAmountTypes.TOTAL_WITH_DELIVERY));
        amounts.put(ENOrderAmountTypes.BILL, source.getValue(ENOrderAmountTypes.BILL));
        amounts.put(ENOrderAmountTypes.SUPPLIER, source.getValue(ENOrderAmountTypes.SUPPLIER));
        amounts.put(ENOrderAmountTypes.MARGIN, source.getValue(ENOrderAmountTypes.MARGIN));
        amounts.put(ENOrderAmountTypes.POSTPAY, source.getValue(ENOrderAmountTypes.POSTPAY));
        amounts.put(ENOrderAmountTypes.CASH_ON_DELIVERY, source.getValue(ENOrderAmountTypes.CASH_ON_DELIVERY));
        return amounts;
    }

    private void init() {
        amounts.put(ENOrderAmountTypes.TOTAL, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.TOTAL_WITH_DELIVERY, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.BILL, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.SUPPLIER, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.MARGIN, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.POSTPAY, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.CASH_ON_DELIVERY, BigDecimal.ZERO);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amounts == null) ? 0 : amounts.hashCode());
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
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
        DtoOrderAmounts other = (DtoOrderAmounts) obj;
        if (amounts == null) {
            if (other.amounts != null)
                return false;
        } else if (!amounts.equals(other.amounts))
            return false;
        if (parent == null) {
            if (other.parent != null)
                return false;
        } else if (!parent.equals(other.parent))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "OrderAmount [amounts=" + amounts + "]";
    }


}
