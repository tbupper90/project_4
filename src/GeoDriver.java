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
		

		Continent cont = new Continent("ContinentName", null, null);
		Country count = new Country("CountryName", null, null, cont);
        City city = new City("CityName", null, null, count);

        

		
        LinkedHashMap<String, Region> placeLocs = new LinkedHashMap<String, Region>();
        placeLocs.put(cont.toString(), cont);
        PlaceOfInterest place = new PlaceOfInterest("PlaceName", null, null, placeLocs);

        Region pointLoc = count;
        PointOfInterest point = new PointOfInterest("PointName", null, null, null, null, pointLoc);

        model.addRegion(cont);
		model.addRegion(count);
        model.addRegion(city);
        model.addRegion(place);
        model.addRegion(point);
      
//        System.out.println(model.getContinents());
		
	}
	
	public static void main(String[] args) {
		new GeoDriver();

	}

}
