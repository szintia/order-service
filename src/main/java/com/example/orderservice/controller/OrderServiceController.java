package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.service.ValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class OrderServiceController {
    public static final String ORDER_POST_URI = "/order";
    public static final String ORDERS_GET_URI = "/orders";

    private final ValidationService validationService;
    private final OrderService orderService;

    public OrderServiceController(ValidationService validationService, OrderService orderService) {
        this.validationService = validationService;
        this.orderService = orderService;
    }

    @Operation(summary = "Create a new validated order and add to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")}
    )
    @PostMapping(ORDER_POST_URI)
    Mono<Order> catchOrder(@RequestBody Order newOrder) {
        return validationService.validate(newOrder);
    }

    @Operation(summary = "Bulk operation to retrieve all orders.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Order cannot be found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")}
    )
    @GetMapping(ORDERS_GET_URI)
    Flux<Order> getAll() {
        return orderService.getAll();
    }
}
