import java.util.LinkedHashMap;


public class GeoDriver {
	private GeoModel model = new GeoModel();
	private GeoView view = new GeoView();
	AddEditView add = new AddEditView(null);

	
	public GeoDriver() {
		view.setModel(model);
		add.setModel(model);

		Continent cont = new Continent("ContinentName", null, null);
		Country count = new Country("CountryName", null, null, cont);
        City city = new City("CityName", null, null, count);

        

		
        LinkedHashMap<String, Region> placeLocs = new LinkedHashMap<String, Region>();
        placeLocs.put(cont.toString(), cont);
        PlaceOfInterest place = new PlaceOfInterest("PlaceName", null, null, placeLocs);

        LinkedHashMap<String, Region> pointLocs = new LinkedHashMap<String, Region>();
        pointLocs.put(count.toString(), count);
        PointOfInterest point = new PointOfInterest("PointName", null, null, null, null, pointLocs);

        model.addRegion(cont);
		model.addRegion(count);
        model.addRegion(city);
        model.addRegion(place);
        model.addRegion(point);
      
		
	}
	
	public static void main(String[] args) {
		new GeoDriver();

	}

}
