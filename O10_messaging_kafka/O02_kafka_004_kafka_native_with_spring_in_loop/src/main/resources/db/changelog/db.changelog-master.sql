CREATE TABLE MESSAGES_TOPIC_AT_MOST_ONCE(
        id INT PRIMARY KEY,
        content VARCHAR(255),
        created_at timestamp default current_timestamp,
        updated_at timestamp default current_timestamp
);