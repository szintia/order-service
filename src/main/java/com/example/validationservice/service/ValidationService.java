package com.example.validationservice.service;

import com.example.validationservice.model.Order;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ValidationService {
    private OrderService orderService;

    public ValidationService() {
    }

    public ValidationService(OrderService orderService) {
        this.orderService = orderService;
    }

    public Mono<Order> validate(Order order) {
        if (order.getCustomer() == null) {
            return null;
        }

        Order savedOrder = orderService.persistOrder(order);

        return Mono.just(savedOrder);
    }
}
