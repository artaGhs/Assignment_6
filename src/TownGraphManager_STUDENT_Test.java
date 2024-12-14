//Arta Ghasemi

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TownGraphManager_STUDENT_Test {
    private TownGraphManagerInterface graph;
    private String[] towns;

    @Before
    public void setUp() {
        graph = new TownGraphManager();
        towns = new String[6];

        for (int i = 0; i < 6; i++) {
            towns[i] = "Town_" + i;
            graph.addTown(towns[i]);
        }

        graph.addRoad(towns[0], towns[1], 3, "Road_1");
        graph.addRoad(towns[1], towns[2], 2, "Road_2");
        graph.addRoad(towns[2], towns[3], 4, "Road_3");
        graph.addRoad(towns[3], towns[4], 5, "Road_4");
    }

    @Test
    public void testAddRoad() {
        assertTrue(graph.addRoad(towns[0], towns[4], 7, "Road_5"));
        assertEquals("Road_5", graph.getRoad(towns[0], towns[4]));
    }

    @Test
    public void testAddTown() {
        assertFalse(graph.containsTown("Town_6"));
        graph.addTown("Town_6");
        assertTrue(graph.containsTown("Town_6"));
    }

    @Test
    public void testGetPath() {
        ArrayList<String> path = graph.getPath(towns[0], towns[4]);
        assertNotNull(path);
        assertEquals(4, path.size());
        assertEquals("Town_0 via Road_1 to Town_1 3 mi", path.get(0).trim());
    }

    @Test
    public void testDeleteRoadConnection() {
        assertTrue(graph.containsRoadConnection(towns[0], towns[1]));
        graph.deleteRoadConnection(towns[0], towns[1], "Road_1");
        assertFalse(graph.containsRoadConnection(towns[0], towns[1]));
    }

    @Test
    public void testDeleteTown() {
        assertTrue(graph.containsTown("Town_3"));
        graph.deleteTown("Town_3");
        assertFalse(graph.containsTown("Town_3"));
    }
}
