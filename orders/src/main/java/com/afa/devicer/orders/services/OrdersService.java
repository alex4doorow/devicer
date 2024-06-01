package com.afa.devicer.orders.services;

import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.bl.entities.SEUser;
import com.afa.devicer.core.bl.repositories.OrderRepository;
import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.model.types.ENMessageStatuses;
import com.afa.devicer.core.rest.dto.DtoOrderMessage;
import com.afa.devicer.core.services.JsonMapper;
import com.afa.devicer.core.services.converters.in.InDtoOrderConverter;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
public class OrdersService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InDtoOrderConverter orderConverter;
    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private OrdersKafkaProducerService ordersKafkaProducerService;

    public SEOrder findById(Long id) throws CoreException {
        SEOrder order = orderRepository
                .findById(id)
                .orElseThrow(() -> new CoreException("ORDER", "order not found", CoreException.THROWS));
        log.info("order: {}", order);
        return order;
    }

    public Long add(DtoOrderMessage dtoOrderMessage, SEUser user) throws CoreException {
        log.info("add: {}", dtoOrderMessage);

        SEOrder order = orderConverter.convertTo(dtoOrderMessage.getOrder());
        order.setUserAdded(user);

        try {
            SEOrder resultOrder = orderRepository.save(order);
            dtoOrderMessage.getOrder().setId(resultOrder.getId());
            dtoOrderMessage.getState().setStatus(ENMessageStatuses.CREATED);

            jsonMapper.toJSON(dtoOrderMessage, CoreException.THROWS);

            ordersKafkaProducerService.sendMessage("orders.dispatcher", jsonMapper.toJSON(dtoOrderMessage, CoreException.THROWS));

            return resultOrder.getId();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
