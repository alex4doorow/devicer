package com.afa.devicer.core.bl.repositories;

import com.afa.devicer.core.bl.entities.BaseEntity;
import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.bl.entities.SEUser;
import com.afa.devicer.core.errors.CoreException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testOrder() throws CoreException {

        Optional<SEUser> optionalUser = userRepository.findById(1L);
        SEUser user;
        if (optionalUser.isEmpty()) {
            user = new SEUser();
            user.setId(1L);
            user.setUsername("test");
            user.setPassword("test");
            user.setEmail("test@test.com");
            user.setLastLogin(LocalDateTime.now());
            userRepository.save(user);
            user = userRepository.findById(1L)
                    .orElseThrow(() -> new CoreException("USER", "user not found", CoreException.THROWS));
        } else {
            user = userRepository
                    .findById(1L)
                    .orElseThrow(() -> new CoreException("USER", "user not found", CoreException.THROWS));
        }

        SEOrder order = new SEOrder();
        //order.setId(1L);
        order.setOrderNum(1L);
        order.setOrderDate(LocalDate.of(2024, 5, 15));
        order.setDateAdded(LocalDateTime.now());
        order.setDateModified(LocalDateTime.now());
        order.setRecStatus(BaseEntity.ACTIVE);
        order.setUserAdded(user);

        orderRepository.save(order);

        //Assertions.assertTrue(orderRepository.findById(2L).isPresent());

        orderRepository.findAll().forEach(o -> {
            log.info("order no: {}", o.getOrderNum());
        });
    }
}
