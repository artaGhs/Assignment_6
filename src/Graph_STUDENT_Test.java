//Arta Ghasemi

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class Graph_STUDENT_Test {
    private Graph graph;
    private Town t1, t2, t3, t4;

    @Before
    public void setUp() {
        graph = new Graph();
        t1 = new Town("Town_A");
        t2 = new Town("Town_B");
        t3 = new Town("Town_C");
        t4 = new Town("Town_D");

        graph.addVertex(t1);
        graph.addVertex(t2);
        graph.addVertex(t3);
        graph.addVertex(t4);

        graph.addEdge(t1, t2, 2, "Road_1");
        graph.addEdge(t2, t3, 4, "Road_2");
    }

    @Test
    public void testAddEdge() {
        graph.addEdge(t3, t4, 3, "Road_3");
        assertEquals("Road_3", graph.getEdge(t3, t4).getName());
    }

    @Test
    public void testShortestPath() {
        ArrayList<String> path = graph.shortestPath(t1, t3);
        assertNotNull(path);
        assertEquals(2, path.size());
        assertEquals("Town_A via Road_1 to Town_B 2 mi", path.get(0).trim());
    }

    @Test
    public void testRemoveEdge() {
        assertTrue(graph.containsEdge(t1, t2));
        graph.removeEdge(t1, t2, 2, "Road_1");
        assertFalse(graph.containsEdge(t1, t2));
    }
}
