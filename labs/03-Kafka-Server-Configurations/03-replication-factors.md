# Kafka Server: Replication Factors

Replication factors ensure data durability and fault tolerance by determining the number of replicas for Kafka's internal topics.

- **`offsets.topic.replication.factor`**: Replication factor for the internal offsets topic, which stores committed consumer offsets.
- **`transaction.state.log.replication.factor`**: Replication factor for the transaction state log, used for storing transactional metadata.

