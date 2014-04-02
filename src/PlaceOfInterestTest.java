import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.Test;


public class PlaceOfInterestTest {

    @Test
    public void testPlaceOfInterest() {
        // Test correct storage of attributes
        LinkedHashMap<String, String> testCountries = new LinkedHashMap<String, String>();
        testCountries.put("Key1", "Country1");
        testCountries.put("Key2", "Country2");

        LinkedHashMap<String, String> assertion = new LinkedHashMap<String, String>();
        assertion.put("Key1", "Country1");
        assertion.put("Key2", "Country2");

    	PlaceOfInterest testPlace = new PlaceOfInterest("Name", "Area",
                "Description", testCountries);
    	
        assertEquals(testPlace.getName(), "Name");
        assertEquals(testPlace.getArea(), "Area");
        assertEquals(testPlace.getDescription(), "Description");
        assertEquals(testPlace.getLocation(), assertion);
    }

}
