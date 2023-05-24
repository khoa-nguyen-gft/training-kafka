# Kafka Server: Retention Policies

Retention policies govern how long Kafka retains messages and log segments, ensuring data availability and efficient resource utilization.

- **`log.retention.hours`** or **log.retention.ms**: Duration or time window for which Kafka retains log segments.
- **`log.retention.bytes`**: Maximum size in bytes that Kafka allows for log segments before triggering log segment rollover.

