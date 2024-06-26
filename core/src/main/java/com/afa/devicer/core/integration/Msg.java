package com.afa.devicer.core.integration;

import com.afa.devicer.core.model.types.ENFormat;
import com.afa.devicer.core.model.types.ENResult;

import java.util.LinkedHashMap;

public class Msg<T> extends LinkedHashMap<String, Object> {

    public static final String _type = "type";
    public static final String _requestId = "requestId";

    public static final String _sender = "sender";
    public static final String _reciever = "reciever";
    public static final String _signature = "signature";

    public static final String _code = "code";
    public static final String _desc = "desc";

    public static final String _filename = "filename";

    private final ENFormat format;
    private T body;
    private Msg<?> originalMsg;

    public Msg(ENFormat format, String type, String requestId, String sender, String reciever, String signature,
               T body) {
        super();
        this.format = format;
        this.body = body;
        put(_type, type);
        put(_requestId, requestId);
        put(_sender, sender);
        put(_reciever, reciever);
        put(_signature, signature);
    }

    public Msg(ENFormat format, ENResult type, String code, String desc, T body) {
        super();
        this.format = format;
        this.body = body;
        put(_type, type);
        put(_code, code);
        put(_desc, desc);
    }

    public Msg(ENFormat format, ENResult type, String requestId, //MIR or other assigned ext reference
               String code, String desc, T body) {
        super();
        this.format = format;
        this.body = body;
        put(_requestId, requestId);
        put(_type, type);
        put(_code, code);
        put(_desc, desc);
    }

    public ENFormat getFormat() {
        return format;
    }

    @SuppressWarnings("unchecked")
    public <RT> RT getType() {
        return (RT) get(_type);
    }

    public String getTypeAsString() {
        return getType() != null ? getType().toString() : "";
    }

    public String getRequestId() {
        return (String) get(_requestId);
    }

    public String getSender() {
        return (String) get(_sender);
    }

    public String getReciever() {
        return (String) get(_reciever);
    }

    public String getSignature() {
        return (String) get(_signature);
    }

    public String getCode() {
        return (String) get(_code);
    }

    public String getDesc() {
        return (String) get(_desc);
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Msg<?> getOriginalMsg() {
        return originalMsg;
    }

    public void setOriginalMsg(Msg<?> originalMsg) {
        this.originalMsg = originalMsg;
    }

}
