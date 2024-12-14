import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {
    private Town town;

    @Before
    public void setUp() {
        town = new Town("Town_1");
    }

    @Test
    public void testGetName() {
        assertEquals("Town_1", town.getName());
    }

    @Test
    public void testEquals() {
        Town sameTown = new Town("Town_1");
        assertEquals(town, sameTown);
    }

    @Test
    public void testCompareTo() {
        Town otherTown = new Town("Town_2");
        assertTrue(town.compareTo(otherTown) < 0);
    }
}
