package main.java.graph;

import java.util.*;

public class Graph<TVertex extends Vertex,TEdge extends Edge> {

    private final Map<String, TVertex> vertices = new HashMap<>();

    public void addVertex(TVertex vertex) {
        vertices.put(vertex.getKey(), vertex);
    }

    public void addEdge(TEdge edge) {


    }

    public TVertex findVertex(String key) {
        return vertices.get(key);
    }

    public TEdge findEdge(String key) {
        return null;
    }

}
