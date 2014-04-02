import java.util.*;

/**
 * This class contains the content for a continent object
 *
 */
public class Continent extends Region
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4924143675857621533L;
	/**
	 * This is the arraylist of countries contained in the coninent
	 */
	LinkedHashMap<String, Country> countries = new LinkedHashMap<String, Country>();

	/**
	 * This is the constructor for a Continent object
	 * @param name Name of the Continent
	 * @param pop Population of the Continent
	 * @param area Area of the Continent
	 */
	public Continent(String name, String pop, String area)
	{

	}


	/**
	 * THis method will add a country to the Array list
	 * @param country Country object to be added
	 * 
	 */
	public void addCountry(String name, Country country)
	{

	}
	
	public LinkedHashMap<String, Country> getCountries()
	{
		return null;
	}
	
	public String toString()
	{
		return "";
	}
	
	
}
