package main.java.graph;

import main.java.City;
import main.java.Road;

import java.util.*;

public class Graph<TVertex, TEdge> {

    private final Map<String, Vertex> vertices = new HashMap<>();
//    private final Map<String, List<TEdge>> adjacencyList = new HashMap<>();


    private class Vertex<TVertex> {
        private String key;
        private TVertex data;
        private final List<Edge>  adjencyList = new ArrayList<>();

        public Vertex(TVertex data) {
            this.data = data;
        }

        public String getKey() {
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

        private String generateKey() {
            return UUID.randomUUID().toString();
        }


    }
    private class Edge<TEdge> {
        String vertex1Key;
        String vertex2Key;
        int weight;
        Boolean accessible;
        TEdge data;

        public Edge(String vertex1Key,String  vertex2Key, int weight, TEdge data) {
            this.vertex1Key = vertex1Key;
            this.vertex2Key = vertex2Key;
            this.weight = weight;
            this.accessible = true;
            this.data = data;
        }

        public int getWeight() {
            return weight;
        }

        public Boolean isAccessible() {
            return accessible;
        }

        public TEdge getData() {
            return data;
        }

        public void blockEdge(){
            this.accessible = false;
        }

        public void unblockEdge(){
            this.accessible = true;
        }

        public String getVertex1Key() {
            return vertex1Key;
        }

        public String getVertex2Key() {
            return vertex2Key;
        }
    }

    public void addVertex(TVertex data) {
        Vertex vertex = new Vertex(data);
        vertices.put(vertex.getKey(), vertex);
//        adjacencyList.put(vertex.getKey(), new ArrayList<TEdge>());
    }

    public void addEdge(String vertex1Key, String vertex2Key, Integer weight, TEdge data) {
        Edge edge = new Edge(vertex1Key, vertex2Key, weight, data);
        Vertex vertex1 = vertices.get(edge.getVertex1Key());
        Vertex vertex2 = vertices.get(edge.getVertex2Key());

        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("Both vertices must exist in the graph");
        }

        vertex1.addEdge(edge);
        vertex2.addEdge(edge);
//        adjacencyList.get(vertex1.getKey()).add(edge);
//        adjacencyList.get(vertex2.getKey()).add(edge);
    }

    public Vertex findVertex(String key) {
        return vertices.get(key);
    }

    public Edge findEdge(String vertexKey1, String vertexKey2) {

        Vertex vertex1 = findVertex(vertexKey1);
        Vertex vertex2 = findVertex(vertexKey2);

        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("Both vertices must exist in the graph");
        }

        List<Edge> edges = vertex1.getAdjacentEdges();
        for (Edge edge : edges) {
            if ((edge.getVertex1Key().equals(vertexKey1) && edge.getVertex2Key().equals(vertexKey2)) ||
                    (edge.getVertex1Key().equals(vertexKey2) && edge.getVertex2Key().equals(vertexKey1))) {
                return edge;
            }
        }

        return null;  // Vrátí null, pokud hrana nebyla nalezena
    }

//    public ArrayList<TEdge> getAllEdges() {
//        HashSet<TEdge> allEdges = new HashSet<>();  // Použití HashSetu zamezí duplikacím
//        for (List<TEdge> edges : adjacencyList.values()) {
//            allEdges.addAll(edges);
//        }
//        return new ArrayList<>(allEdges);
//    }
//
//    public ArrayList<TVertex> getVertices() {
//        return new ArrayList<>(vertices.values());
//    }
}
