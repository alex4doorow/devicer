package com.afa.devicer.core.rest.dto.view;

import com.afa.devicer.core.rest.dto.DtoOrder;
import lombok.Data;

@Data
public class DtoViewOrderStatus {

    public static String VIEW_STATUS_NONE = "";
    public static String VIEW_STATUS_SUCCESS = "success";
    public static String VIEW_STATUS_WARNING = "warning";
    public static String VIEW_STATUS_DANGER = "danger";
    public static String VIEW_STATUS_INFO = "info";
    public static String VIEW_STATUS_SECONDARY = "secondary";
    public static String VIEW_STATUS_LIGHT = "light";
    public static String VIEW_STATUS_DARK = "dark";

    private String union;
    private String orderId;
    private String orderNum;
    private String orderDate;

    public DtoViewOrderStatus() {
        this.union = VIEW_STATUS_LIGHT;
        this.orderId = VIEW_STATUS_LIGHT;
        this.orderNum = VIEW_STATUS_LIGHT;
        this.orderDate = VIEW_STATUS_LIGHT;
    }

    public static DtoViewOrderStatus createViewOrderStatus(DtoOrder order) {
        DtoViewOrderStatus viewOrderStatus = new DtoViewOrderStatus();
        // ..
        return viewOrderStatus;

    }
}
