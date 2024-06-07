package com.afa.devicer.core.model.conditions;

import com.afa.devicer.core.model.types.*;
import com.afa.devicer.core.utils.DateTimeUtils;
import com.afa.devicer.core.utils.Pair;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public abstract class AnyConditions {

    private Pair<LocalDate> period;

    private ENPeriodTypes periodType;

    private int periodMonth;
    private int periodQuarter;
    private int periodHalfYear;
    private int periodYear;

    //	private Set<DeliveryTypes> deliveryTypes;
    private Set<ENCustomerTypes> customerTypes;
    private Set<ENPaymentTypes> paymentTypes;
    private Set<ENOrderAdvertTypes> advertTypes;

    private Set<ENOrderStatuses> statuses;
    private Set<ENOrderTypes> types;

    public AnyConditions() {
//		deliveryTypes = new HashSet<DeliveryTypes>();
        customerTypes = new HashSet<>();
        paymentTypes = new HashSet<>();
        advertTypes = new HashSet<>();

//		statuses = new HashSet<OrderStatuses>();
        types = new HashSet<>();
        this.periodType = ENPeriodTypes.ANY_MONTH;
        this.period = new Pair<>(DateTimeUtils.sysDate(), DateTimeUtils.sysDate());
    }

    public AnyConditions(LocalDate periodStart, LocalDate periodEnd) {
        this();
        this.period = new Pair<>(periodStart, periodEnd);
    }

    public AnyConditions(ENPeriodTypes periodType, LocalDate inputDate) {
        this();
        this.periodType = periodType;
        setPeriodByType(this.periodType, DateTimeUtils.sysDate());

    }

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    public LocalDate getPeriodStart() {
        return period.getStart();
    }

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    public void setPeriodStart(LocalDate periodStart) {
        period.setStart(periodStart);
    }

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    public LocalDate getPeriodEnd() {
        return period.getEnd();
    }

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    public void setPeriodEnd(LocalDate periodEnd) {
        period.setEnd(periodEnd);
    }

    public Pair<LocalDate> findPeriodByType(ENPeriodTypes type, LocalDate date) {
        LocalDate start;
        LocalDate end;

        if (type == ENPeriodTypes.CURRENT_MONTH) {
            start = DateTimeUtils.firstDayOfMonth(date);
            end = DateTimeUtils.lastDayOfMonth(start);
        } else if (type == ENPeriodTypes.PRIOR_MONTH) {
            LocalDate firstDateOfMonth = DateTimeUtils.firstDayOfMonth(date);
            start = DateTimeUtils.firstDayOfMonth(DateTimeUtils.beforeAnyDayOfDate(firstDateOfMonth, 1));
            end = DateTimeUtils.lastDayOfMonth(start);
        } else if (type == ENPeriodTypes.CURRENT_QUARTER) {
            start = DateTimeUtils.firstDayOfQuarter(date);
            end = DateTimeUtils.lastDayOfQuarter(start);
        } else if (type == ENPeriodTypes.CURRENT_YEAR) {
            start = DateTimeUtils.firstDayOfYear(date);
            end = DateTimeUtils.lastDayOfYear(start);
        } else if (type == ENPeriodTypes.LAST_7_DAYS) {
            end = DateTimeUtils.truncateDate(date);
            start = DateTimeUtils.beforeAnyDayOfDate(end, 7);
        } else if (type == ENPeriodTypes.LAST_30_DAYS) {
            end = DateTimeUtils.truncateDate(date);
            start = DateTimeUtils.beforeAnyDayOfDate(end, 30);
        } else if (type == ENPeriodTypes.LAST_90_DAYS) {
            end = DateTimeUtils.truncateDate(date);
            start = DateTimeUtils.beforeAnyDayOfDate(end, 90);
        } else if (type == ENPeriodTypes.CURRENT_HALF_YEAR) {
            start = DateTimeUtils.firstDayOfHalfYear(date);
            end = DateTimeUtils.lastDayOfHalfYear(start);
        } else {
            start = DateTimeUtils.truncateDate(LocalDate.now());
            end = DateTimeUtils.truncateDate(LocalDate.now());
        }
        return new Pair<>(start, end);
    }

    public void setPeriodByType(ENPeriodTypes type, LocalDate inputDate) {
        Pair<LocalDate> period = findPeriodByType(type, inputDate);
        setPeriod(period);

        this.periodMonth = DateTimeUtils.monthOfDate(period.getStart());
        this.periodQuarter = DateTimeUtils.quarterOfDate(period.getStart());
        this.periodHalfYear = DateTimeUtils.halfYearOfDate(period.getStart());
        this.periodYear = DateTimeUtils.yearOfDate(period.getStart());
    }

}
