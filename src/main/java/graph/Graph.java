package main.java.graph;

import main.java.City;
import main.java.Road;

import java.util.*;

public class Graph<K,TVertex, TEdge> {

    private final Map<K, Vertex> vertices = new HashMap<>();
    private final List<Edge> edges = new ArrayList<>();

    public class Vertex {
        private final K key;
        private final TVertex data;
        private final List<Edge>  adjencyList = new ArrayList<>();

        public Vertex(K key, TVertex data) {
            this.data = data;
            this.key = key;
        }

        public K getKey() {
            return this.key;
        }

        public TVertex getData() {
            return data;
        }

        public void addEdge(Edge edge) {
            this.adjencyList.add(edge);
        }

        public List<Edge> getAdjacentEdges() {
            return this.adjencyList;
        }

    }
    public class Edge {
        K vertex1Key;
        K vertex2Key;
        TEdge data;

        public Edge(K vertex1Key,K vertex2Key, TEdge data) {
            this.vertex1Key = vertex1Key;
            this.vertex2Key = vertex2Key;
            this.data = data;
        }

        public TEdge getData() {
            return data;
        }

        public K getVertex1Key() {
            return vertex1Key;
        }

        public K getVertex2Key() {
            return vertex2Key;
        }
    }

    public Map<K, Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addVertex(K key, TVertex data) {
        Vertex vertex = new Vertex(key,data);
        vertices.put(vertex.getKey(), vertex);
    }

    public void addEdge(K vertex1Key, K vertex2Key, TEdge data) throws IllegalArgumentException {
        Edge edge = new Edge(vertex1Key, vertex2Key, data);
        Vertex vertex1 = vertices.get(edge.getVertex1Key());
        Vertex vertex2 = vertices.get(edge.getVertex2Key());

        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("Both vertices must exist in the graph");
        }

        vertex1.addEdge(edge);
        vertex2.addEdge(edge);
        edges.add(edge);
    }

    public Vertex getVertex(K key) {
        return vertices.get(key);
    }

    public TEdge getEdge(K vertexKey1, K vertexKey2) throws NoSuchElementException, IllegalArgumentException {
        Vertex vertex1 = findVertex(vertexKey1);
        Vertex vertex2 = findVertex(vertexKey2);

        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("Both vertices must exist in the graph");
        }

        List<Edge> edges = vertex1.getAdjacentEdges();
        for (Edge edge : edges) {
            if ((edge.getVertex1Key().equals(vertexKey1) && edge.getVertex2Key().equals(vertexKey2)) ||
                    (edge.getVertex1Key().equals(vertexKey2) && edge.getVertex2Key().equals(vertexKey1))) {
                return edge.getData();
            }
        }

        throw new NoSuchElementException("Edge not found between the specified vertices");
    }

    private Vertex findVertex(K key) {
        return vertices.get(key);
    }
}
