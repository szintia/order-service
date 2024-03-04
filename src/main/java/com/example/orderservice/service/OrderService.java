package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private KafkaService kafkaService;

    public OrderService() {
    }

    @Autowired
    public OrderService(OrderRepository orderRepository, KafkaService kafkaService) {
        this.orderRepository = orderRepository;
        this.kafkaService = kafkaService;
    }

    public Order persistOrder(Order newOrder) {
        Order savedOrder = orderRepository.save(newOrder);
        kafkaService.sendMessage(newOrder);
        return savedOrder;
    }

    public Flux<Order> getAll() {
        return Flux.fromIterable(orderRepository.findAll());
    }
}
