package com.example.validationservice.service;

import com.example.validationservice.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaService {
    @Value(value = "${spring.kafka.topic}")
    private String topic = "orders";

    private KafkaTemplate<String, Object> kafkaTemplate;
    private ObjectMapper objectMapper;

    public KafkaService() {
    }

    @Autowired
    KafkaService(KafkaTemplate<String, Object> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    void sendMessage(Order order) {
        try {
            byte[] serializedObject = objectMapper.writeValueAsBytes(order);
            ProducerRecord<String, Object> record = new ProducerRecord<>(topic, serializedObject);
            kafkaTemplate.send(record);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
