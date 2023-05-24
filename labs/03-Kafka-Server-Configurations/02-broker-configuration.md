# Kafka Server: Broker Configurations

These configurations define the basic properties of Kafka brokers, such as their identities, network interfaces, and default behavior.

- **`broker.id`**: Unique identifier for each Kafka broker in a cluster.
- **`listeners`**: Network listeners to which Kafka binds and listens for client connections.
- **`advertised.listeners`**: Published addresses that clients can use to connect to Kafka brokers.
- **`num.partitions`**: Number of partitions for new topics if not specified explicitly.
- **`default.replication.factor`**: Default replication factor for new topics if not specified explicitly.


