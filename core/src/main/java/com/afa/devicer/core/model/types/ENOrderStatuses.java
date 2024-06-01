package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
	
	UNKNOWN(0, "неопределен", ""), 	
	BID(1, "заявка", "info"), // margin = 0, postpay = 0	
		//PROCESSING(21, "подтверждение", "info"), // margin = 0, postpay = 0
		//UNPAID(22, "оформлен, не оплачен", "info"), // margin = 0, postpay = 0	
	APPROVED(2, "подтвержден", ""),	// margin > 0, postpay > 0
	PAY_WAITING(3, "ожидаем оплату", "warning"), // margin = 0, postpay = ?
	PAY_ON(4, "оплата поступила", "warning"), // margin > 0, postpay = ?		
	DELIVERING(5, "доставляется", ""), // margin > 0, postpay > 0
	READY_GIVE_AWAY(7, "прибыл", ""), // margin > 0, postpay > 0
	READY_GIVE_AWAY_TROUBLE(12, "заканчивается срок хранения", "danger"), // margin > 0, postpay > 0
	DELIVERED(10, "получен", ""), 
	DOC_NOT_EXIST(11, "нет ТОРГ-12", ""), // margin > 0, postpay = 0
	FINISHED(8, "завершен", "success"), // margin > 0, postpay = 0	
	REDELIVERY(9, "отказ от вручения", "secondary"), // margin = 0, postpay > 0
	CANCELED(13, "отменен", "danger"), // margin = 0, postpay = 0
	REDELIVERY_FINISHED(15, "возврат получен", "danger"), // --
	LOST(16, "утерян", "lost"); // margin = 0, postpay = 0
	
	private int id;
	private String annotation;
	private String view;

	public static ENOrderStatuses getValueById(long value) {
	
		if (value == 1) {
			return ENOrderStatuses.BID;
		} else if (value == 2) {
			return ENOrderStatuses.APPROVED;
		} else if (value == 3) {
			return ENOrderStatuses.PAY_WAITING;
		} else if (value == 4) {
			return ENOrderStatuses.PAY_ON;
		} else if (value == 5) {
			return ENOrderStatuses.DELIVERING;
		} else if (value == 7) {
			return ENOrderStatuses.READY_GIVE_AWAY;
		} else if (value == 12) {
			return ENOrderStatuses.READY_GIVE_AWAY_TROUBLE;
		} else if (value == 8) {
			return ENOrderStatuses.FINISHED;
		} else if (value == 9) {
			return ENOrderStatuses.REDELIVERY;
		} else if (value == 10) {
			return ENOrderStatuses.DELIVERED;
		} else if (value == 11) {
			return ENOrderStatuses.DOC_NOT_EXIST;
		} else if (value == 13) {
			return ENOrderStatuses.CANCELED;
		} else if (value == 15) {
			return ENOrderStatuses.REDELIVERY_FINISHED;
		} else if (value == 16) {
			return ENOrderStatuses.LOST;
		} /*else if (value == 21) {
			return OrderStatuses.PROCESSING;
		} else if (value == 22) {
			return OrderStatuses.UNPAID;
		} */else {
			return ENOrderStatuses.UNKNOWN;
		}		
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
