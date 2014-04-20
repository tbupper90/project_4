import java.util.LinkedHashMap;


public class GeoDriver {
	private GeoModel model = new GeoModel();
	private GeoView view = new GeoView();
	private GeoController controller = new GeoController();
	

	
	public GeoDriver() {
//		System.out.println(model);
		view.setModel(model);
		controller.setModel(model);
		controller.setView(view);

		Continent cont1 = new Continent("Continent1", "1000", "300");
        Continent cont2 = new Continent("Continent2", "2000", "280");
        Continent cont3 = new Continent("Continent3", "3000", "260");
        Continent cont4 = new Continent("Continent4", "4000", "150");
		Country count1 = new Country("Country1", "1000", "200", cont1);
        Country count2 = new Country("Country2", "1500", "180", cont1);
        Country count3 = new Country("Country3", "2000", "120", cont2);
        Country count4 = new Country("Country4", "4000", "50", cont2);
        City city1 = new City("City1", "1000", "500", count1);
		
        LinkedHashMap<String, Region> placeLocs = new LinkedHashMap<String, Region>();
        placeLocs.put(cont1.toString(), cont1);
        PlaceOfInterest place1 = new PlaceOfInterest("Place1", "300", null, placeLocs);        
        
        Region pointLoc = count1;
        PointOfInterest point1 = new PointOfInterest("Point1", null, null, null, null, pointLoc);

        model.addRegion(cont1);
        model.addRegion(cont2);
        model.addRegion(cont3);
        model.addRegion(cont4);
		model.addRegion(count1);
        model.addRegion(count2);
        model.addRegion(count3);
        model.addRegion(count4);
        model.addRegion(city1);
        model.addRegion(place1);
        model.addRegion(point1);
      
//        System.out.println(model.getContinents());
		
	}
	
	public static void main(String[] args) {
		new GeoDriver();

	}

}
