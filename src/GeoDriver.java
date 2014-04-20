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
        Continent cont2 = new Continent("Continent1", "2000", "200");
        Continent cont3 = new Continent("Continent1", "3000", "100");
		Country count1 = new Country("Country1", null, null, cont1);
        City city1 = new City("City1", null, null, count1);
		
        LinkedHashMap<String, Region> placeLocs = new LinkedHashMap<String, Region>();
        placeLocs.put(cont1.toString(), cont1);
        PlaceOfInterest place1 = new PlaceOfInterest("Place1", null, null, placeLocs);        
        
        Region pointLoc = count1;
        PointOfInterest point1 = new PointOfInterest("Point1", null, null, null, null, pointLoc);

        model.addRegion(cont1);
        model.addRegion(cont2);
        model.addRegion(cont3);
		model.addRegion(count1);
        model.addRegion(city1);
        model.addRegion(place1);
        model.addRegion(point1);
      
//        System.out.println(model.getContinents());
		
	}
	
	public static void main(String[] args) {
		new GeoDriver();

	}

}
