import static org.junit.Assert.*;

import org.junit.Test;


public class CountryTest {
	Continent testCon = new Continent("ConName", "ConPop", "ConArea");
    @Test
    public void testCountry() {
        // Test correct storage of attributes
        Country testCountry = new Country("Name", "Pop", "Area", testCon);

        assertEquals(testCountry.getName(), "Name");
        assertEquals(testCountry.getPop(), "Pop");
        assertEquals(testCountry.getArea(), "Area");
        assertEquals(testCountry.getContinent(), testCon);
    }

}
