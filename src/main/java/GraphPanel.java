package main.java;

import main.java.graph.Graph;
import main.java.graph.Location;
import main.java.graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class GraphPanel extends JPanel {
    private Map<String, Point> positions;
    private Graph<City, Road> graph;
    private double scaleX, scaleY;
    private double zoomFactor = 1.0;
    private double zoomMultiplier = 1.1;
    private Point dragStartScreen;
    private Point dragEndScreen;
    private AffineTransform coordTransform = new AffineTransform();

    public GraphPanel(Graph<City, Road> graph, Dimension size) {
        this.positions = new HashMap<>();
        this.graph = graph;
        this.setPreferredSize(size);
        calculateScaleFactors();
        setupMouseWheelZoom();
        setupMousePan();
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

    private void setupMouseWheelZoom() {
        addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                double delta = 0.05f * e.getPreciseWheelRotation();
                double factor = Math.exp(-delta); // Výpočet faktoru zoomu
                double x = e.getX();
                double y = e.getY();

                // Aktualizace transformační matice pro zoom kolem kurzoru
                AffineTransform at = new AffineTransform();
                at.translate(x, y);
                at.scale(factor, factor);
                at.translate(-x, -y);

                coordTransform.preConcatenate(at);

                repaint();
            }
        });
    }


    private void setupMousePan() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                dragStartScreen = e.getPoint();
                dragEndScreen = null;
            }

            public void mouseReleased(MouseEvent e) {
                moveCamera(e);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                moveCamera(e);
            }
        });
    }

    private void moveCamera(MouseEvent e) {
        try {
            dragEndScreen = e.getPoint();
            Point2D.Float dragStart = transformPoint(dragStartScreen);
            Point2D.Float dragEnd = transformPoint(dragEndScreen);
            double dx = dragEnd.x - dragStart.x;
            double dy = dragEnd.y - dragStart.y;
            coordTransform.translate(dx, dy);
            dragStartScreen = dragEndScreen;
            dragEndScreen = null;
            repaint();
        } catch (NoninvertibleTransformException ex) {
            ex.printStackTrace();
        }
    }

    private Point2D.Float transformPoint(Point p1) throws NoninvertibleTransformException {
        AffineTransform inverse = coordTransform.createInverse();
        Point2D.Float p2 = new Point2D.Float();
        inverse.transform(p1, p2);
        return p2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Aplikace zoom transformace
        AffineTransform transform = new AffineTransform();
        transform.translate(getWidth() / 2, getHeight() / 2);
        transform.scale(zoomFactor, zoomFactor);
        transform.translate(-getWidth() / 2, -getHeight() / 2);
        transform.concatenate(coordTransform);

        g2.setTransform(transform);

        // Nastavení velikosti fontu v závislosti na zoomFactor
        int fontSize = (int) Math.max(10, 10 * zoomFactor);  // Základní velikost je 10, upravuje se s zoomem
        g2.setFont(new Font("Arial", Font.PLAIN, fontSize));

        // Kreslení vrcholů
        for (City city : graph.getVertices()) {
            Location loc = city.getLocation();
            int x = (int) loc.getX();
            int y = (int) loc.getY();
            int ovalSize = (int) Math.max(5, 5 * zoomFactor);  // Základní velikost je 5, upravuje se s zoomem

            // Vrchol
            g2.fillOval(x - ovalSize / 2, y - ovalSize / 2, ovalSize, ovalSize);
            // Text
            g2.drawString(city.getName(), x - ovalSize / 2, y - ovalSize / 2 - fontSize / 2);
        }

        // Kreslení hran
        for (Road road : graph.getAllEdges()) {
            City c1 = graph.findVertex(road.getVertex1Key());
            City c2 = graph.findVertex(road.getVertex2Key());
            g2.drawLine((int) c1.getLocation().getX(), (int) c1.getLocation().getY(),
                    (int) c2.getLocation().getX(), (int) c2.getLocation().getY());
        }
    }



//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//
//        // Kreslení hran
//        for (Road road : graph.getAllEdges()) {
//            City c1 = graph.findVertex(road.getVertex1Key());
//            City c2 = graph.findVertex(road.getVertex2Key());
//            Point p1 = new Point((int) (c1.getLocation().getX() * scaleX), (int) (c1.getLocation().getY() * scaleY));
//            Point p2 = new Point((int) (c2.getLocation().getX() * scaleX), (int) (c2.getLocation().getY() * scaleY));
//
//            if (!road.isAccessible())
//                g2.setColor(Color.RED);
//            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
//            g2.setColor(Color.BLACK);
//            int midX = (p1.x + p2.x) / 2;
//            int midY = (p1.y + p2.y) / 2;
//            g2.drawString(String.valueOf(road.getValue()) + " Km", midX, midY);
//        }
//
//        // Kreslení vrcholů
//        for (City city : graph.getVertices()) {
//            Location loc = city.getLocation();
//            int x = (int) (loc.getX() * scaleX);
//            int y = (int) (loc.getY() * scaleY);
//            g2.fillOval(x - 10, y - 10, 20, 20);
//            g2.drawString(city.getName(), x - 20, y - 20);
//        }
//    }

}
