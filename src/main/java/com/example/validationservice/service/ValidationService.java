package com.example.validationservice.service;

import com.example.validationservice.model.Order;
import com.example.validationservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationService {
    @Autowired
    private OrderRepository orderRepository;

    public Order validate(Order newOrder) {
        return newOrder;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
