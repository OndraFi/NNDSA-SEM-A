package main.java;

import main.java.graph.Graph;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        Test<Integer> t = new Test<Integer>(4);

        System.out.println("Hello world!");
        Graph<City,Road> g = new Graph<City,Road>();
        City c = new City("Kolín");
        g.addVertex(c);


        JFrame frame = new JFrame();
        frame.setSize(150, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        City returnVertex = g.findVertex("abc");
        frame.setTitle(g.findVertex("abc").getName());
        JLabel label = new JLabel(g.findVertex("abc").getName());
        frame.add(label);

        frame.setVisible(true);
    }
}