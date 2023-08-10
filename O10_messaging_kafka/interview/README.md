## [Main title](../README.md)

# Apache Kafka
+ [What is Apache Kafka?](#mention-what-is-apache-kafka)
+ [What is the traditional method of message transfer?](#Mention-what-is-the-traditional-method-of-message-transfer)


## Mention what is Apache Kafka?
Apache Kafka is a publish-subscribe messaging system developed by Apache written in Scala. It is a distributed, partitioned and replicated log service.

![Alt text](./images/Mention%20what%20is%20Apache%20Kafka.png)

[Table of Contents](#apache-kafka)

## Mention what is the traditional method of message transfer?
The traditional method of message transfer includes two methods
+ Queuing(Point-to-point): In a queuing, a pool of consumers may read message from the server and each message goes to one of them
+ Publish-Subscribe: In this model, messages are broadcasted to all consumers

![Alt text](images/Mention%20what%20is%20the%20traditional%20method%20of%20message%20transfer.png)

Kafka caters single consumer abstraction that generalized both of the above- the consumer group.

[Table of Contents](#apache-kafka)


