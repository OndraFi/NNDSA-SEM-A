package main.java.gui;

import main.java.City;
import main.java.DijkstraAlgorithm;
import main.java.Road;
import main.java.graph.Graph;
import main.java.gui.components.CityForm;
import main.java.gui.components.GraphIOForm;
import main.java.gui.components.GraphPanel;
import main.java.gui.components.RoadForm;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Gui extends JFrame {
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 800;
    private final Graph<String, City, Road> graph;
    private JPanel sidebar;

    private CityForm cityForm;
    private RoadForm roadForm;
    private GraphIOForm graphIOForm;

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
        addGraphIOForm();
        addDijkstraForm();

        GraphPanel graphPanel = new GraphPanel(this.graph, new Dimension((int) Math.round(PANEL_WIDTH * 0.8), (int) Math.round(PANEL_HEIGHT * 0.8)));
        add(graphPanel, BorderLayout.CENTER);
    }

    private void addCityForm() {
        cityForm = new CityForm(this.graph);
        cityForm.addCityListener(() -> {
            roadForm.mapVerticesToComboBoxes();
            repaint();
        });
        sidebar.add(cityForm);
        sidebar.revalidate();
        sidebar.repaint();
    }

    private void addRoadForm() {
        roadForm = new RoadForm(this.graph);
        roadForm.addRoadListener(this::repaint);
        sidebar.add(roadForm);
        sidebar.revalidate();
        sidebar.repaint();
    }

    private void addGraphIOForm() {
        graphIOForm = new GraphIOForm(this.graph);
        graphIOForm.addLoadListener(() -> {
            roadForm.mapVerticesToComboBoxes();
            repaint();
        });
        sidebar.add(graphIOForm);
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
            data[row][0] = graph.getVertex(key).getName();  // První sloupec s názvy měst
            int col = 1;
            for (String colKey : keys) {
                String successorKey = successors.get(key).get(colKey);
                data[row][col] = successorKey == null ? "" : graph.getVertex(successorKey).getName();  // Zobrazí jména měst následníků
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
            columns[index++] = graph.getVertex(key).getName();
        }
        return columns;
    }

    private void displayMatrix(Map<String, Map<String, String>> successors, Graph<String, City, Road> graph) {
        ArrayList<String> keys = new ArrayList<>(graph.getVertices().keySet());
        Collections.sort(keys);

        Object[][] tableData = prepareTableData(successors, graph);
        String[] columnNames = prepareColumnNames(keys, graph);

        if (dijkstraTable != null) {
            dijkstraPanel.remove(dijsktraScrollPane);
            dijkstraControlPanel.remove(dijkstraHeightSpinner);
            remove(dijkstraControlPanel);
            remove(dijkstraPanel);
        }

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
        revalidate();
        repaint();
    }



}