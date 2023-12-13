# Order validation microservice with Java 17 and Spring Boot

This exercise is about to showcase a microservice ecosystem with Kafka Streams.
For the JPA based implementations I use H2 in memory DB. 
The REST application is reactive in order to be able to handle massive numbers of concurrent connections.
Later I plan to replace H2 with Redis to access data reactively as well.

Until this moment we have only an order validation service which exposes below REST interfaces:

### Methods      Urls	        Actions
    POST        /validate       create a new order in case it's valid
    GET         /orders         retrieve all orders

### Technical details:

Local port is set to 8081

After running the application, you can find
- OpenApi descriptions at:
http://localhost:8081/api-docs
- Swagger UI at:
http://localhost:8081/swagger-ui/index.html#/

