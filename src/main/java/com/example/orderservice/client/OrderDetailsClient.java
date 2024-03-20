package com.example.orderservice.client;

import com.example.orderservice.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url="${url:http://localhost:8080}", name = "order-details-service")
public interface OrderDetailsClient {

    @PostMapping("/order-details")
    Order validate(@RequestBody Order order);
}
