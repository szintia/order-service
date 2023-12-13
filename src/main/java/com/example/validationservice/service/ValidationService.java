package com.example.validationservice.service;

import com.example.validationservice.model.Order;
import com.example.validationservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ValidationService {
    @Autowired
    private OrderRepository orderRepository;

    public Mono<Order> validate(Order newOrder) {
        return Mono.just(orderRepository.save(newOrder));
    }

    public Flux<Order> getAll() {
        return Flux.fromIterable(orderRepository.findAll());
    }
}
