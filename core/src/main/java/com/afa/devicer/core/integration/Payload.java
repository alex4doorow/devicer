package com.afa.devicer.core.integration;

import com.afa.devicer.core.utils.BaseUtils;

public class Payload {
	
	public Msg<?> msgIn; 	//to store original message;
	public Msg<?> msgOut;	//to store response(acknowledgement)
	
	public Payload(Msg<?> msg) {
		this.msgIn = msg;
	}
	
	public Payload(Msg<?> msgIn, Msg<?> msgOut) {
		this.msgIn = msgIn;
		this.msgOut = msgOut;
	}
	
	public Msg<?> getOriginalInMsg() {
		return BaseUtils.NVL(msgIn.getOriginalMsg(), msgIn);
	}
}
