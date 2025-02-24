package main.java;

import main.java.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {

    public static final int PANEL_WIDTH = 1920;
    public static final int PANEL_HEIGHT = 1080;

    public static void main(String[] args) {
//        Test<Integer> t = new Test<Integer>(4);

        System.out.println("Hello world!");
//        Graph<City, Road> g = new Graph<City, Road>();
//        City c = new City("Kolín",50,50);
//        City c2 = new City("Kutná Hora",150,50);
//        City c3 = new City("Záboří nad Labem",100,200);
//        City c4 = new City("Čáslav",200,50);
//        g.addVertex(c);
//        g.addVertex(c2);
//        g.addVertex(c3);
//        g.addVertex(c4);
//
//        String c1key = c.getKey();
//        String c2key = c2.getKey();
//        String c3key = c3.getKey();
//        String c4key = c4.getKey();
//        Road r1 = new Road(c1key, c2key, 10);
//        Road r2 = new Road(c3key, c2key, 13);
//        Road r3 = new Road(c3key, c1key, 14);
//        Road r4 = new Road(c2key, c4key, 20);
//        r4.blockEdge();
//        g.addEdge(r1);
//        g.addEdge(r2);
//        g.addEdge(r3);
//        g.addEdge(r4);

//        Graph<City,Road> g = generateGridGraph(5,5);
//        Graph<City,Road> g = generateTreeLikeGraph(8,8);
//        Graph<City,Road> g = generateConnectedTreeGraph(5,5);
//        Graph<City,Road> g = generateConnectedRandomGraph(15,15);
//
//        GraphPanel graphPanel = new GraphPanel(g,new Dimension((int)Math.round(PANEL_WIDTH*0.8),(int)Math.round(PANEL_HEIGHT*0.8)));
////
////        // Nastavení pozic vrcholů v GraphPanel
////        graphPanel.addVertex(c ); // Kolín
////        graphPanel.addVertex(c2); // Kutná Hora
////        graphPanel.addVertex(c3); // Záboří nad Labem
//
//        JFrame frame = new JFrame();
//        frame.setSize(PANEL_WIDTH, PANEL_HEIGHT); // Zvětšení velikosti okna, aby bylo místo pro graf
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout()); // Použití BorderLayoutu pro lepší umístění komponent
//
//        // Přidání JLabels pro zobrazení informací
//        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
////        labelPanel.add(new JLabel("Road Value: " + g.findEdge(c3key, c2key).getValue()));
//
//        frame.add(labelPanel, BorderLayout.SOUTH); // Přidání panelu s popisky na spodní stranu okna
//        frame.add(graphPanel, BorderLayout.CENTER); // Přidání GraphPanel do středu okna
//
//        frame.setTitle("Graph Visualization");
//        frame.setVisible(true);

        App app = new App();
        app.openWindow();

    }

