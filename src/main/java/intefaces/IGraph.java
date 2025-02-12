package main.java.intefaces;

import main.java.Graph;

public interface IGraph<EVertex,EEdge> {

    void addVertex(EVertex data);

    void addEdge(EEdge e);

    Graph.Vertex findVertex(String key);

    EEdge findEdge(String key);
}
