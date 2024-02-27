package com.example.validationservice.service;

import com.example.validationservice.model.Order;
import com.example.validationservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ValidationService {
    private OrderService orderService;

    public ValidationService() {
    }

    public ValidationService(OrderService orderService) {
        this.orderService = orderService;
    }

    public Mono<Order> validate(Order newOrder) {
        //TODO  validate order
        Order savedOrder = orderService.persistOrder(newOrder);

        return Mono.just(savedOrder);
    }
}
