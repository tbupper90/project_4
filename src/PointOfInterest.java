
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
	private String location;
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
	public PointOfInterest(String name, String description, String location,
			String lat, String lon, String elev)
	{

	}
	
	/**
	 * @return The place's name
	 */
	public String getName()
	{
		return "";
	}
	
	/**
	 * @return The place's description
	 */
	public String getDescription()
	{
		return "";
	}
		
	/**
	 * @return The place's location
	 */
	public String getLocation()
	{
		return "";
	}
		
	/**
	 * @return The point's latitude
	 */
	public String getLat()
	{
		return "";
	}
	
	/**
	 * @return The point's longitude
	 */
	public String getLon()
	{
		return "";
	}

	/**
	 * @return The point's elevation
	 */
	public String getElev()
	{
		return "";
	}

	public String toString()
	{
		return "";
	}
}
