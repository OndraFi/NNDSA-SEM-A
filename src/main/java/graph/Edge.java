package main.java.graph;

public abstract class Edge {
    String vertex1Key;
    String vertex2Key;
    int value;
    Boolean accessible;
    
    public Edge(String vertex1Key,String  vertex2Key, int value) {
        this.vertex1Key = vertex1Key;
        this.vertex2Key = vertex2Key;
        this.value = value;
        this.accessible = true;
    }

    public int getValue() {
        return value;
    }

    public Boolean getAccessible() {
        return accessible;
    }

    public void blockEdge(){
        this.accessible = false;
    }

    public String getVertex1Key() {
        return vertex1Key;
    }

    public String getVertex2Key() {
        return vertex2Key;
    }
}
