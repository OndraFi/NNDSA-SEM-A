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
        generateAssigmentGraph();
    }

    public void openWindow() {
        this.gui = new Gui(this.graph);
    }

    public void importFromCSV(String fileName) {

    }

    public void importFromFile(String fileName) {

    }

    public void exportToFile(String fileName) {

    }

    private void generateAssigmentGraph(){
        City z = new City("Z", 0, 0);
        City k = new City("K", 30, 30);
        City s = new City("S", 100, 40);
        City i = new City("I", 120, 100);
        City a = new City("A", 60, 120);
        City x = new City("X", 80, 140);
        City m = new City("M", 160, 160);
        City g = new City("G", 140,180);
        City u = new City("U", 20, 200);
        City t = new City("T", 130, 300);
        City n = new City("N", 80, 290);
        City f = new City("F", 100, 250);
        City r = new City("R", 70, 230);
        City p = new City("P", 60, 210);
        City w = new City("W", 40, 270);

        graph.addVertex("Z", z);
        graph.addVertex("K", k);
        graph.addVertex("S", s);
        graph.addVertex("I", i);
        graph.addVertex("A", a);
        graph.addVertex("X", x);
        graph.addVertex("M", m);
        graph.addVertex("G", g);
        graph.addVertex("U", u);
        graph.addVertex("T", t);
        graph.addVertex("N", n);
        graph.addVertex("F", f);
        graph.addVertex("R", r);
        graph.addVertex("P", p);
        graph.addVertex("W", w);


        Road zk = new Road(10);
        graph.addEdge("Z", "K", zk);
        Road ks = new Road(70);
        graph.addEdge("K", "S", ks);
        Road ka = new Road(70);
        graph.addEdge("K", "A", ka);
        Road sa = new Road(60);
        graph.addEdge("S", "A", sa);
        Road si = new Road(40);
        graph.addEdge("S", "I", si);
        Road ix = new Road(60);
        graph.addEdge("I", "X", ix);
        Road ax = new Road(50);
        graph.addEdge("A", "X", ax);
        Road xm = new Road(60);
        graph.addEdge("X", "M", xm);
        Road xg = new Road(65);
        graph.addEdge("X", "G", xg);
        Road xu = new Road(80);
        graph.addEdge("X", "U", xu);
        Road mg = new Road(20);
        graph.addEdge("M", "G", mg);
        Road ug = new Road(90);
        graph.addEdge("U", "G", ug);
        Road gt = new Road(100);
        graph.addEdge("G", "T", gt);
        Road tn = new Road(40);
        graph.addEdge("T", "N", tn);
        Road nf = new Road(50);
        graph.addEdge("N", "F", nf);
        Road nr = new Road(60);
        graph.addEdge("N", "R", nr);
        Road np = new Road(40);
        graph.addEdge("N", "P", np);
        Road pw = new Road(20);
        graph.addEdge("P", "W", pw);
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
