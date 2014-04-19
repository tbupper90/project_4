import static org.junit.Assert.*;

import org.junit.Test;


public class CityTest {
    Country testCountry = new Country("CountName", "CountPop", "CountArea",
			new Continent("ConName", "ConPop", "ConArea"));

    @Test
    public void testCityStringStringStringString() {
        // Test correct storage of attributes excluding GPS data
    	City testNoGPS = new City("Name", "Population", "Area", testCountry);

        assertEquals(testNoGPS.getName(), "Name");
        assertEquals(testNoGPS.getPop(), "Population");
        assertEquals(testNoGPS.getArea(), "Area");
        assertEquals(testNoGPS.getCountry(), testCountry);
    }

    @Test
    public void testCityStringStringStringStringStringStringString() {
        // Test correct storage of attributes including GPS data
        City testGPS = new City("Name", "Population", "Area", testCountry,
				"Lat", "Lon", "Elev");

        assertEquals(testGPS.getName(), "Name");
        assertEquals(testGPS.getPop(), "Population");
        assertEquals(testGPS.getArea(), "Area");
        assertEquals(testGPS.getCountry(), testCountry);
        assertEquals(testGPS.getLat(), "Lat");
        assertEquals(testGPS.getLon(), "Lon");
        assertEquals(testGPS.getElev(), "Elev");
    }

}
