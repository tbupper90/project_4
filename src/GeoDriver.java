import java.awt.EventQueue;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.SwingUtilities;


public class GeoDriver {
	private GeoModel model = new GeoModel();
	private GeoView view = new GeoView();
	private GeoController controller = new GeoController();
	

	
	public GeoDriver() {
//		System.out.println(model);
		view.setModel(model);
		controller.setModel(model);
		controller.setView(view);

/*		
		Continent cont1 = new Continent("Continent1", "1000", "300");
        Continent cont2 = new Continent("Continent2", "2000", "280");
        Continent cont3 = new Continent("Continent3", "3000", "260");
        Continent cont4 = new Continent("Continent4", "4000", "150");
		Country count1 = new Country("Country1", "1000", "3000", cont1);
        Country count2 = new Country("Country2", "1500", "200", cont1);
        Country count3 = new Country("Country3", "2000", "120", cont1);
        Country count4 = new Country("Country4", "4000", "50", cont1);
        City city1 = new City("City1", "1000", "500", count1);
        City city2 = new City("City2", "2000", "300", count1);
		
        LinkedHashMap<String, Region> placeLocs = new LinkedHashMap<String, Region>();
        placeLocs.put(cont1.toString(), cont1);
        PlaceOfInterest place1 = new PlaceOfInterest("Place1", "300", null, placeLocs);        
        PlaceOfInterest place2 = new PlaceOfInterest("Place2", "200", null, placeLocs);        
        
        Region pointLoc = count1;
        PointOfInterest point1 = new PointOfInterest("Point1", null, null, null, null, pointLoc);
        PointOfInterest point2 = new PointOfInterest("Point2", null, null, null, null, pointLoc);

        model.addRegion(cont1);
        model.addRegion(cont2);
        model.addRegion(cont3);
        model.addRegion(cont4);

        model.addRegion(count1);
        model.addRegion(count2);
        model.addRegion(count3);
        model.addRegion(count4);

        model.addRegion(city1);
        model.addRegion(city2);

        model.addRegion(place1);
        model.addRegion(place2);

        model.addRegion(point1);
        model.addRegion(point2);
*/
		
        try {
            model.readTextFile("continents.csv", "Continents");
            model.readTextFile("countries.csv", "Countries");
            model.readTextFile("cities.csv", "Cities");
            model.readTextFile("places.csv", "Places of Interest");
            model.readTextFile("points.csv", "Points of Interest");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
//        System.out.println(model.getContinents());
		
	}
	
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new GeoDriver();
            }
        });

	}
	
	public static void testStuff() {
	    
	}

}
