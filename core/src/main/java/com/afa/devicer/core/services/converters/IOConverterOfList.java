package com.afa.devicer.core.services.converters;

import java.util.Collection;

public interface IOConverterOfList<IN, OUT> {
    Collection<OUT> convertTo(Collection<IN> inputList);
}