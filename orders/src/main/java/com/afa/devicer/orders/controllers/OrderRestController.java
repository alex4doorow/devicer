package com.afa.devicer.orders.controllers;

import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.rest.controllers.BaseRestController;
import com.afa.devicer.core.rest.dto.DtoOrder;
import com.afa.devicer.orders.controllers.api.OrderRestApi;
import com.afa.devicer.orders.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/orders/v1/orders")
@Slf4j
public class OrderRestController extends BaseRestController implements OrderRestApi {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOrderById(@PathVariable Long id) {
        log.info("[START] {} request: {}", "FIND", id);

        try {
            SEOrder order = orderService.findById(id);
            //DtoOrder dtoOrder = outDtoOrderConverter.convertTo(order);
            DtoOrder dtoOrder = DtoOrder.builder()
                    .id(order.getId())
                    .orderNum(order.getOrderNo())
                    .orderDate(order.getOrderDate())
                    .build();
            return response("findById", dtoOrder, false);
        } catch (Exception e) {
            return errorResponse("findById", e);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<DtoOrder> addOrder(@RequestBody String body) {

        DtoOrder resultDtoOrder = DtoOrder.builder()
                .id(1L)
                .orderNum(1L)
                .orderDate(LocalDate.now())
                .build();

        return new ResponseEntity<>(resultDtoOrder, HttpStatus.OK);
    }

    private ResponseEntity<Object> response(String msgInType, DtoOrder response, boolean isError) {
        log.info("[END] {} response:\n{}", msgInType, response);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(response, httpHeaders, (isError) ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
}
