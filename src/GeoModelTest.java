import org.junit.Test;


public class GeoModelTest {

    @Test
    public void test() {
        Continent continent = new Continent("Name", "Pop", "Area");
        Country country = new Country("Name", "Pop", "Area",
        		new Continent("ConName", "ConPop", "ConArea"));
        grabType(continent);
        grabType(country);
    }

    public void grabType(Region r) {
        System.out.println(r.getClass().getName());
    }
}
