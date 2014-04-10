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
	

	 String description;
	 LinkedHashMap<String,Region> locations;
	
	/**
	 * This is the constructor for the PlaceOfInterest object.
	 * @param name Name of the place
	 * @param area Area of the place
	 * @param description Description of the place
	 * @param country Country the place belongs to
	 */
	public PlaceOfInterest(String name, String area, 
			String description, LinkedHashMap<String, Region> locations)
	{
		this.name = name;
		this.area = area;
		this.description = description;
		this.locations = locations;
	}
	
	/**
	 * @return The place's name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @return The place's area
	 */
	public String getArea()
	{
		return area;
	}
	
	/**
	 * @return The place's description
	 */
	public String getDescription()
	{
		return description;
	}
	
	public boolean dataQC()
	{
		double runningSum = 0;
		for(String location : locations.keySet())
		{
			runningSum += Double.parseDouble(locations.get(location).area);
//			System.out.println(location + ":" + runningSum + " compared to " + area) ;
		}
		
		if(runningSum < Double.parseDouble(area))
		{
			return false;
		}
		else return true;
	}
	
	/**
	 * @return The country the place belongs to
	 */
	public LinkedHashMap<String, Region> getLocations()
	{
		return locations;
	}
	
	public String toString()
	{
		return name;
	}
}
