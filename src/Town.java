//Arta Ghasemi

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Town implements Comparable<Town> {
    private String name;
    private List<Town> adjacentTowns;

    public Town(String name) {
        this.name = name;
        this.adjacentTowns = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Town> getAdjacentTowns() {
        return adjacentTowns;
    }

    @Override
    public int compareTo(Town other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Town town = (Town) obj;
        return Objects.equals(name, town.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
