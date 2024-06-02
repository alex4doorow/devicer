package com.afa.devicer.core.utils.helpers;

import com.afa.devicer.core.model.types.ENOrderStatuses;
import com.afa.devicer.core.model.types.ENOrderTypes;
import com.afa.devicer.core.model.types.ENPaymentTypes;

public class OrderHelper {

    public static boolean isBillAmount(ENOrderTypes orderType,
                                       ENPaymentTypes paymentType,
                                       ENOrderStatuses status) {
        if (orderType == ENOrderTypes.ORDER) {
            if (status == ENOrderStatuses.APPROVED) {
                return true;
            } else if (status == ENOrderStatuses.PAY_WAITING) {
                return true;
            } else if (status == ENOrderStatuses.PAY_ON) {
                return true;
            } else if (status == ENOrderStatuses.DELIVERING) {
                return true;
            } else if (status == ENOrderStatuses.READY_GIVE_AWAY) {
                return true;
            } else if (status == ENOrderStatuses.READY_GIVE_AWAY_TROUBLE) {
                return true;
            } else if (status == ENOrderStatuses.DELIVERED) {
                return true;
            } else if (status == ENOrderStatuses.FINISHED) {
                return true;
            } else if (status == ENOrderStatuses.DOC_NOT_EXIST) {
                return true;
            } else {
                return true;
            }
        } else if (orderType == ENOrderTypes.BILL) {
            if (isPrepayment(paymentType)) {
                if (status == ENOrderStatuses.APPROVED) {
                    return false;
                } else if (status == ENOrderStatuses.PAY_WAITING) {
                    return false;
                } else if (status == ENOrderStatuses.PAY_ON) {
                    return true;
                } else if (status == ENOrderStatuses.DELIVERING) {
                    return true;
                } else if (status == ENOrderStatuses.READY_GIVE_AWAY) {
                    return true;
                } else if (status == ENOrderStatuses.READY_GIVE_AWAY_TROUBLE) {
                    return true;
                } else if (status == ENOrderStatuses.DELIVERED) {
                    return true;
                } else if (status == ENOrderStatuses.FINISHED) {
                    return true;
                } else if (status == ENOrderStatuses.DOC_NOT_EXIST) {
                    return true;
                } else {
                    return true;
                }
            } else if (paymentType == ENPaymentTypes.POSTPAY) {

                if (status == ENOrderStatuses.APPROVED) {
                    return true;
                } else if (status == ENOrderStatuses.PAY_WAITING) {
                    return true;
                } else if (status == ENOrderStatuses.PAY_ON) {
                    return true;
                } else if (status == ENOrderStatuses.DELIVERING) {
                    return true;
                } else if (status == ENOrderStatuses.READY_GIVE_AWAY) {
                    return true;
                } else if (status == ENOrderStatuses.READY_GIVE_AWAY_TROUBLE) {
                    return true;
                } else if (status == ENOrderStatuses.DELIVERED) {
                    return true;
                } else if (status == ENOrderStatuses.FINISHED) {
                    return true;
                } else if (status == ENOrderStatuses.DOC_NOT_EXIST) {
                    return true;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isPrepayment(ENPaymentTypes paymentType) {
        return paymentType == ENPaymentTypes.PREPAYMENT || paymentType == ENPaymentTypes.YANDEX_PAY;
    }

    public static boolean isPostpaidAmount(ENOrderTypes orderType,
                                           ENPaymentTypes paymentType,
                                           ENOrderStatuses status) {

        if (orderType == ENOrderTypes.ORDER) {
            if (paymentType == ENPaymentTypes.POSTPAY) {
                // заказ ФЛ с наложенным платежом
                if (status == ENOrderStatuses.APPROVED) {
                    return true;
                } else if (status == ENOrderStatuses.DELIVERING) {
                    return true;
                } else if (status == ENOrderStatuses.READY_GIVE_AWAY) {
                    return true;
                } else if (status == ENOrderStatuses.READY_GIVE_AWAY_TROUBLE) {
                    return true;
                } else if (status == ENOrderStatuses.DELIVERED) {
                    return true;
                } else if (status == ENOrderStatuses.REDELIVERY) {
                    return true;
                } else {
                    return true;
                }
            } else if (paymentType == ENPaymentTypes.PAYMENT_COURIER) {
                return false;
            }
        } else if (orderType == ENOrderTypes.BILL) {
            if (isPrepayment(paymentType)) {
                return false;
            } else if (paymentType == ENPaymentTypes.POSTPAY) {
                if (status == ENOrderStatuses.APPROVED) {
                    return true;
                } else if (status == ENOrderStatuses.DELIVERING) {
                    return true;
                } else if (status == ENOrderStatuses.READY_GIVE_AWAY) {
                    return true;
                } else if (status == ENOrderStatuses.READY_GIVE_AWAY_TROUBLE) {
                    return true;
                } else if (status == ENOrderStatuses.DELIVERED) {
                    return true;
                } else if (status == ENOrderStatuses.PAY_WAITING) {
                    return true;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
