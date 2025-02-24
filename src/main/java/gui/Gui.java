package main.java.gui;

import main.java.City;
import main.java.DijkstraAlgorithm;
import main.java.Road;
import main.java.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class Gui extends JFrame {
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 800;
    private final Graph<String, City, Road> graph;
    private JPanel sidebar;
    private JTextField cityNameField, xCoordField, yCoordField, weightField;
    private JButton addCityButton;
    private JComboBox<ComboBoxItem> vertex1ComboBox;
    private JComboBox<ComboBoxItem> vertex2ComboBox;
    private JButton addEdgeButton;

    private JButton dijkstraButton;
    private JComboBox<ComboBoxItem> dijkstraComboBox;
    private JPanel dijkstraPanel;
    private JPanel dijkstraControlPanel;
    private JSpinner dijkstraHeightSpinner;
    private JScrollPane dijsktraScrollPane;
    private JTable dijkstraTable;

    public Gui(Graph<String, City, Road> graph) {
        super("Semestrálí práce A Ondřej Fiala");
        this.graph = graph;
        initWindow();
        initializeComponents();
        mapVerticesToComboBoxes();
        setVisible(true);
    }

    private void initWindow() {
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        add(sidebar, BorderLayout.EAST);

        addCityForm();
        addRoadForm();
        addDijkstraForm();

        GraphPanel graphPanel = new GraphPanel(this.graph, new Dimension((int) Math.round(PANEL_WIDTH * 0.8), (int) Math.round(PANEL_HEIGHT * 0.8)));
        add(graphPanel, BorderLayout.CENTER);
    }

    private void addCityForm() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        cityNameField = new JTextField(20);
        xCoordField = new JTextField(20);
        yCoordField = new JTextField(20);

        formPanel.add(new JLabel("City Name:"));
        formPanel.add(cityNameField);
        formPanel.add(new JLabel("X Coordinate:"));
        formPanel.add(xCoordField);
        formPanel.add(new JLabel("Y Coordinate:"));
        formPanel.add(yCoordField);

        addCityButton = new JButton("Add City");
        addCityButton.addActionListener(e -> addCity());
        formPanel.add(addCityButton);

        sidebar.add(formPanel);
        sidebar.revalidate();
        sidebar.repaint();
    }

    private void addRoadForm() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        vertex1ComboBox = new JComboBox<>();
        vertex2ComboBox = new JComboBox<>();
        weightField = new JTextField(5);
        addEdgeButton = new JButton("Add Edge");

        formPanel.add(new JLabel("From:"));
        formPanel.add(vertex1ComboBox);
        formPanel.add(new JLabel("To:"));
        formPanel.add(vertex2ComboBox);
        formPanel.add(new JLabel("Weight:"));
        formPanel.add(weightField);
        formPanel.add(addEdgeButton);

        addEdgeButton.addActionListener(e -> addEdge());

        sidebar.add(formPanel);
        sidebar.revalidate();
        sidebar.repaint();
    }

    private void addDijkstraForm() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        dijkstraComboBox = new JComboBox<>();
        dijkstraButton = new JButton("Dijkstra");
        dijkstraButton.addActionListener(e -> dijkstra());

        formPanel.add(new JLabel("From:"));
        formPanel.add(dijkstraComboBox);
        formPanel.add(dijkstraButton);

        sidebar.add(formPanel);
        sidebar.revalidate();
        sidebar.repaint();
    }

    private void mapVerticesToComboBoxes() {
        vertex1ComboBox.removeAllItems();
        vertex2ComboBox.removeAllItems();
        dijkstraComboBox.removeAllItems();
        for (Graph<String,City,Road>.Vertex vertex : this.graph.getVertices().values()) {
            ComboBoxItem item = new ComboBoxItem(vertex.getKey(), (vertex.getData()).getName());
            dijkstraComboBox.addItem(item);
            vertex1ComboBox.addItem(item);
            vertex2ComboBox.addItem(item);
        }
    }

    private void addCity() {
        String cityName = cityNameField.getText();
        String xCoord = xCoordField.getText();
        String yCoord = yCoordField.getText();

        try {
            int x = Integer.parseInt(xCoord);
            int y = Integer.parseInt(yCoord);

            if (!cityName.isEmpty() && !xCoord.isEmpty() && !yCoord.isEmpty()) {
                City city = new City(cityName, x, y);
                graph.addVertex(generateKey(), city);  // Předpokládáme, že máme metodu getKey v třídě City
                cityNameField.setText("");
                xCoordField.setText("");
                yCoordField.setText("");
                JOptionPane.showMessageDialog(this, "City added successfully!");
            }
            mapVerticesToComboBoxes();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid coordinates", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addEdge() {
        ComboBoxItem item1 = (ComboBoxItem) vertex1ComboBox.getSelectedItem();
        ComboBoxItem item2 = (ComboBoxItem) vertex2ComboBox.getSelectedItem();
        if(item1 == null || item2 == null) {
            JOptionPane.showMessageDialog(this, "Please select two vertices.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String vertex1Key = item1.getKey();
        String vertex2Key = item2.getKey();
        String weightStr = weightField.getText();
        try {
            int weight = Integer.parseInt(weightStr);
            if (!vertex1Key.equals(vertex2Key)) {
                graph.addEdge(vertex1Key, vertex2Key, new Road(weight));
                JOptionPane.showMessageDialog(this, "Edge added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Cannot add edge between the same vertex.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid weight.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generateKey() {
        return UUID.randomUUID().toString();
    }

    private void dijkstra(){
        ComboBoxItem startVertex = (ComboBoxItem) dijkstraComboBox.getSelectedItem();
        if(startVertex == null) {
            JOptionPane.showMessageDialog(this, "Please select a starting vertex.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Map<String, Map<String, String>> successors = DijkstraAlgorithm.dijkstra(graph, startVertex.getKey());
        displayMatrix(successors, this.graph);
    }

    private Object[][] prepareTableData(Map<String, Map<String, String>> successors, Graph<String, City, Road> graph) {
        ArrayList<String> keys = new ArrayList<>(graph.getVertices().keySet());
        Collections.sort(keys);

        Object[][] data = new Object[keys.size()][keys.size() + 1];
        int row = 0;
        for (String key : keys) {
            data[row][0] = graph.getVertex(key).getData().getName();  // První sloupec s názvy měst
            int col = 1;
            for (String colKey : keys) {
                String successorKey = successors.get(key).get(colKey);
                data[row][col] = successorKey == null ? "" : graph.getVertex(successorKey).getData().getName();  // Zobrazí jména měst následníků
                col++;
            }
            row++;
        }
        return data;
    }

    private String[] prepareColumnNames(ArrayList<String> keys, Graph<String, City, Road> graph) {
        String[] columns = new String[keys.size() + 1];
        columns[0] = "City \\ To"; // Název prvního sloupce
        int index = 1;
        for (String key : keys) {
            columns[index++] = graph.getVertex(key).getData().getName();
        }
        return columns;
    }

    private void displayMatrix(Map<String, Map<String, String>> successors, Graph<String, City, Road> graph) {
        ArrayList<String> keys = new ArrayList<>(graph.getVertices().keySet());
        Collections.sort(keys);

        Object[][] tableData = prepareTableData(successors, graph);
        String[] columnNames = prepareColumnNames(keys, graph);

        dijkstraTable = new JTable(tableData, columnNames);
        dijsktraScrollPane = new JScrollPane(dijkstraTable);
        dijkstraTable.setFillsViewportHeight(true);

        dijkstraPanel = new JPanel(new BorderLayout());
        dijkstraPanel.add(dijsktraScrollPane, BorderLayout.CENTER);

        // Add a spinner to adjust the height of the table
        dijkstraHeightSpinner = new JSpinner(new SpinnerNumberModel(200, 100, 800, 50));
        dijkstraHeightSpinner.addChangeListener(e -> {
            int height = (int) dijkstraHeightSpinner.getValue();
            dijsktraScrollPane.setPreferredSize(new Dimension(dijsktraScrollPane.getWidth(), height));
            dijkstraPanel.revalidate();
            dijkstraPanel.repaint();
        });

        dijkstraControlPanel = new JPanel();
        dijkstraControlPanel.add(new JLabel("Table Height:"));
        dijkstraControlPanel.add(dijkstraHeightSpinner);

        add(dijkstraControlPanel, BorderLayout.NORTH);
        add(dijkstraPanel, BorderLayout.SOUTH);
        setVisible(true);
    }



}