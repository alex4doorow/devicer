package com.afa.devicer.web.dto.view;

import com.afa.devicer.core.model.types.ENDeliveryTypes;
import com.afa.devicer.core.model.types.ENOrderStatuses;
import com.afa.devicer.core.model.types.ENOrderTypes;
import com.afa.devicer.core.model.types.ENPaymentTypes;
import com.afa.devicer.core.rest.dto.DtoOrder;
import lombok.Data;

@Data
public class DtoWebViewOrderStatus {

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

    public DtoWebViewOrderStatus() {
        this.union = VIEW_STATUS_LIGHT;
        this.orderId = VIEW_STATUS_LIGHT;
        this.orderNum = VIEW_STATUS_LIGHT;
        this.orderDate = VIEW_STATUS_LIGHT;
    }

    public static DtoWebViewOrderStatus createViewOrderStatus(DtoOrder order) {
        DtoWebViewOrderStatus viewOrderStatus = new DtoWebViewOrderStatus();
        if (order.getType() == ENOrderTypes.KP || order.getType() == ENOrderTypes.CONSULTATION) {
            if (order.getStatus() == ENOrderStatuses.CANCELED) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                return viewOrderStatus;
            } else {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_INFO);
                return viewOrderStatus; // голубой
            }
        } else if (order.getType() == ENOrderTypes.ORDER) {
            if (order.getStatus() == ENOrderStatuses.BID) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_LIGHT);
                return viewOrderStatus;
            } else if (order.getStatus() == ENOrderStatuses.APPROVED) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_LIGHT);
                if (order.getDelivery().getType() == ENDeliveryTypes.YANDEX_MARKET_FBS) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_WARNING);
                } else if (order.getDelivery().getType() == ENDeliveryTypes.OZON_FBS) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_WARNING);
                } else if (order.getDelivery().getType().isCdek()) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_WARNING);
                } else if (order.getDelivery().getType().isPost()) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_WARNING);
                } else if (order.getDelivery().getType().isCourier()) {
                    viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_WARNING);
                    return viewOrderStatus;
                } else if (order.getDelivery().getType() == ENDeliveryTypes.YANDEX_GO) {
                    viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_WARNING);
                    return viewOrderStatus;
                }
                return viewOrderStatus;
            } else if (order.getStatus() == ENOrderStatuses.DOC_NOT_EXIST) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_LIGHT);
                viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_DARK);
                viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_DARK);
                viewOrderStatus.setOrderDate(DtoWebViewOrderStatus.VIEW_STATUS_DARK);
                return viewOrderStatus;
            } else if (order.getStatus() == ENOrderStatuses.FINISHED) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                return viewOrderStatus; // зеленый
            } else if (order.getStatus() == ENOrderStatuses.REDELIVERY) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                return viewOrderStatus; // красный
            } else if (order.getStatus() == ENOrderStatuses.CANCELED) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                return viewOrderStatus; // красный
            } else if (order.getStatus() == ENOrderStatuses.LOST) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                return viewOrderStatus; // красный
            } else if (order.getStatus() == ENOrderStatuses.REDELIVERY_FINISHED) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                return viewOrderStatus; // красный
            } else if (order.getStatus() == ENOrderStatuses.DELIVERING) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_LIGHT);
                if (order.getDelivery().getType().isCdek()) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    return viewOrderStatus;
                } else if (order.getDelivery().getType().isPost()) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    return viewOrderStatus;
                } else if (order.getDelivery().getType().isCourier()) {
                    viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_WARNING);
                    return viewOrderStatus;
                } else if (order.getDelivery().getType() == ENDeliveryTypes.YANDEX_GO) {
                    viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_WARNING);
                    return viewOrderStatus;
                } else if (order.getDelivery().getType() == ENDeliveryTypes.YANDEX_MARKET_FBS) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    return viewOrderStatus;
                } else if (order.getDelivery().getType() == ENDeliveryTypes.OZON_FBS) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    return viewOrderStatus;
                }
            } else if (order.getStatus() == ENOrderStatuses.READY_GIVE_AWAY || order.getStatus() == ENOrderStatuses.READY_GIVE_AWAY_TROUBLE) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_LIGHT);
                if (order.getDelivery().getType().isCdek()) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                    viewOrderStatus.setOrderDate(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                    return viewOrderStatus;
                } else if (order.getDelivery().getType().isPost()) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                    viewOrderStatus.setOrderDate(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                    return viewOrderStatus;
                } else if (order.getDelivery().getType() == ENDeliveryTypes.YANDEX_MARKET_FBS) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                    viewOrderStatus.setOrderDate(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                    return viewOrderStatus;
                } else if (order.getDelivery().getType() == ENDeliveryTypes.OZON_FBS) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                    viewOrderStatus.setOrderDate(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                    return viewOrderStatus;
                }
            } else if (order.getStatus() == ENOrderStatuses.DELIVERED) {
                if (order.getDelivery().getType().isPost() || order.getDelivery().getType().isCdek()) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderDate(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    return viewOrderStatus;
                } else if (order.getDelivery().getType().isCourier()) {
                    viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    return viewOrderStatus;
                } else if (order.getDelivery().getType() == ENDeliveryTypes.YANDEX_MARKET_FBS) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderDate(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    return viewOrderStatus;
                } else if (order.getDelivery().getType() == ENDeliveryTypes.OZON_FBS) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderDate(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    return viewOrderStatus;
                }
            }
        } else if (order.getType() == ENOrderTypes.BILL) {
            if (order.getStatus() == ENOrderStatuses.BID) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_INFO);
                return viewOrderStatus;
            } else if (order.getStatus() == ENOrderStatuses.APPROVED) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_LIGHT);
                if (order.isPrepayment()) {
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                } else {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_WARNING);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                }
                return viewOrderStatus;
            } else if (order.getStatus() == ENOrderStatuses.PAY_WAITING) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_LIGHT);
                viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                return viewOrderStatus;
            } else if (order.getStatus() == ENOrderStatuses.PAY_ON) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_LIGHT);
                if (order.isPrepayment()) {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_WARNING);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                } else {
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderDate(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                }
                return viewOrderStatus;
            } else if (order.getStatus() == ENOrderStatuses.DOC_NOT_EXIST) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_LIGHT);
                viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_DARK);
                viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_DARK);
                viewOrderStatus.setOrderDate(DtoWebViewOrderStatus.VIEW_STATUS_DARK);
                return viewOrderStatus;
            } else if (order.getStatus() == ENOrderStatuses.FINISHED) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                return viewOrderStatus;
            } else if (order.getStatus() == ENOrderStatuses.CANCELED) {
                viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                return viewOrderStatus;
            } else if (order.getStatus() == ENOrderStatuses.DELIVERING) {
                if (order.isPrepayment()) {
                    viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_LIGHT);
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderDate(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                    return viewOrderStatus;
                } else if (order.getPayment() == ENPaymentTypes.POSTPAY) {
                    viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_LIGHT);
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                    viewOrderStatus.setOrderDate(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                    return viewOrderStatus;
                }
            } else if (order.getStatus() == ENOrderStatuses.READY_GIVE_AWAY || order.getStatus() == ENOrderStatuses.READY_GIVE_AWAY_TROUBLE) {
                if (order.isPrepayment()) {
                    viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_LIGHT);
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderDate(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    return viewOrderStatus;
                } else if (order.getPayment() == ENPaymentTypes.POSTPAY) {
                    viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_LIGHT);
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                    viewOrderStatus.setOrderDate(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    return viewOrderStatus;
                }

            } else if (order.getStatus() == ENOrderStatuses.DELIVERED) {
                if (order.isPrepayment()) {
                    viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_LIGHT);
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    viewOrderStatus.setOrderDate(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    return viewOrderStatus;
                } else if (order.getPayment() == ENPaymentTypes.POSTPAY) {
                    viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_LIGHT);
                    viewOrderStatus.setOrderId(DtoWebViewOrderStatus.VIEW_STATUS_DANGER);
                    viewOrderStatus.setOrderNum(DtoWebViewOrderStatus.VIEW_STATUS_SUCCESS);
                    return viewOrderStatus;
                }
            }

        } else if (order.getType() == ENOrderTypes.REFUND) {
            viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_SECONDARY);
            return viewOrderStatus;

        } else if (order.getType() == ENOrderTypes.REPAIR || order.getType() == ENOrderTypes.CHANGE) {
            viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_SECONDARY);
            return viewOrderStatus;

        } else if (order.getType() == ENOrderTypes.GIFT) {
            viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_SECONDARY);
            return viewOrderStatus;

        } else if (order.getType() == ENOrderTypes.TEST_DIVE) {
            viewOrderStatus.setUnion(DtoWebViewOrderStatus.VIEW_STATUS_SECONDARY);
            return viewOrderStatus;
        }
        return viewOrderStatus;

    }
}
