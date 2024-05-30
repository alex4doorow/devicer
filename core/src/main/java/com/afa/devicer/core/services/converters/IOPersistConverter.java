package com.afa.devicer.core.services.converters;

public interface IOPersistConverter<IN, OUT> {
    OUT saveTo(IN input, OUT output);
}
