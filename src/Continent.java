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
		this.name = name;
		this.pop = pop;
		this.area = area;
	}


	/**
	 * THis method will add a country to the Array list
	 * @param country Country object to be added
	 * 
	 */
	public void addCountry(String name, Country country)
	{
		countries.put(name, country);
	}
	
	
	public LinkedHashMap<String, Country> getCountries()
	{
		return countries;
	}
	
	public boolean dataQC()
	{
		double runningSum = 0;
		for(String country : countries.keySet())
		{
			runningSum += Double.parseDouble(countries.get(country).area);
		}
		
		if(runningSum > Double.parseDouble(area))
		{
			return false;
		}
		else return true;
	}
	
	public String toString()
	{
		return name + " " + countries + "\n";
	}
	
	
}
