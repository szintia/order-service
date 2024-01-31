package com.example.validationservice.service;

import com.example.validationservice.model.Order;
import com.example.validationservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ValidationService {
    private final OrderRepository orderRepository;
    private final KafkaService kafkaService;

    @Autowired
    ValidationService(OrderRepository orderRepository, KafkaService kafkaService) {
        this.orderRepository = orderRepository;
        this.kafkaService = kafkaService;
    }

    public Mono<Order> validate(Order newOrder) {
        kafkaService.sendMessage(newOrder);
        return Mono.just(orderRepository.save(newOrder));
    }

    public Flux<Order> getAll() {
        return Flux.fromIterable(orderRepository.findAll());
    }
}
