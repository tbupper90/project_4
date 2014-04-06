import static org.junit.Assert.*;

import org.junit.Test;


public class GeoModelTest {

    @Test
    public void test() {
        Continent continent = new Continent("Name", "Pop", "Area");
        Country country = new Country("Name", "Pop", "Area", "Continent");
        grabType(continent);
        grabType(country);
    }

    public void grabType(Region r) {
        System.out.println(r.getClass().getName());
    }
}
