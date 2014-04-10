import java.util.LinkedHashMap;


/**
 * 
 * This class contains information for points of interest.
 *
 */
public class PointOfInterest extends Region
{
    /**
     * 
     */
    private static final long serialVersionUID = -5566762757484533693L;

    private String description;
    LinkedHashMap<String,Region> locations;
    private String lat;
    private String lon;
    private String elev;

    /**
     * This is the constructor for the PlaceOfInterest object.
     * @param name Name of the place
     * @param description Description of the point
     * @param location Location the point belongs to
     * @param lat Latitude of the point
     * @param lon Longitude of the point
     * @param elev Elevation of the point
     */
    public PointOfInterest(String name, String description, 
            String lat, String lon, String elev, LinkedHashMap<String,Region> locations)
    {
    	this.name = name;
    	this.description = description;
    	this.locations = locations;
    	this.lat = lat;
    	this.lon = lon;
    	this.elev = elev;
    }

    /**
     * @return The place's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return The place's description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return The place's location
     */
    public LinkedHashMap<String,Region> getLocations()
    {
        return locations;
    }

    /**
     * @return The point's latitude
     */
    public String getLat()
    {
        return lat;
    }

    /**
     * @return The point's longitude
     */
    public String getLon()
    {
        return lon;
    }

    /**
     * @return The point's elevation
     */
    public String getElev()
    {
        return elev;
    }
    
    public boolean dataQC()
    {
    	for(String point : points.keySet())
    	{
    		if(this.elev.equals(points.get(point).getElev()) || this.lat.equals(points.get(point).getLat()) 
    				|| this.lon.equals(points.get(point).getLon()))
    			{
    				return false; 
    			}//end if
    	}//end for
    	
    	for(String location : locations.keySet())
    	{
    		if(this.area.equals(locations.get(location).area) == false)
    		{
    			return false;
    		}//end if
    	}//end for
    	
    	return true;
    }
    
    public String toString()
    {
        return name;
    }
}