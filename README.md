# Order validation microservice

This exercise is about to showcase a reactive microservice ecosystem with Kafka distributed event streaming platform. <br />
Using JDK 17 with Spring framework components (Core, Boot, Web, MVC, Cloud, WebFlux, Actuator, Data JPA). <br />
For the JPA based implementations I use H2 in memory DB but later I plan to replace it with Redis to access data reactively as well. <br />
REST API is created with Spring WebFlux, which is a fully non-blocking web stack - it can do more work with fewer resources - and supports back pressure. <br />
At the moment only the server side is implemented in a reactive way but later I will do the same for the client side with the help of WebClient. <br />

See more about reactive programming: https://reactivemanifesto.org/

Until this moment we have only an order validation service which exposes below REST interfaces:

# API Endpoints
### Methods      Urls	        Actions
    POST        /validate       create a new order in case it's valid
    GET         /orders         retrieve all orders

# Technical details:

Local port is set to 8081

After running the application, you can find
- OpenApi descriptions at:
http://localhost:8081/api-docs
- Swagger UI at:
http://localhost:8081/swagger-ui/index.html#/

# Setting up Kafka on Windows

1. Download Kafka (includes Zookeeper): https://www.apache.org/dyn/closer.cgi?path=/kafka/
2. After extracting, start Zookeeper service via zookeeper-server-start.bat command.
   
   ```D:\kafka\kafka_2.13-3.6.1>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties```
   By default it listens to port 2181.
   ```console
       [2023-12-14 16:15:31,174] INFO clientPortAddress is 0.0.0.0:2181 (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
   ```
4. Start Kafka via kafka-server-start.bat command in a new cmd:

   ```D:\kafka\kafka_2.13-3.6.1>.\bin\windows\kafka-server-start.bat .\config\server.properties```

   It's connected to Zookeeper:
```console
   [2023-12-14 16:17:38,748] INFO Connecting to zookeeper on localhost:2181 (kafka.server.KafkaServer)

   [2023-12-14 16:17:40,014] INFO Registered broker 0 at path /brokers/ids/0 with addresses: PLAINTEXT://3B1811282:9092, czxid (broker epoch): 25 (kafka.zk.KafkaZkClient)
```
   Default port is 9092. In the command prompt we can see all the configurations.

4. Let's create a topic, open a new cmd and use the kafka-topics.bat command:

   ```D:\kafka\kafka_2.13-3.6.1>.\bin\windows\kafka-topics.bat --create --topic orders --bootstrap-server localhost:9092```
    ```console
   Created topic orders.
    ```

5. To get some info about the partition, replication, use kafka-topics.bat command with --describe:
   
   ```D:\kafka\kafka_2.13-3.6.1>.\bin\windows\kafka-topics.bat --describe --topic orders --bootstrap-server localhost:9092```
   ```console
   Topic: orders   TopicId: F2JeuSm2Rw2qquuGL7RWgg PartitionCount: 1       ReplicationFactor: 1    Configs:
   Topic: orders   Partition: 0    Leader: 0       Replicas: 0     Isr: 0
   ```

6. Testing Producer via kafka-console-producer.bat command
   
   ```D:\kafka\kafka_2.13-3.6.1>.\bin\windows\kafka-console-producer.bat --topic orders --bootstrap-server localhost:9092```
After that we can add some events.
 ```console
    >Testing producer with an event
 ```

7. Testing Consumer side, let's see what we have:
   
   ``` D:\kafka\kafka_2.13-3.6.1>.\bin\windows\kafka-console-consumer.bat --topic orders --from-beginning --bootstrap-server localhost:9092```
 ```console
    Testing producer with an event
 ```
More info can be found on the following Apache Kafka site: https://kafka.apache.org/quickstart#quickstart_send
</details>
   

# Sending message to Kafka topic

After the setup of Kafka we can now send messages to the topic called "orders" via Swagger UI - POST HTTP method.

![image](https://github.com/szintia/validation-service/assets/8359566/1d567e27-781c-4a55-ac5e-2600755cc279)

# Building and verifying Dockerfile
NOTE - Kafka configuration is not added yet to the Dockerfile <br />
To create our own Docker image, the executable artifact from the validation service, we need to
1. build the Dockerfile (committed to the project) with a tag 1.0 and with a name "my-app":<br />
   ``` docker build -t my-app:1.0 .```<br />
   Image is built:<br />
   ![image](https://github.com/szintia/validation-service/assets/8359566/e11d0dc5-4e56-417d-80be-cdeb066a895e)
   ![image](https://github.com/szintia/validation-service/assets/8359566/fc548ade-6e49-4a6e-bb3d-f238b4e18b2a)

3. verify our validation service starts successfully -> running Docker image in a container <br />
   ```docker run -d -p 8081:8081 my-app:1.0``` -> run application in a container with port binding, our app listens to port 8081 same as container's port<br />
   ```docker ps ```  -> list running containers<br />
![image](https://github.com/szintia/validation-service/assets/8359566/1f110f5a-33b5-4aaa-a19a-bcfc49a451d7) <br />
Swagger UI is now available on port 8081:
![image](https://github.com/szintia/validation-service/assets/8359566/8718d33e-470b-4106-9c2b-824e8c7aa69e)



