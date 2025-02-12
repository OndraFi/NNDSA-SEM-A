package main.java;

import main.java.intefaces.IGraph;

import java.util.*;

public class Graph<EVertex,EEdge> implements IGraph<EVertex,EEdge> {

    public class Vertex {
        String key;
        EVertex data;

        public Vertex(EVertex data) {
            this.data = data;
            this.key = this.generateKey();
        }
        public String getKey() {
            return this.key;
        }
        private String generateKey(){
            return "abc"; // TODO
        }

        public EVertex getData() {
            return this.data;
        }
    }

    private class Edge {
        Vertex[] vertices;
        String key;
        EEdge data;
    }

    private final Map<String, Vertex> vertices = new HashMap<>();

    @Override
    public void addVertex(EVertex data) {
        Vertex v = new Vertex(data);
        vertices.put(v.getKey(), v);
    }

    @Override
    public void addEdge(EEdge e) {

    }

    @Override
    public Vertex findVertex(String key) {
        return vertices.get(key);
    }

    @Override
    public EEdge findEdge(String key) {
        return null;
    }

}
