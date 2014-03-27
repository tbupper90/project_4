import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class sortTest {
    
    public ArrayList<Region> makeTestList() {
        // List with unique out-of-order elements to test sorting methods
        ArrayList<Region> testList = new ArrayList<Region>();
        testList.add(new Continent("Name1", "333", "22"));
        testList.add(new Continent("Name2", "111", "33"));
        testList.add(new Continent("Name3", "222", "11"));
        return testList;
    }

    public ArrayList<City> makeTestCity() {
        // List with unique out-of-order elements to test sorting methods
        ArrayList<City> testList = new ArrayList<City>();
        testList.add(new City("Name1", "333", "22", "Coun1", "N3", "E2", "1"));
        testList.add(new City("Name2", "111", "33", "Coun2", "N1", "E3", "2"));
        testList.add(new City("Name3", "222", "11", "Coun3", "N2", "E1", "3"));
        return testList;
    }
    
    
    @Test
    public void testSortByArea() {
        ArrayList<Region> list = makeTestList();
        sort.sortByArea(list);
        assertEquals(list.get(0).getArea(), "33");
    }

    @Test
    public void testSortByPopulation() {
        ArrayList<Region> list = makeTestList();
        sort.sortByPopulation(list);
        assertEquals(list.get(0).getPop(), "333");
    }

    @Test
    public void testSortByLat() {
        ArrayList<City> list = makeTestCity();
        sort.sortByLat(list);
        assertEquals(list.get(0).getLat(), "N1");
    }

    @Test
    public void testSortByLon() {
        ArrayList<City> list = makeTestCity();
        sort.sortByLon(list);
        assertEquals(list.get(0).getLon(), "E1");
    }

    @Test
    public void testSortByElev() {
        ArrayList<City> list = makeTestCity();
        sort.sortByElev(list);
        assertEquals(list.get(0).getElev(), "3");
    }

    @Test
    public void testSortLexi() {
        ArrayList<Region> list = makeTestList();
        sort.sortByLexi(list);
        assertEquals(list.get(0).getName(), "Name1");
    }

    @Test
    public void testSortRandomly() {
        // Nothing to assert for a random sort, so run and print the results to
        // ensure the method works
        ArrayList<Region> list = makeTestList();
        for (int i = 0; i < 3; i++) {
            sort.sortRandomly(list);
            for (Region iter: list) {
                System.out.println(iter.getName());
            }            
        }
    }
    
    

}
