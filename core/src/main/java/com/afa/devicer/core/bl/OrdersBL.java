package com.afa.devicer.core.bl;

import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.errors.CoreException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public interface OrdersBL {
    Collection<SEOrder> findOrdersByParams(LocalDate createdFrom, LocalDate createdTo) throws CoreException;
}
