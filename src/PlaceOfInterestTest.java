import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.Test;


public class PlaceOfInterestTest {

    @Test
    public void testPlaceOfInterest() {
        // Test correct storage of attributes
        Country Count1 = new Country("Count1Name", null, null,
                new Continent("Con1", null, null));
        Country Count2 = new Country("Count2Name", null, null,
                new Continent("Con2", null, null));        
        
        LinkedHashMap<String, Region> testCountries = new LinkedHashMap<String, Region>();
        testCountries.put("Key1", Count1);
        testCountries.put("Key2", Count2);

        LinkedHashMap<String, Region> assertion = new LinkedHashMap<String, Region>();
        assertion.put("Key1", Count1);
        assertion.put("Key2", Count2);

    	PlaceOfInterest testPlace = new PlaceOfInterest("Name", "Area",
                "Description", testCountries);
    	
        assertEquals(testPlace.getName(), "Name");
        assertEquals(testPlace.getArea(), "Area");
        assertEquals(testPlace.getDescription(), "Description");
        assertEquals(testPlace.getLocations(), assertion);
    }

}
