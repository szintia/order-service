package com.example.validationservice.controller;

import com.example.validationservice.ValidationServiceApplication;
import com.example.validationservice.model.Customer;
import com.example.validationservice.model.Order;
import com.example.validationservice.service.ValidationService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ActiveProfiles("test")
@AutoConfigureWebTestClient
@SpringBootTest(classes = {ValidationServiceApplication.class, KafkaTestConfig.class})

public class ValidationServiceControllerTest {

    @Autowired
    private ValidationServiceController validationServiceController;

    @MockBean
    private ValidationService validationService;

    private WebTestClient webTestClient;
    private Order order;

    @BeforeEach
    void beforeEach() {
        webTestClient = WebTestClient.bindToController(validationServiceController).build();

        order = getOrder();
        given(validationService.validate(order)).willReturn(Mono.just(order));
    }

    @Test
    void givenValidPostRequest_validateOrder_ReturnSuccess() {
        //GIVEN

        //WHEN
        webTestClient
                .post()
                .uri("/validate")
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromValue(order))
                .exchange()
                //THEN
                .expectStatus().isOk();
    }

    @Test
    void givenValidGetRequest_retrieveAllOrders_ReturnSuccess() {
        //GIVEN

        //WHEN
        webTestClient
                .get()
                .uri("/orders")
                .exchange()
                //THEN
                .expectStatus().isOk();
    }

    @NotNull
    private static Order getOrder() {
        Order order = new Order();
        order.setId(1);
        order.setDate(LocalDate.now());

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("John");
        customer.setLastName("Connor");

        order.setCustomer(customer);
        return order;
    }
}
