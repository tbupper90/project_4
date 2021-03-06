import java.util.*;

/**
 * 
 * This class contains the information for a Country object
 *
 */
public class Country extends Region
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2851503159815089632L;

	/*
	 * a liked hash map of cities, as well as the variables for a country
	 * object
	 */
//	ArrayList<City> cities = new ArrayList<City>();
	LinkedHashMap<String,City> cities = new LinkedHashMap<String, City>();
	LinkedHashMap<String,PlaceOfInterest> places = new LinkedHashMap<String,PlaceOfInterest>();

	private String continent;
	
	/**
	 * creates a Country object
	 */
	/**
	 * This is the constructor for the Country object
	 * @param name Name of the country
	 * @param pop Population of the country
	 * @param area Area of the country
	 * @param continent Continent the country belongs to
	 */
	public Country(String name, String pop, String area, String continent)
	{
		this.name = name;
		this.pop = pop;
		this.area = area;
		this.continent = continent;
	}
	/**
	 * these methods will get the different variables of
	 * the City object
	 */
	
	public String getContinent()
	{
		return continent;
	}

	/**
	 * This method will add a city to the city array list
	 * @param name The name of the city being added
	 * @param city The city object to be added
	 */
	public void addCity(String name, City city)
	{
		cities.put(name.toLowerCase(), city);
	}
	
	public String toString()
	{
		return name;
	}
	

}


