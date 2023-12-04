package com.example.validationservice.controller;

import com.example.validationservice.model.Order;
import com.example.validationservice.service.ValidationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationServiceController {
    private final ValidationService validationService;

    public ValidationServiceController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping("/validate")
    Order validateOrder(@RequestBody Order newOrder) {
        return validationService.validate(newOrder);
    }
}
