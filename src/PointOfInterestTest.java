import static org.junit.Assert.*;

import org.junit.Test;


public class PointOfInterestTest {
	Continent testLoc = new Continent("LocName", "LocPop", "LocArea");
	@Test
	public void testPointOfInterest() {
        // Test correct storage of attributes
        PointOfInterest testPoint = new PointOfInterest("Name", "Description",
        		"Lat", "Lon", "Elev", testLoc);

        assertEquals(testPoint.getName(), "Name");
        assertEquals(testPoint.getDescription(), "Description");
        assertEquals(testPoint.getLat(), "Lat");
        assertEquals(testPoint.getLon(), "Lon");
        assertEquals(testPoint.getElev(), "Elev");
        assertEquals(testPoint.getLocation(), testLoc);
	}

}