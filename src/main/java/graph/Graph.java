package main.java.graph;

import main.java.City;
import main.java.Road;

import java.util.*;

public class Graph<TVertex extends Vertex, TEdge extends Edge> {

    private final Map<String, TVertex> vertices = new HashMap<>();
    private final Map<String, List<TEdge>> adjacencyList = new HashMap<>();


    public void addVertex(TVertex vertex) {
        vertices.put(vertex.getKey(), vertex);
        adjacencyList.put(vertex.getKey(), new ArrayList<TEdge>());
    }

    public void addEdge(TEdge edge) {
        TVertex vertex1 = vertices.get(edge.getVertex1Key());
        TVertex vertex2 = vertices.get(edge.getVertex2Key());

        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("Both vertices must exist in the graph");
        }

        adjacencyList.get(vertex1.getKey()).add(edge);
        adjacencyList.get(vertex2.getKey()).add(edge);
    }

    public TVertex findVertex(String key) {
        return vertices.get(key);
    }

    public TEdge findEdge(String vertexKey1, String vertexKey2) {
        List<TEdge> edges = adjacencyList.get(vertexKey1);
        if (edges != null) {
            for (TEdge edge : edges) {
                if ((edge.getVertex1Key().equals(vertexKey1) && edge.getVertex2Key().equals(vertexKey2)) ||
                        (edge.getVertex1Key().equals(vertexKey2) && edge.getVertex2Key().equals(vertexKey1))) {
                    return edge;
                }
            }
        }
        return null;  // Vrátí null, pokud hrana nebyla nalezena
    }

    public ArrayList<TEdge> getAllEdges() {
        HashSet<TEdge> allEdges = new HashSet<>();  // Použití HashSetu zamezí duplikacím
        for (List<TEdge> edges : adjacencyList.values()) {
            allEdges.addAll(edges);
        }
        return new ArrayList<>(allEdges);
    }

    public ArrayList<TVertex> getVertices() {
        return new ArrayList<>(vertices.values());
    }
}
