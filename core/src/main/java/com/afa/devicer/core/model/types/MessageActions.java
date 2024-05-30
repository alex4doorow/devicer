package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageActions {
    UNKNOWN,
    CREATE,
    UPDATE,
    VALIDATE,
    DELETE;
}
