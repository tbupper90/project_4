import java.util.*;


/**
 * This class contains information for a city object
 * 
 *
 */
public class City extends Region 
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7124684400952080970L;
	private String country;
	private String lat;
	private String lon;
	private String elev;
	

	/**
	 * This method creates a city object without lat, lon, or elevation
	 * @param name Name of the city
	 * @param pop Population of the city
	 * @param area Area of the city
	 * @param country Country the city is in
	 */
	public City(String name, String pop, String area, String country)
	{

	}

	
	/**
	 * This method will create a city object 
	 * @param name Name of the city
	 * @param pop Population of the city
	 * @param area Area of the city
	 * @param country Country the city is in
	 * @param lat Latitude
	 * @param lon Longitude
	 * @param elev Elevation
	 */
	public City(String name, String pop, String area, String country, String lat, String lon, String elev)
	{

	}
	/*
	 * These methods will get the different variables of the City
	 */
	
	public String getCountry()
	{
		return "";
	}
	
	public String getLat()
	{
		return "";
	}
	
	public String getLon()
	{
		return "";
	}
	
	public String getElev()
	{
		return "";
	}
	
	public String toString()
	{
		return "";
	}
	
	
	/**
	 * This class contains the comparators for a city object that aren't 
	 * inherited from a Region 
	 * 
	 *
	 */
	public static class Comparators
	{
		public static Comparator<City> LAT = new Comparator<City>()
		{
			@Override
			public int compare(City o1, City o2)
			{
				//checks for empty strings
				return 0;
				
				//if neither contain empty string, compare the lats
				//return 0;
			}
		};
		
		public static Comparator<City> LON = new Comparator<City>()
		{
			@Override
			public int compare(City o1, City o2)
			{
				//checks for empty strings
					return 0;
				
				//if neither object contains an empty string...
				//return 0;
			}
		};
		
		public static Comparator<City> ELEV = new Comparator<City>()
		{
			@Override
			public int compare(City o1, City o2)
			{
				//checks for empty strings
				return 0;
				
				//if there are no empty strings...
				
				//return 0;
			}
		};
		
	}



	
	
}
