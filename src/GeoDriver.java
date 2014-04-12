
public class GeoDriver {
	private GeoModel model = new GeoModel();
	private GeoView view = new GeoView();
	
	public GeoDriver() {
		view.setModel(model);
		Continent cont = new Continent("ContName", null, null);
		Continent cont2 = new Continent("ContName2", null, null);
		Country count = new Country("Name", null, null, cont);
		model.addRegion(cont);
		model.addRegion(cont2);
		//model.addRegion(count);
		model.removeRegion(cont);
		//model.removeRegion(cont2);
		
	}
	
	public static void main(String[] args) {
		new GeoDriver();

	}

}
