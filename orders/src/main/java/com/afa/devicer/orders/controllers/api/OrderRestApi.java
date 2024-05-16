package com.afa.devicer.orders.controllers.api;

import com.afa.devicer.core.rest.dto.DtoOrder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Orders", description = "the orders Api")
public interface OrderRestApi {


    @Operation(summary = "Find order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    ResponseEntity<Object> findOrderById(@PathVariable Long id);

    @Operation(summary = "Add a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    ResponseEntity<DtoOrder> addOrder(@RequestBody String body);
}