//    public static Graph<City, Road> generateGridGraph(int numRows, int numCols) {
//        Graph<City, Road> graph = new Graph<City, Road>();
//        City[][] cities = new City[numRows][numCols];
//
//        // Vytvoření vrcholů v mřížce
//        int horizontalSpacing = PANEL_WIDTH / (numCols + 1);
//        int verticalSpacing = PANEL_HEIGHT / (numRows + 1);
//
//        for (int row = 0; row < numRows; row++) {
//            for (int col = 0; col < numCols; col++) {
//                String cityName = "City R" + row + "C" + col;
//                int x = (col + 1) * horizontalSpacing;
//                int y = (row + 1) * verticalSpacing;
//                City city = new City(cityName, x, y);
//                graph.addVertex(city);
//                cities[row][col] = city;
//            }
//        }
//
//        // Vytvoření hran mezi sousedními vrcholy
//        for (int row = 0; row < numRows; row++) {
//            for (int col = 0; col < numCols; col++) {
//                if (col < numCols - 1) {
//                    Road roadHorizontal = new Road(cities[row][col].getKey(), cities[row][col + 1].getKey(), 5);
//                    graph.addEdge(roadHorizontal);
//                }
//                if (row < numRows - 1) {
//                    Road roadVertical = new Road(cities[row][col].getKey(), cities[row + 1][col].getKey(), 5);
//                    graph.addEdge(roadVertical);
//                }
//            }
//        }
//
//        return graph;
//    }
//
//    public static Graph<City, Road> generateTreeLikeGraph(int numRows, int numCols) {
//        Graph<City, Road> graph = new Graph<>();
//        Random random = new Random();
//        City[][] cities = new City[numRows][numCols];
//
//        int horizontalSpacing = PANEL_WIDTH / (numCols + 1);
//        int verticalSpacing = PANEL_HEIGHT / (numRows + 1);
//
//        // Vytvoření vrcholů s náhodným posunem
//        for (int row = 0; row < numRows; row++) {
//            for (int col = 0; col < numCols; col++) {
//                String cityName = "City R" + row + "C" + col;
//                int x = (col + 1) * horizontalSpacing + random.nextInt(horizontalSpacing/3) - horizontalSpacing/6;
//                int y = (row + 1) * verticalSpacing + random.nextInt(verticalSpacing/3) - verticalSpacing/6;
//                City city = new City(cityName, x, y);
//                graph.addVertex(city);
//                cities[row][col] = city;
//            }
//        }
//
//        // Přidání hran s odchylkou
//        for (int row = 0; row < numRows; row++) {
//            for (int col = 0; col < numCols; col++) {
//                // Přidávání hran pouze s šancí, aby graf vypadal více jako strom
//                if (col < numCols - 1 && random.nextBoolean()) {
//                    Road road = new Road(cities[row][col].getKey(), cities[row][col + 1].getKey(), random.nextInt(15) + 5);
//                    graph.addEdge(road);
//                }
//                if (row < numRows - 1 && random.nextBoolean()) {
//                    Road road = new Road(cities[row][col].getKey(), cities[row + 1][col].getKey(), random.nextInt(15) + 5);
//                    graph.addEdge(road);
//                }
//            }
//        }
//
//        return graph;
//    }
//
//    public static Graph<City, Road> generateConnectedTreeGraph(int numRows, int numCols) {
//        Graph<City, Road> graph = new Graph<>();
//        Random random = new Random();
//        City[][] cities = new City[numRows][numCols];
//
//        int horizontalSpacing = PANEL_WIDTH / (numCols + 1);
//        int verticalSpacing = PANEL_HEIGHT / (numRows + 1);
//
//        // Vytvoření vrcholů s náhodným posunem
//        for (int row = 0; row < numRows; row++) {
//            for (int col = 0; col < numCols; col++) {
//                String cityName = "City R" + row + "C" + col;
//                int x = (col + 1) * horizontalSpacing + random.nextInt(horizontalSpacing/3) - horizontalSpacing/6;
//                int y = (row + 1) * verticalSpacing + random.nextInt(verticalSpacing/3) - verticalSpacing/6;
//                City city = new City(cityName, x, y);
//                graph.addVertex(city);
//                cities[row][col] = city;
//
//                // Propojení s předchozím vrcholem ve sloupci (pokud existuje)
//                if (col > 0) {
//                    Road road = new Road(cities[row][col - 1].getKey(), city.getKey(), random.nextInt(15) + 5);
//                    graph.addEdge(road);
//                }
//
//                // Propojení s předchozím vrcholem v řádku (pokud existuje)
//                if (row > 0) {
//                    Road road = new Road(cities[row - 1][col].getKey(), city.getKey(), random.nextInt(15) + 5);
//                    graph.addEdge(road);
//                }
//            }
//        }
//
//        return graph;
//    }
//
//    public static Graph<City, Road> generateConnectedRandomGraph(int numRows, int numCols) {
//        Graph<City, Road> graph = new Graph<>();
//        Random random = new Random();
//        City[][] cities = new City[numRows][numCols];
//
//        int horizontalSpacing = PANEL_WIDTH / (numCols + 1);
//        int verticalSpacing = PANEL_HEIGHT / (numRows + 1);
//
//        // Vytvoření vrcholů s náhodným posunem
//        for (int row = 0; row < numRows; row++) {
//            for (int col = 0; col < numCols; col++) {
//                String cityName = "City R" + row + "C" + col;
//                int x = (col + 1) * horizontalSpacing + random.nextInt(horizontalSpacing/3) - horizontalSpacing/6;
//                int y = (row + 1) * verticalSpacing + random.nextInt(verticalSpacing/3) - verticalSpacing/6;
//                City city = new City(cityName, x, y);
//                graph.addVertex(city);
//                cities[row][col] = city;
//
//                // Zajištění alespoň jedné hrany pro každý vrchol (kromě prvního)
//                if (col > 0) {
//                    Road road = new Road(cities[row][col - 1].getKey(), city.getKey(), random.nextInt(15) + 5);
//                    graph.addEdge(road);
//                } else if (row > 0) {
//                    Road road = new Road(cities[row - 1][col].getKey(), city.getKey(), random.nextInt(15) + 5);
//                    graph.addEdge(road);
//                }
//            }
//        }
//
//        // Přidání několika náhodných hran pro zvýšení složitosti
//        int additionalEdges = (numRows + numCols) * 2;
//        for (int i = 0; i < additionalEdges; i++) {
//            int row1 = random.nextInt(numRows);
//            int col1 = random.nextInt(numCols);
//            int row2 = random.nextInt(numRows);
//            int col2 = random.nextInt(numCols);
//            if (row1 != row2 || col1 != col2) {
//                Road road = new Road(cities[row1][col1].getKey(), cities[row2][col2].getKey(), random.nextInt(15) + 5);
//                graph.addEdge(road);
//            }
//        }
//
//        return graph;
//    }



}