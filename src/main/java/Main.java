package main.java;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Graph<City, Edge> g = new Graph<>();
        City c = new City("Kolín");
        g.addVertex(c);


        JFrame frame = new JFrame();
        frame.setSize(150, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(g.findVertex("abc").getName());
        JLabel label = new JLabel("Geeks Premier League 2023");
        frame.add(label);

        frame.setVisible(true);
    }
}