import static org.junit.Assert.*;

import org.junit.Test;


public class CountryTest {

    @Test
    public void testCountry() {
        // Test correct storage of attributes
        Country testCountry = new Country("Name", "Pop", "Area", "Continent");

        assertEquals(testCountry.getName(), "Name");
        assertEquals(testCountry.getPop(), "Pop");
        assertEquals(testCountry.getArea(), "Area");
        assertEquals(testCountry.getContinent(), "Continent");
    }

}
