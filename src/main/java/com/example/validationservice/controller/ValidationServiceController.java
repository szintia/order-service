package com.example.validationservice.controller;

import com.example.validationservice.model.Order;
import com.example.validationservice.service.OrderService;
import com.example.validationservice.service.ValidationService;
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
public class ValidationServiceController {
    private final ValidationService validationService;
    private final OrderService orderService;

    public ValidationServiceController(ValidationService validationService, OrderService orderService) {
        this.validationService = validationService;
        this.orderService = orderService;
    }

    @Operation(summary = "Create a new validated order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")}
    )
    @PostMapping("/validate")
    Mono<Order> validateOrder(@RequestBody Order newOrder) {
        return validationService.validate(newOrder);
    }

    @Operation(summary = "Retrieve all orders.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Order cannot be found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")}
    )
    @GetMapping("/orders")
    Flux<Order> getAll() {
        return orderService.getAll();
    }
}
