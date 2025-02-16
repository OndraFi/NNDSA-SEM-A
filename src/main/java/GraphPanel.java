package main.java;

import main.java.graph.Graph;
import main.java.graph.Location;
import main.java.graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GraphPanel extends JPanel {
    private Map<String, Point> positions;
    private Graph<City, Road> graph;
    private double scaleX, scaleY;

    public GraphPanel(Graph<City, Road> graph, Dimension size) {
        this.positions = new HashMap<>();
        this.graph = graph;
        this.setPreferredSize(size);
        calculateScaleFactors();
    }

    private void calculateScaleFactors() {
        double maxX = 0;
        double maxY = 0;
        for (City city : graph.getVertices()) {
            Location loc = city.getLocation();
            if (loc.getX() > maxX) maxX = loc.getX();
            if (loc.getY() > maxY) maxY = loc.getY();
        }
        scaleX = this.getPreferredSize().width / maxX;
        scaleY = this.getPreferredSize().height / maxY;
    }

    public void addVertex(Vertex v) {
        Location location = v.getLocation();
        positions.put(v.getKey(), new Point(location.getX(), location.getY()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Kreslení hran
        for (Road road : graph.getAllEdges()) {
            City c1 = graph.findVertex(road.getVertex1Key());
            City c2 = graph.findVertex(road.getVertex2Key());
            Point p1 = new Point((int) (c1.getLocation().getX() * scaleX), (int) (c1.getLocation().getY() * scaleY));
            Point p2 = new Point((int) (c2.getLocation().getX() * scaleX), (int) (c2.getLocation().getY() * scaleY));

            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
            int midX = (p1.x + p2.x) / 2;
            int midY = (p1.y + p2.y) / 2;
            g2.drawString(String.valueOf(road.getValue()) + " Km", midX, midY);
        }

        // Kreslení vrcholů
        for (City city : graph.getVertices()) {
            Location loc = city.getLocation();
            int x = (int) (loc.getX() * scaleX);
            int y = (int) (loc.getY() * scaleY);
            g2.fillOval(x - 10, y - 10, 20, 20);
            g2.drawString(city.getName(), x - 20, y - 20);
        }
    }

}
