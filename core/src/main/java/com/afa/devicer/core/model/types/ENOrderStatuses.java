package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
select * from oc_order_status where language_id = 2
		1 добавлен
		2 процесс оплаты
		17 передан в CRM
		19 подтвержден
		15 оплачен
		3 отправлен
		20 прибыл
		18 получен
		5 завершен
		16 анулирован
		12 возвращен
		11 возврат получен	
*/
@Getter
@AllArgsConstructor
public enum ENOrderStatuses {
	
	UNKNOWN(0L, "неопределен", ""),
	BID(1L, "заявка", "info"), // margin = 0, postpay = 0
		//PROCESSING(21, "подтверждение", "info"), // margin = 0, postpay = 0
		//UNPAID(22, "оформлен, не оплачен", "info"), // margin = 0, postpay = 0	
	APPROVED(2L, "подтвержден", ""),	// margin > 0, postpay > 0
	PAY_WAITING(3L, "ожидаем оплату", "warning"), // margin = 0, postpay = ?
	PAY_ON(4L, "оплата поступила", "warning"), // margin > 0, postpay = ?
	DELIVERING(5L, "доставляется", ""), // margin > 0, postpay > 0
	READY_GIVE_AWAY(7L, "прибыл", ""), // margin > 0, postpay > 0
	READY_GIVE_AWAY_TROUBLE(12L, "заканчивается срок хранения", "danger"), // margin > 0, postpay > 0
	DELIVERED(10L, "получен", ""),
	DOC_NOT_EXIST(11L, "нет ТОРГ-12", ""), // margin > 0, postpay = 0
	FINISHED(8L, "завершен", "success"), // margin > 0, postpay = 0
	REDELIVERY(9L, "отказ от вручения", "secondary"), // margin = 0, postpay > 0
	CANCELED(13L, "отменен", "danger"), // margin = 0, postpay = 0
	REDELIVERY_FINISHED(15L, "возврат получен", "danger"), // --
	LOST(16L, "утерян", "lost"); // margin = 0, postpay = 0
	
	private final long id;
	private final String annotation;
	private final String view;

	public static ENOrderStatuses getValueById(long value) {

		for (ENOrderStatuses type : values()) {
			if (Objects.equals(value, type.getId())) {
				return type;
			}
		}
		return null;
	}
	
	public static ENOrderStatuses getValueByAnnotation(String value) {
		if (value.equals(ENOrderStatuses.BID.getAnnotation())) {
			return ENOrderStatuses.BID;
		} /*else if (value.equals(OrderStatuses.PROCESSING.getAnnotation())) {
			return OrderStatuses.PROCESSING;
		} else if (value.equals(OrderStatuses.UNPAID.getAnnotation())) {
			return OrderStatuses.UNPAID;
		} */else if (value.equals(ENOrderStatuses.APPROVED.getAnnotation())) {
			return ENOrderStatuses.APPROVED;
		} else if (value.equals(ENOrderStatuses.PAY_WAITING.getAnnotation())) {
			return ENOrderStatuses.PAY_WAITING;
		} else if (value.equals(ENOrderStatuses.PAY_ON.getAnnotation())) {
			return ENOrderStatuses.PAY_ON;
		} else if (value.equals(ENOrderStatuses.DELIVERING.getAnnotation())) {
			return ENOrderStatuses.DELIVERING;
		} else if (value.equals(ENOrderStatuses.READY_GIVE_AWAY.getAnnotation())) {
			return ENOrderStatuses.READY_GIVE_AWAY;
		} else if (value.equals(ENOrderStatuses.READY_GIVE_AWAY_TROUBLE.getAnnotation())) {
			return ENOrderStatuses.READY_GIVE_AWAY_TROUBLE;
		} else if (value.equals(ENOrderStatuses.DELIVERED.getAnnotation())) {
			return ENOrderStatuses.DELIVERED;
		} else if (value.equals(ENOrderStatuses.FINISHED.getAnnotation())) {
			return ENOrderStatuses.FINISHED;
		} else if (value.equals(ENOrderStatuses.DOC_NOT_EXIST.getAnnotation())) {
			return ENOrderStatuses.DOC_NOT_EXIST;
		} else if (value.equals(ENOrderStatuses.REDELIVERY.getAnnotation())) {
			return ENOrderStatuses.REDELIVERY;
		} else if (value.equals(ENOrderStatuses.CANCELED.getAnnotation())) {
			return ENOrderStatuses.CANCELED;
		} else if (value.equals(ENOrderStatuses.REDELIVERY_FINISHED.getAnnotation())) {
			return ENOrderStatuses.REDELIVERY_FINISHED;
		} else if (value.equals(ENOrderStatuses.LOST.getAnnotation())) {
			return ENOrderStatuses.LOST;
		} else {
			return ENOrderStatuses.UNKNOWN;
		}		
	}
	
	public static String convertValuesToSplitedString(ENOrderStatuses... values) {
		
		if (values == null || values.length == 0) {
			return "";
		}
		String result = "";
		for (ENOrderStatuses value : values) {
			result += String.valueOf(value.getId()) + ",";			
		}
		result = result.substring(0, result.length() - 1).trim();
		return result;		
	}
}
