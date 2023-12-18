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
<details>
<summary><b> Setting up Kafka on Windows</b></summary>

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
   
   
   

