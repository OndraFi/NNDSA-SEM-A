package main.java;

public abstract class Vertex {
    private String key;

    public Vertex() {
        this.generateUniqueKey();
    }

    public String getKey() {
        return this.key;
    }

    private void generateUniqueKey(){
        this.key = "abc";
    }
}
