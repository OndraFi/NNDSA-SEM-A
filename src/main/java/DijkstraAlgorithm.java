package main.java;

import main.java.graph.Graph;

import java.util.*;

public class DijkstraAlgorithm {
    // Dijkstra's algorithm to find shortest path from s to all other nodes

    public static Map<String, Map<String, String>> dijkstra(Graph<String, City, Road> graph, String startVertexKey) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        Map<String, Map<String, String>> successors = new HashMap<>();

        // Priority queue to determine the vertex with the shortest distance
        PriorityQueue<Graph<String, City, Road>.Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(vertex -> distances.getOrDefault(vertex.getKey(), Integer.MAX_VALUE)));

        // Initialize distances and previous
        for (Graph<String, City, Road>.Vertex vertex : graph.getVertices().values()) {
            distances.put(vertex.getKey(), Integer.MAX_VALUE);
            previous.put(vertex.getKey(), null);
            successors.put(vertex.getKey(), new HashMap<>());
        }

        // Set the distance from the start vertex to itself to 0
        distances.put(startVertexKey, 0);
        queue.add(graph.getVertex(startVertexKey));

        while (!queue.isEmpty()) {
            Graph<String, City, Road>.Vertex u = queue.poll();

            // Explore each adjacent vertex of u
            for (Graph<String, City, Road>.Edge edge : u.getAdjacentEdges()) {
                Graph<String, City, Road>.Vertex v = graph.getVertex(edge.getVertex2Key().equals(u.getKey()) ? edge.getVertex1Key() : edge.getVertex2Key());
                int alt = distances.get(u.getKey()) + edge.getData().getWeight();

                if (alt < distances.get(v.getKey())) {
                    distances.put(v.getKey(), alt);
                    previous.put(v.getKey(), u.getKey());
                    queue.add(v);

                    // Update successors for each vertex
                    successors.get(u.getKey()).put(v.getKey(), v.getKey());
                    // Recursively update the successors path
                    updateSuccessors(successors, previous, u, v);
                }
            }
        }
        printSuccessorMatrix(successors, graph);
        return successors;
    }

    private static void updateSuccessors(Map<String, Map<String, String>> successors, Map<String, String> previous, Graph<String, City, Road>.Vertex current, Graph<String, City, Road>.Vertex next) {
        // Update the successors for the current path
        for (String key : successors.keySet()) {
            if (successors.get(key).containsKey(current.getKey())) {
                successors.get(key).put(next.getKey(), successors.get(key).get(current.getKey()));
            }
        }
    }

    public static void printSuccessorMatrix(Map<String, Map<String, String>> successors, Graph<String, City, Road> graph) {
        List<String> keys = new ArrayList<>(graph.getVertices().keySet());
        Collections.sort(keys);

        // Tisk hlavičky
        System.out.print("    ");
        for (String key : keys) {
            City city = graph.getVertex(key).getData();
            System.out.print(String.format("%2s ", city.getName()));
        }
        System.out.println();

        // Tisk těla matice
        for (String key : keys) {
            City rowCity = graph.getVertex(key).getData();
            System.out.print(String.format("%2s ", rowCity.getName()));
            Map<String, String> row = successors.get(key);
            for (String colKey : keys) {
                String successorKey = row.get(colKey);
                if (successorKey == null) {
                    System.out.print(" . ");
                } else {
                    City colCity = graph.getVertex(successorKey).getData();
                    System.out.print(String.format("%2s ", colCity.getName()));
                }
            }
            System.out.println();
        }
    }






}
