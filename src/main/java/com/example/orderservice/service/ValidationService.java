package com.example.orderservice.service;

import com.example.orderservice.client.OrderDetailsClient;
import com.example.orderservice.model.Order;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ValidationService {
    private static final Logger log = LoggerFactory.getLogger(ValidationService.class);
    private OrderService orderService;
    private OrderDetailsClient orderDetailsClient;
    private Tracer tracer;

    public ValidationService() {
    }

    public ValidationService(OrderService orderService, OrderDetailsClient orderDetailsClient, Tracer tracer) {
        this.orderService = orderService;
        this.orderDetailsClient = orderDetailsClient;
        this.tracer = tracer;
    }

    public Mono<Order> validate(Order order) {
        Span span = this.tracer.nextSpan();

        try (Tracer.SpanInScope ws = this.tracer.withSpan(span.start())) {
            log.info("<TRACE:{}> ", this.tracer.currentSpan().context().traceId());

            if (order.getCustomer() == null) {
                return null;
            }
            Order savedOrder = orderService.persistOrder(order);
            return Mono.just(savedOrder);
        } finally {
            span.end();
        }
    }
}
