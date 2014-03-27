import static org.junit.Assert.*;

import org.junit.Test;


public class CityTest {

    @Test
    public void testCityStringStringStringString() {
        // Test correct storage of attributes excluding GPS data
        City testNoGPS = new City("Name", "Population", "Area", "Country");

        assertEquals(testNoGPS.getName(), "Name");
        assertEquals(testNoGPS.getPop(), "Population");
        assertEquals(testNoGPS.getArea(), "Area");
        assertEquals(testNoGPS.getCountry(), "Country");
    }

    @Test
    public void testCityStringStringStringStringStringStringString() {
        // Test correct storage of attributes including GPS data
        City testGPS = new City("Name", "Population", "Area", "Country", "Lat",
                                "Lon", "Elev");

        assertEquals(testGPS.getName(), "Name");
        assertEquals(testGPS.getPop(), "Population");
        assertEquals(testGPS.getArea(), "Area");
        assertEquals(testGPS.getCountry(), "Country");
        assertEquals(testGPS.getLat(), "Lat");
        assertEquals(testGPS.getLon(), "Lon");
        assertEquals(testGPS.getElev(), "Elev");
    }

}
