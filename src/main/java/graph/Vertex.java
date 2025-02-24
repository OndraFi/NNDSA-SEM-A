package main.java.graph;

import java.util.UUID;

public abstract class Vertex {
    String key;

    public String getKey() {
        return this.key;
    }

    private String generateKey() {
        return UUID.randomUUID().toString();
    }

}