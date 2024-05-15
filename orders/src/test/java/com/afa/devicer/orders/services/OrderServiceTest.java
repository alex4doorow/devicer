package com.afa.devicer.orders.services;

import com.afa.devicer.core.bl.entities.BaseEntity;
import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.bl.repositories.OrderRepository;
import com.afa.devicer.core.errors.CoreException;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class OrderServiceTest {

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Test
    public void testOrderAdd() throws CoreException {

        SEOrder order = new SEOrder();
        order.setId(33L);
        order.setOrderNo(33L);
        order.setOrderDate(LocalDate.of(2024, 5, 16));
        order.setDateAdded(LocalDateTime.now());
        order.setDateModified(LocalDateTime.now());
        order.setRecStatus(BaseEntity.ACTIVE);

        Mockito.when(orderRepository.save(any(SEOrder.class))).thenReturn(order);
        Mockito.when(orderRepository.findById(33L)).thenReturn(Optional.of(order));

        SEOrder result = orderService.findById(33L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(order.getId(), result.getId());


    }


}

