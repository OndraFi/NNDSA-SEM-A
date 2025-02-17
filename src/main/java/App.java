package main.java;

import main.java.graph.Graph;
import main.java.graph.Vertex;

import java.util.Random;

public class App {

    Graph<City,Road> graph;

    public App() {
        this.graph = this.generateConnectedRandomGraph(15,15);
    }

    public void openWindow() {

    }

    public void findShortestPathsFromVertex(Vertex vertex) {

    }

    private void dijkstra(Vertex vertex) {

    }

    public void importFromFile(String fileName) {

    }

    private Graph<City, Road> generateConnectedRandomGraph(int numRows, int numCols) {
        Graph<City, Road> graph = new Graph<>();
        Random random = new Random();
        City[][] cities = new City[numRows][numCols];

        int horizontalSpacing = PANEL_WIDTH / (numCols + 1);
        int verticalSpacing = PANEL_HEIGHT / (numRows + 1);

        // Vytvoření vrcholů s náhodným posunem
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                String cityName = "City R" + row + "C" + col;
                int x = (col + 1) * horizontalSpacing + random.nextInt(horizontalSpacing/3) - horizontalSpacing/6;
                int y = (row + 1) * verticalSpacing + random.nextInt(verticalSpacing/3) - verticalSpacing/6;
                City city = new City(cityName, x, y);
                graph.addVertex(city);
                cities[row][col] = city;

                // Zajištění alespoň jedné hrany pro každý vrchol (kromě prvního)
                if (col > 0) {
                    Road road = new Road(cities[row][col - 1].getKey(), city.getKey(), random.nextInt(15) + 5);
                    graph.addEdge(road);
                } else if (row > 0) {
                    Road road = new Road(cities[row - 1][col].getKey(), city.getKey(), random.nextInt(15) + 5);
                    graph.addEdge(road);
                }
            }
        }

        // Přidání několika náhodných hran pro zvýšení složitosti
        int additionalEdges = (numRows + numCols) * 2;
        for (int i = 0; i < additionalEdges; i++) {
            int row1 = random.nextInt(numRows);
            int col1 = random.nextInt(numCols);
            int row2 = random.nextInt(numRows);
            int col2 = random.nextInt(numCols);
            if (row1 != row2 || col1 != col2) {
                Road road = new Road(cities[row1][col1].getKey(), cities[row2][col2].getKey(), random.nextInt(15) + 5);
                graph.addEdge(road);
            }
        }

        return graph;
    }

}
