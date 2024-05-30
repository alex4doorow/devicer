package com.afa.devicer.core.services.converters;

public interface IOConverter<IN, OUT> {
    OUT convertTo(IN input);
}
