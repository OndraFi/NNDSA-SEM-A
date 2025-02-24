package main.java;

import main.java.graph.Graph;
import main.java.gui.Gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class App {
    Graph<String, City, Road> graph;
    private Gui gui;

    public App() {
        this.graph = new Graph<>();
        generateStarLikeGraph(10);
    }

    public void openWindow() {
        this.gui = new Gui(this.graph);
    }

    public void importFromFile(String fileName) {

    }

    public void exportToFile(String fileName) {

    }

    private void generateStarLikeGraph(int n) {
        City center = new City("Center", 500, 500);
        this.graph.addVertex("Center", center);
        for (int i = 0; i < n; i++) {
            City city = new City("City" + i, (int) (Math.random() * 500), (int) (Math.random() * 1000));
            this.graph.addVertex("City" + i, city);
            this.graph.addEdge("Center", "City" + i, new Road((int) (Math.random() * 100)));
        }
    }

}
