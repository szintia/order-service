package com.example.validationservice.service;

import com.example.validationservice.model.Order;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public Order validate(Order newOrder) {
        return newOrder;
    }
}
