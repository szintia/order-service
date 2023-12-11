# Order validation microservice with Java 17 and Spring Boot

This exercise is about to showcase a microservice ecosystem with Kafka Streams.
For the JPA based implementations I use H2 in memory DB.

Until this moment we have only an order validation service which exposes below REST interfaces:

## Methods      Urls	        Actions
    POST        /validate       create a new order in case it's valid
    GET         /orders         retrieve all orders
