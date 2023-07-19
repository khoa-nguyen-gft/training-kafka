# Kafka Server: Compression

Compression reduces the storage and network bandwidth requirements for messages, improving overall throughput and reducing storage costs.

- **`compression.type`**: Compression algorithm to apply to message payloads, such as gzip, snappy, or lz4.


```yaml
version: '3'
services:
  kafka:
    image: confluentinc/cp-kafka:latest
    container_name: kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_COMPRESSION_TYPE: gzip  # Compression algorithm for message payloads
    volumes:
       - "./.data/kafka_etc:/etc/kafka"
```
In this example, the Docker Compose file defines a service for Kafka. The compression configuration for Kafka is specified as an environment variable within the environment section. A comment is added after the compression type to provide an explanation of its purpose.

