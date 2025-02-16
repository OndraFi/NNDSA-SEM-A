package main.java;

import main.java.graph.Graph;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;

    public static void main(String[] args) {
//        Test<Integer> t = new Test<Integer>(4);

        System.out.println("Hello world!");
        Graph<City, Road> g = new Graph<City, Road>();
        City c = new City("Kolín",50,50);
        City c2 = new City("Kutná Hora",150,50);
        City c3 = new City("Záboří nad Labem",100,200);
        City c4 = new City("Čáslav",200,50);
        g.addVertex(c);
        g.addVertex(c2);
        g.addVertex(c3);
        g.addVertex(c4);

        String c1key = c.getKey();
        String c2key = c2.getKey();
        String c3key = c3.getKey();
        String c4key = c4.getKey();
        Road r1 = new Road(c1key, c2key, 10);
        Road r2 = new Road(c3key, c2key, 13);
        Road r3 = new Road(c3key, c1key, 14);
        Road r4 = new Road(c2key, c4key, 20);
        g.addEdge(r1);
        g.addEdge(r2);
        g.addEdge(r3);
        g.addEdge(r4);

        GraphPanel graphPanel = new GraphPanel(g,new Dimension((int)Math.round(PANEL_WIDTH*0.8),(int)Math.round(PANEL_HEIGHT*0.8)));

        // Nastavení pozic vrcholů v GraphPanel
        graphPanel.addVertex(c ); // Kolín
        graphPanel.addVertex(c2); // Kutná Hora
        graphPanel.addVertex(c3); // Záboří nad Labem

        JFrame frame = new JFrame();
        frame.setSize(PANEL_WIDTH, PANEL_HEIGHT); // Zvětšení velikosti okna, aby bylo místo pro graf
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Použití BorderLayoutu pro lepší umístění komponent

        // Přidání JLabels pro zobrazení informací
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        labelPanel.add(new JLabel("Road Value: " + g.findEdge(c3key, c2key).getValue()));

        frame.add(labelPanel, BorderLayout.SOUTH); // Přidání panelu s popisky na spodní stranu okna
        frame.add(graphPanel, BorderLayout.CENTER); // Přidání GraphPanel do středu okna

        frame.setTitle("Graph Visualization");
        frame.setVisible(true);

    }
}