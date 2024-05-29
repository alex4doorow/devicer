package com.afa.devicer.orders.controllers;

import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.errors.CoreException;
import com.afa.devicer.core.rest.controllers.BaseRestController;
import com.afa.devicer.core.rest.dto.DtoOrder;
import com.afa.devicer.core.rest.dto.DtoOrderMessage;
import com.afa.devicer.core.services.JsonMapper;
import com.afa.devicer.core.services.UserService;
import com.afa.devicer.orders.controllers.api.OrdersRestApi;
import com.afa.devicer.orders.services.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/orders/v1/orders")
@Slf4j
public class OrdersRestController extends BaseRestController implements OrdersRestApi {

    private UserService userService;
    private OrdersService orderService;
    private JsonMapper jsonMapper;

    @Autowired
    public void setOrderService(OrdersService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setJsonMapper(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    //@GetMapping("/{id}")
    public ResponseEntity<Object> findOrderById(@PathVariable Long id) {
        log.info("[START] {} request: {}", "FIND", id);

        try {
            SEOrder order = orderService.findById(id);
            //DtoOrder dtoOrder = outDtoOrderConverter.convertTo(order);
            DtoOrder dtoOrder = DtoOrder.builder()
                    .id(order.getId())
                    .orderNum(order.getOrderNum())
                    .orderDate(order.getOrderDate())
                    .build();
            return response("findById", dtoOrder, false);
        } catch (Exception e) {
            return errorResponse("findById", e);
        }
    }

    //@PostMapping("/add")
    public ResponseEntity<Object> addOrder(@RequestBody String message) {

        try {
            DtoOrderMessage dtoOrderMessage = jsonMapper.fromJSON(message, DtoOrderMessage.class);
            Long id = orderService.add(dtoOrderMessage, userService.getCurrentUser());

            dtoOrderMessage.getOrder().setId(id);
            dtoOrderMessage.getState().setStatus("SUSPEND");

            String kafkaMessage = jsonMapper.toJSON(dtoOrderMessage, CoreException.THROWS);

            return response("add", dtoOrderMessage, false);
        } catch (CoreException e) {
            return errorResponse("add", e);
        }
    }

    private ResponseEntity<Object> response(String msgInType, Object response, boolean isError) {
        log.info("[END] {} response:\n{}", msgInType, response);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(response, httpHeaders, (isError) ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
}
