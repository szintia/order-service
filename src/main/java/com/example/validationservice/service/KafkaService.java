package com.example.validationservice.service;

import com.example.validationservice.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaService {
    private static final String ORDERS_TOPIC = "orders";

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    KafkaService(KafkaTemplate kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    void sendMessage(Order order) {
        try {
            byte[] serializedObject = objectMapper.writeValueAsBytes(order);
            ProducerRecord<String, Object> record = new ProducerRecord<>(ORDERS_TOPIC, serializedObject);
            kafkaTemplate.send(record);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
