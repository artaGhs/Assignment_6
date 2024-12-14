//Arta Ghasemi

//Implementation of the graph interface for managing towns and roads.

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Graph implements GraphInterface<Town, Road> {
    private Map<Town, List<Road>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        if (sourceVertex == null || destinationVertex == null) return null;

        for (Road road : adjacencyList.getOrDefault(sourceVertex, new ArrayList<>())) {
            if (road.getDestination().equals(destinationVertex) || road.getSource().equals(destinationVertex)) {
                return road;
            }
        }
        for (Road road : adjacencyList.getOrDefault(destinationVertex, new ArrayList<>())) {
            if (road.getDestination().equals(sourceVertex) || road.getSource().equals(sourceVertex)) {
                return road;
            }
        }
        return null;
    }


    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        if (!adjacencyList.containsKey(sourceVertex) || !adjacencyList.containsKey(destinationVertex)) {
            throw new IllegalArgumentException("Source or destination vertex not found in the graph.");
        }
        Road road = new Road(sourceVertex, destinationVertex, weight, description);
        adjacencyList.get(sourceVertex).add(road);
        adjacencyList.get(destinationVertex).add(road);
        return road;
    }

    @Override
    public boolean addVertex(Town v) {
        if (v == null) return false;
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new ArrayList<>());
            return true;
        }
        return false;
    }

    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        return getEdge(sourceVertex, destinationVertex) != null;
    }

    @Override
    public boolean containsVertex(Town v) {
        return adjacencyList.containsKey(v);
    }

    @Override
    public Set<Road> edgeSet() {
        Set<Road> roads = new HashSet<>();
        for (List<Road> roadList : adjacencyList.values()) {
            roads.addAll(roadList);
        }
        return roads;
    }

    @Override
    public Set<Road> edgesOf(Town vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex not found in the graph.");
        }
        return new HashSet<>(adjacencyList.get(vertex));
    }

    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        Road road = getEdge(sourceVertex, destinationVertex);
        if (road != null && road.getWeight() == weight && road.getName().equals(description)) {
            adjacencyList.get(sourceVertex).remove(road);
            adjacencyList.get(destinationVertex).remove(road);
            return road;
        }
        return null;
    }

    @Override
    public boolean removeVertex(Town v) {
        if (!adjacencyList.containsKey(v)) return false;
        adjacencyList.remove(v);
        for (List<Road> roads : adjacencyList.values()) {
            roads.removeIf(road -> road.getSource().equals(v) || road.getDestination().equals(v));
        }
        return true;
    }

    @Override
    public Set<Town> vertexSet() {
        return adjacencyList.keySet();
    }

    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
        dijkstraShortestPath(sourceVertex); // Calculate shortest paths from the source
        ArrayList<String> path = new ArrayList<>();
        
        if (!previous.containsKey(destinationVertex) || previous.get(destinationVertex) == null) {
            return path; // Return an empty list if no path exists
        }

        // Backtrack the path from destination to source
        Town current = destinationVertex;
        while (current != null && previous.containsKey(current)) {
            Town previousTown = previous.get(current);
            if (previousTown != null) {
                Road connectingRoad = getEdge(previousTown, current);
                path.add(0, String.format("%s via %s to %s %d mi",
                        previousTown.getName(), connectingRoad.getName(), current.getName(), connectingRoad.getWeight()));
            }
            current = previousTown;
        }

        return path;
    }
    private Map<Town, Integer> distances;
    private Map<Town, Town> previous;

    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
        distances = new HashMap<>();
        previous = new HashMap<>();
        PriorityQueue<Town> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Initialize distances and previous maps
        for (Town town : adjacencyList.keySet()) {
            distances.put(town, Integer.MAX_VALUE);
            previous.put(town, null);
        }
        distances.put(sourceVertex, 0);
        queue.add(sourceVertex);

        while (!queue.isEmpty()) {
            Town current = queue.poll();

            for (Road road : adjacencyList.get(current)) {
                Town neighbor = road.getDestination().equals(current) ? road.getSource() : road.getDestination();
                int newDistance = distances.get(current) + road.getWeight();

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }
}
