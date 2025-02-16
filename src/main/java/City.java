package main.java;

import main.java.graph.Location;
import main.java.graph.Vertex;

public class City extends Vertex {

    private final String name;

    public City(String name, int x ,int y) {
        super(x,y);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
