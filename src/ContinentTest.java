import static org.junit.Assert.*;

import org.junit.Test;


public class ContinentTest {

    @Test
    public void testContinent() {
        // Test correct storage of attributes
        Continent testContinent = new Continent("Name", "Pop", "Area");
        
        assertEquals(testContinent.getName(), "Name");
        assertEquals(testContinent.getPop(), "Pop");
        assertEquals(testContinent.getArea(), "Area");
    }

}
