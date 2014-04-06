import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;


public class GeoModel {
    
    private LinkedHashMap<String, Continent> continents;
    private LinkedHashMap<String, Country> countries;
    private LinkedHashMap<String, City> cities;
    private LinkedHashMap<String, PlaceOfInterest> places;
    private LinkedHashMap<String, PointOfInterest> points;
    
    public void add(Region r) {
        // The getName used here returns the name of the class type,
        // not to be confused with the getName method in Region
        String type = r.getClass().getName();
        switch (type) {
        case ("Continent"):
            continents.put(r.toString(), (Continent)r);
            break;
        case ("Country"):
            countries.put(r.toString(), (Country)r);
            break;
        case ("City"):
            cities.put(r.toString(), (City)r);
            break;
        case ("PlaceOfInterest"):
            places.put(r.toString(), (PlaceOfInterest)r);
            break;
        case ("PointOfInterest"):
            points.put(r.toString(), (PointOfInterest)r);
            break;
        }
        // Notify the listener
        processEvent(
                new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add " + type));
    }
    
    public void remove(Region r) {
        String type = r.getClass().getName();
        switch (type) {
        case ("Continent"):
            continents.remove(r.toString());
            break;
        case ("Country"):
            countries.remove(r.toString());
            break;
        case ("City"):
            cities.remove(r.toString());
            break;
        case ("PlaceOfInterest"):
            places.remove(r.toString());
            break;
        case ("PointOfInterest"):
            points.remove(r.toString());
            break;
        }
        // Notify the listener
        processEvent(
                new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "remove " + type));        
    }
    
    public LinkedHashMap<String, Continent> getContinents() {
        return continents;
    }
    
    public LinkedHashMap<String, Country> getCountries() {
        return countries;
    }

    public LinkedHashMap<String, City> getCities() {
        return cities;
    }

    public LinkedHashMap<String, PlaceOfInterest> getPlaces() {
        return places;
    }

    public LinkedHashMap<String, PointOfInterest> getPoints() {
        return points;
    }

    private void processEvent(ActionEvent e) {
        // Fire appropriate events so that GeoView knows to update its lists
    }
}
