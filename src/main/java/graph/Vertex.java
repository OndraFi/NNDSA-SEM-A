package main.java.graph;

public abstract class Vertex {
    String key;
    Location location;

    public Vertex() {
        this.key = this.generateKey();
    }

    public String getKey() {
        return this.key;
    }

    private String generateKey() {
        return "abc"; // TODO
    }

}