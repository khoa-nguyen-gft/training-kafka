# Kafka Server: Message Size Limits

These configurations define the upper limits on the size of individual messages and replica fetches, preventing excessive resource usage.

- **`message.max.bytes`**: Maximum size in bytes allowed for a single message.
- **`replica.fetch.max.bytes`**: Maximum size in bytes for a replica to fetch from a leader.


```yaml
version: '3'
services:
  kafka:
    image: confluentinc/cp-kafka:latest
    container_name: kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_MESSAGE_MAX_BYTES: 1048576  # Maximum size in bytes for a single message
      KAFKA_REPLICA_FETCH_MAX_BYTES: 52428800  # Maximum size in bytes for a replica to fetch from a leader
    volumes:
       - "./.data/kafka_etc:/etc/kafka"
```

In this example, the Docker Compose file defines a service for Kafka. The message size limits for Kafka are specified as environment variables within the environment section. Comments are added after each message size limit to provide explanations of their purpose.

