# Kafka Server: Broker Configurations

These configurations define the basic properties of Kafka brokers, such as their identities, network interfaces, and default behavior.

- **`broker.id`**: Unique identifier for each Kafka broker in a cluster.
- **`listeners`**: Network listeners to which Kafka binds and listens for client connections.
- **`advertised.listeners`**: Published addresses that clients can use to connect to Kafka brokers.
- **`num.partitions`**: Number of partitions for new topics if not specified explicitly.
- **`default.replication.factor`**: Default replication factor for new topics if not specified explicitly.


    ```yaml
    version: '3'
    services:
    kafka:
        image: confluentinc/cp-kafka:latest
        container_name: kafka
        ports:
        - "9093:9092"
        environment:
            KAFKA_BROKER_ID: 1  # Unique identifier for the Kafka broker
            KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092  # Network listeners for client connections
            KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka.example.com:9092  # Published addresses for clients to connect
            KAFKA_NUM_PARTITIONS: 3  # Number of partitions for new topics
            KAFKA_DEFAULT_REPLICATION_FACTOR: 2  # Default replication factor for new topics
        volumes:
        - ./.data/kafka:/var/lib/kafka

    ```

- In this example, the Docker Compose file defines a service for Kafka. Each configuration parameter is specified as an environment variable within the environment section. Comments are added after each parameter to provide explanations of their purpose. Additionally, a volume is mounted to persist the Kafka data in the host machine's ./.data/kafka directory.

