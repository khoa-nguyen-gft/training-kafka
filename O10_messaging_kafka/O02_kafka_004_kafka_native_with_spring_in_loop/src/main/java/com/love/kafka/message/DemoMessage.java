package com.love.kafka.message;

public class DemoMessage {
    public static final String TOPIC = "TOPIC_1";

    private int id;

    private String content;


    public DemoMessage() {
    }

    public DemoMessage(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DemoMessage{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
