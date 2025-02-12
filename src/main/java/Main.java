package main.java;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        Test<Integer> t = new Test<Integer>(4);

        System.out.println("Hello world!");
        Graph<String,City, Edge> g = new Graph<>();
        City c = new City("Kolín");
        g.addVertex(c);


        JFrame frame = new JFrame();
        frame.setSize(150, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(g.findVertex("abc").getData().getName());
        JLabel label = new JLabel(g.findVertex("abc").getData().getName());
        frame.add(label);

        frame.setVisible(true);
    }
}