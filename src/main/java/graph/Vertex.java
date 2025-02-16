package main.java.graph;

import java.util.UUID;

public abstract class Vertex {
    String key;
    Location location;

    public Vertex(int x , int y) {
        this.key = this.generateKey();
        this.location = new Location(x,y);
    }

    public String getKey() {
        return this.key;
    }

    public Location getLocation() {
        return location;
    }

    private String generateKey() {
        return UUID.randomUUID().toString();
    }

}