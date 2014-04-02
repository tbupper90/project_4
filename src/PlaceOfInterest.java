import java.util.*;

/**
 * 
 * This class contains information for places of interest.
 *
 */
public class PlaceOfInterest extends Region
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5566762757484533693L;
	

	private String description;
	private LinkedHashMap<String,String> location;
	
	/**
	 * This is the constructor for the PlaceOfInterest object.
	 * @param name Name of the place
	 * @param area Area of the place
	 * @param description Description of the place
	 * @param location Countries or continent the place belongs to
	 */
	public PlaceOfInterest(String name, String area, 
			String description, LinkedHashMap<String, String> location)
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
	 * @return The place's area
	 */
	public String getArea()
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
	 * @return The location the place belongs to
	 */
	public LinkedHashMap<String, String> getLocation()
	{
		return null;
	}
	
	public String toString()
	{
		return "";
	}
}
