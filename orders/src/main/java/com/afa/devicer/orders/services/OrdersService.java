package com.afa.devicer.orders.services;

import com.afa.devicer.core.bl.entities.BaseEntity;
import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.bl.entities.SEUser;
import com.afa.devicer.core.bl.repositories.OrderRepository;
import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.rest.dto.DtoOrder;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
@Transactional
public class OrdersService {

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

    public Long add(DtoOrder dtoOrder, SEUser user) throws CoreException {
        log.info("add: {}", dtoOrder);

        SEOrder order = new SEOrder();
        order.setOrderNum(dtoOrder.getOrderNum());
        order.setOrderDate(dtoOrder.getOrderDate());
        //order.setDateAdded(LocalDateTime.now());
        order.setRecStatus(BaseEntity.ACTIVE);
        order.setUserAdded(user);

        try {
            SEOrder resultOrder = orderRepository.save(order);
            return resultOrder.getId();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
