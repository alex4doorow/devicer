package com.afa.devicer.core.bl;

import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.errors.CoreException;

import java.time.LocalDate;
import java.util.Collection;

public interface OrdersBL {
    Collection<SEOrder> findOrdersByParams(LocalDate createdFrom, LocalDate createdTo) throws CoreException;
}
