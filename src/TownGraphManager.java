import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

//Arta Ghasemi
//12/13/2024
class TownGraphManager implements TownGraphManagerInterface {
    private Graph graph;

    public TownGraphManager() {
        graph = new Graph();
    }
    
    
  //Populates the graph with towns and roads from a given file.
    public void populateTownGraph(File file) throws FileNotFoundException, IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("The file " + file.getName() + " does not exist.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String[] roadInfo = parts[0].split(",");
                String roadName = roadInfo[0];
                int distance = Integer.parseInt(roadInfo[1]);
                String town1 = parts[1];
                String town2 = parts[2];

                addTown(town1);
                addTown(town2);
                addRoad(town1, town2, distance, roadName);
            }
        } catch (IOException e) {
            throw new IOException("Error reading file: " + file.getName(), e);
        }
    }

    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        Town t1 = new Town(town1);
        Town t2 = new Town(town2);
        if (graph.containsVertex(t1) && graph.containsVertex(t2)) {
            graph.addEdge(t1, t2, weight, roadName);
            return true;
        }
        return false;
    }

    @Override
    public String getRoad(String town1, String town2) {
        Road road = graph.getEdge(new Town(town1), new Town(town2));
        return road != null ? road.getName() : null;
    }

    @Override
    public boolean addTown(String v) {
        return graph.addVertex(new Town(v));
    }

    @Override
    public Town getTown(String name) {
        for (Town town : graph.vertexSet()) {
            if (town.getName().equals(name)) {
                return town;
            }
        }
        return null;
    }

    @Override
    public boolean containsTown(String v) {
        return graph.containsVertex(new Town(v));
    }

    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        return graph.containsEdge(new Town(town1), new Town(town2));
    }

    @Override
    public ArrayList<String> allRoads() {
        return graph.edgeSet().stream().map(Road::getName).sorted().collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {
        Road toRemove = graph.getEdge(new Town(town1), new Town(town2));
        if (toRemove != null && toRemove.getName().equals(road)) {
            graph.removeEdge(new Town(town1), new Town(town2), toRemove.getWeight(), road);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTown(String v) {
        return graph.removeVertex(new Town(v));
    }

    @Override
    public ArrayList<String> allTowns() {
        return graph.vertexSet().stream()
                .map(Town::getName)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        Town source = getTown(town1);
        Town destination = getTown(town2);

        if (source == null || destination == null) {
            return new ArrayList<>(); // Return empty list if towns don't exist
        }

        return graph.shortestPath(source, destination);
    }

    
    
    
}

