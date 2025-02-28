package main.java.gui.components;

import main.java.City;
import main.java.Road;
import main.java.graph.Graph;
import main.java.gui.ComboBoxItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoadForm extends JPanel {
    private JComboBox<ComboBoxItem> vertex1ComboBox;
    private JComboBox<ComboBoxItem> vertex2ComboBox;
    private JTextField weightField;
    private JButton addEdgeButton;
    private final Graph<String, City, Road> graph;
    private List<Runnable> roadListeners = new ArrayList<>();

    public RoadForm(Graph<String, City, Road> graph) {
        this.graph = graph;
        initializeComponents();
        mapVerticesToComboBoxes();
    }

    public void addRoadListener(Runnable listener) {
        roadListeners.add(listener);
    }

    private void notifyRoadListeners() {
        for (Runnable listener : roadListeners) {
            listener.run();
        }
    }

    private void initializeComponents() {
        setLayout(new GridLayout(0, 2));

        vertex1ComboBox = new JComboBox<>();
        vertex2ComboBox = new JComboBox<>();
        weightField = new JTextField(5);
        addEdgeButton = new JButton("Add Edge");

        add(new JLabel("From:"));
        add(vertex1ComboBox);
        add(new JLabel("To:"));
        add(vertex2ComboBox);
        add(new JLabel("Weight:"));
        add(weightField);
        add(addEdgeButton);

        addEdgeButton.addActionListener(e -> addEdge());
    }

    public void mapVerticesToComboBoxes() {
        vertex1ComboBox.removeAllItems();
        vertex2ComboBox.removeAllItems();
        for (Map.Entry<String, City> entry : this.graph.getVertices().entrySet()) {
            String key = entry.getKey();
            City city = entry.getValue();
            ComboBoxItem item = new ComboBoxItem(key, city.getName());
            vertex1ComboBox.addItem(item);
            vertex2ComboBox.addItem(item);
        }
    }

    private void addEdge() {
        ComboBoxItem item1 = (ComboBoxItem) vertex1ComboBox.getSelectedItem();
        ComboBoxItem item2 = (ComboBoxItem) vertex2ComboBox.getSelectedItem();
        if (item1 == null || item2 == null) {
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
            notifyRoadListeners();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid weight.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}