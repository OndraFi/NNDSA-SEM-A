package main.java.graph.intefaces;

import main.java.graph.Graph;

public interface IGraph<TVertex,TEdge> {

    void addVertex(TVertex data);

    void addEdge(TEdge e);

    TVertex findVertex(String key);

    TEdge findEdge(String key);
}
