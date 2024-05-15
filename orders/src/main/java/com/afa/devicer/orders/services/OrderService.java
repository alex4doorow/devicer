package com.afa.devicer.orders.services;

import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.bl.repositories.OrderRepository;
import com.afa.devicer.core.errors.CoreException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public SEOrder findById(Long id) throws CoreException {
        SEOrder order = orderRepository
                .findById(id)
                .orElseThrow(() -> new CoreException("ORDER", "order not found", CoreException.THROWS));
        log.info("order: {}", order);
        return order;
    }

}
