# Kafka Server: Replication Factors

Replication factors ensure data durability and fault tolerance by determining the number of replicas for Kafka's internal topics.

- **`offsets.topic.replication.factor`**: Replication factor for the internal offsets topic, which stores committed consumer offsets.
- **`transaction.state.log.replication.factor`**: Replication factor for the transaction state log, used for storing transactional metadata.

```yaml
version: '3'
services:
  kafka:
    image: confluentinc/cp-kafka:latest
    container_name: kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1  # Unique identifier for the Kafka broker
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092  # Network listeners for client connections
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka.example.com:9092  # Published addresses for clients to connect
      KAFKA_NUM_PARTITIONS: 3  # Number of partitions for new topics
      KAFKA_DEFAULT_REPLICATION_FACTOR: 2  # Default replication factor for new topics
      volumes:
       - "./.data/kafka_etc:/etc/kafka"

```

In this example, the Docker Compose file defines a service for Kafka. Each configuration parameter is specified as an environment variable within the environment section. Comments are added after each parameter to provide explanations of their purpose. Additionally, a volume is mounted to persist the Kafka data in the host machine's ./.data/kafka directory.

