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
      # Replication factor for the internal offsets topic, which stores committed consumer offsets.
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 3
      # Replication factor for the transaction state log, used for storing transactional metadata.
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 2
    volumes:
       - "./.data/kafka_etc:/etc/kafka"

```

In this example, the Docker Compose file defines a service for Kafka. Each configuration parameter is specified as an environment variable within the environment section. Comments are added after each parameter to provide explanations of their purpose. Additionally, a volume is mounted to persist the Kafka data in the host machine's ./.data/kafka directory.

