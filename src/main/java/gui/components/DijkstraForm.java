package main.java.gui.components;

import main.java.City;
import main.java.DijkstraAlgorithm;
import main.java.Road;
import main.java.graph.Graph;
import main.java.gui.ComboBoxItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class DijkstraForm extends JPanel {

    private JComboBox<ComboBoxItem> dijkstraComboBox, dijkstraToComboBox;
    private final Graph<String, City, Road> graph;
    private Map<String, Map<String, String>> successors;
    private GraphPanel graphPanel;

    public DijkstraForm(Graph<String, City, Road> graph, GraphPanel graphPanel) {
        this.graph = graph;
        this.graphPanel = graphPanel;
        initComponent();
    }

    public void mapVerticesToComboBoxes() {
        dijkstraComboBox.removeAllItems();
        dijkstraToComboBox.removeAllItems();
        for (Map.Entry<String, City> entry : this.graph.getVertices().entrySet()) {
            dijkstraComboBox.addItem(new ComboBoxItem(entry.getKey(), entry.getValue().getName()));
            dijkstraToComboBox.addItem(new ComboBoxItem(entry.getKey(), entry.getValue().getName()));
        }
    }

    public void changesWareMadeInGraph() {
        this.successors = null;
    }

    private void initComponent() {
        setLayout(new GridLayout(0, 2));
        dijkstraComboBox = new JComboBox<>();
        dijkstraToComboBox = new JComboBox<>();
        JButton dijkstraButton = new JButton("Dijkstra");
        dijkstraButton.addActionListener(e -> dijkstra());
        JButton dijkstraToButton = new JButton("Zobraz cestu do");
        dijkstraToButton.addActionListener(e -> dijkstraTo());
        this.mapVerticesToComboBoxes();
        add(new JLabel("From:"));
        add(dijkstraComboBox);
        add(dijkstraButton);
        add(new JLabel(""));
        add(new JLabel("To:"));
        add(dijkstraToComboBox);
        add(dijkstraToButton);
    }

    private void dijkstra() {
        ComboBoxItem startVertex = (ComboBoxItem) dijkstraComboBox.getSelectedItem();
        if (startVertex == null) {
            JOptionPane.showMessageDialog(this, "Please select a starting vertex.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Map<String, Map<String, String>> successors = DijkstraAlgorithm.dijkstra(graph, startVertex.getKey());
        this.successors = successors;
        displayMatrix(successors, this.graph);
    }

    private void dijkstraTo() {
        ComboBoxItem startVertex = (ComboBoxItem) dijkstraComboBox.getSelectedItem();
        ComboBoxItem endVertex = (ComboBoxItem) dijkstraToComboBox.getSelectedItem();
        if (startVertex == null || endVertex == null) {
            JOptionPane.showMessageDialog(this, "Please select both a starting and ending vertex.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(this.successors == null) {
            JOptionPane.showMessageDialog(this, "Spusťte nejdřív dijkstrův algoritmus pro graf", "Error", JOptionPane.ERROR_MESSAGE);
            return;        }
        ArrayList<String> path = new ArrayList<>();
        String current = startVertex.getKey();
        while (!current.equals(endVertex.getKey())) {
            path.add(current);
            current = successors.get(current).get(endVertex.getKey());
            if (current == null) {
                JOptionPane.showMessageDialog(this, "No path found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        path.add(endVertex.getKey());
        graphPanel.setPath(path);
        graphPanel.repaint();
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

        JTable dijkstraTable = new JTable(tableData, columnNames);
        JScrollPane dijsktraScrollPane = new JScrollPane(dijkstraTable);
        dijkstraTable.setFillsViewportHeight(true);

        JFrame frame = new JFrame("Dijkstra Result");
        frame.setLayout(new BorderLayout());
        frame.add(dijsktraScrollPane, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

}
