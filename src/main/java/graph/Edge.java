package main.java.graph;

public abstract class Edge {
    Vertex[] vertices;
    String key;
    int value;
    Boolean accessible;
    
    public Edge(Vertex[] vertices, String key, int value) {
        this.vertices = vertices;
        this.key = key;
        this.value = value;
        this.accessible = true;
    }

    public void blockEdge(){
        this.accessible = false;
    }

}
