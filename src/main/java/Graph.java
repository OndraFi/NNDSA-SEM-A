package main.java;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class Graph<Vertex,E> {

    Dictionary<String, Vertex> vertices = new Hashtable<>();    private List<E> edges;

    public void addVertex(Vertex v) {
        vertices.put(v.getKey(), v);
    }

    public void addEdge(E e) {

    }

    public Vertex findVertex(String key) {
        return vertices.get(key);
    }

    public E findEdge(String key) {
        return null;
    }

}
