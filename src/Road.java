//Arta Ghasemi

import java.util.Objects;


//Represents a road (edge) in the graph
class Road implements Comparable<Road> {
    private Town source;
    private Town destination;
    private int weight;
    private String name;

    public Road(Town source, Town destination, int weight, String name) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.name = name;
    }

    public Town getSource() {
        return source;
    }

    public Town getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Road other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Road road = (Road) obj;
        return weight == road.weight &&
               ((source.equals(road.source) && destination.equals(road.destination)) ||
                (source.equals(road.destination) && destination.equals(road.source))) &&
               name.equals(road.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, weight, name) + Objects.hash(destination, source, weight, name);
    }


    @Override
    public String toString() {
        return String.format("%s via %s to %s %d mi", source, name, destination, weight);
    }
}
