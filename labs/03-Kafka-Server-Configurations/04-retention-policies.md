# Kafka Server: Retention Policies

Retention policies govern how long Kafka retains messages and log segments, ensuring data availability and efficient resource utilization.

- **`log.retention.hours`** or **log.retention.ms**: Duration or time window for which Kafka retains log segments.
- **`log.retention.bytes`**: Maximum size in bytes that Kafka allows for log segments before triggering log segment rollover.

```yaml
version: '3'
services:
  kafka:
    image: confluentinc/cp-kafka:latest
    container_name: kafka
    ports:
      - "9093:9092"
    environment:
      KAFKA_LOG_RETENTION_HOURS: 168  # Retention duration in hours for log segments
      KAFKA_LOG_RETENTION_BYTES: 1073741824  # Maximum size in bytes for log segments
    volumes:
       - "./.data/kafka_etc:/etc/kafka"
```

In this example, the Docker Compose file defines a service for Kafka. The retention policies for Kafka are specified as environment variables within the environment section. Comments are added after each retention policy to provide explanations of their purpose.

