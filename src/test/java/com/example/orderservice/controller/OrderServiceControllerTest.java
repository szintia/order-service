package com.example.orderservice.controller;

import com.example.orderservice.OrderServiceApplication;
import com.example.orderservice.model.Customer;
import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.service.ValidationService;
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

import static com.example.orderservice.controller.OrderServiceController.ORDERS_GET_URI;
import static com.example.orderservice.controller.OrderServiceController.ORDER_POST_URI;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ActiveProfiles("test")
@AutoConfigureWebTestClient
@SpringBootTest(classes = {OrderServiceApplication.class, KafkaTestConfig.class})

public class OrderServiceControllerTest {

    @Autowired
    private OrderServiceController validationServiceController;

    @MockBean
    private ValidationService validationService;

    @MockBean
    private OrderService orderService;

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
                .uri(ORDER_POST_URI)
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
                .uri(ORDERS_GET_URI)
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
