//Arta Ghasemi

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {
    private Road road;

    @Before
    public void setUp() {
        road = new Road(new Town("Town_X"), new Town("Town_Y"), 3, "Road_XY");
    }

    @Test
    public void testGetWeight() {
        assertEquals(3, road.getWeight());
    }

    @Test
    public void testEquals() {
        Road sameRoad = new Road(new Town("Town_X"), new Town("Town_Y"), 3, "Road_XY");
        assertEquals(road, sameRoad);
    }

    @Test
    public void testToString() {
        assertEquals("Town_X via Road_XY to Town_Y 3 mi", road.toString());
    }
}
