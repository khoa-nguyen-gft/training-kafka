package com.love.kafka.message;

public class DemoMessage {
    public static final String TOPIC = "DEMO_01";

    private int id;

    public DemoMessage() {
    }
    public DemoMessage(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Demo01Message{" +
                "id=" + id +
                '}';
    }
}
