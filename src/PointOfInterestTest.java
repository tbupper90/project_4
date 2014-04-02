import static org.junit.Assert.*;

import org.junit.Test;


public class PointOfInterestTest {

	@Test
	public void testPointOfInterest() {
        // Test correct storage of attributes
        PointOfInterest testPoint = new PointOfInterest("Name", "Description",
        		"Location", "Lat", "Lon", "Elev");

        assertEquals(testPoint.getName(), "Name");
        assertEquals(testPoint.getDescription(), "Description");
        assertEquals(testPoint.getLocation(), "Location");
        assertEquals(testPoint.getLat(), "Lat");
        assertEquals(testPoint.getLon(), "Lon");
        assertEquals(testPoint.getElev(), "Elev");
	}

}
