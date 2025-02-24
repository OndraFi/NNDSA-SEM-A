package main.java.gui;

public class ComboBoxItem {
    private String key;
    private String name;

    public ComboBoxItem(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    // Tato metoda je použita comboboxem pro zobrazení názvu
    @Override
    public String toString() {
        return name;
    }
}
