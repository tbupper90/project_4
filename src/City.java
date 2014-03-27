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
		this.name = name;
		this.pop = pop;
		this.area = area;
		this.country = country;
		this.lat = "";
		this.lon = "";
		this.elev = "";
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
		this.name = name;
		this.pop = pop;
		this.area = area;
		this.country = country;
		this.lat = lat;
		this.lon = lon;
		this.elev = elev;
	}
	/*
	 * These methods will get the different variables of the City
	 */
	
	public String getCountry()
	{
		return country;
	}
	
	public String getLat()
	{
		return lat;
	}
	
	public String getLon()
	{
		return lon;
	}
	
	public String getElev()
	{
		return elev;
	}
	
	public String toString()
	{
		return name;
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
				if(o2.getElev().equals("") && (o1.getElev().equals("") == false))
				{
					return -1;
				}
				else if(o1.getElev().equals("") && (o2.getElev().equals("") == false))
				{
					return 1;
				}
				
				else if(o2.getElev().equals("") && o1.getElev().equals(""))
				{
					return 0;
				}
				
				//if neither contain empty string, compare the lats
				if(o1.getLat().compareTo(o2.getLat()) < 0)
				{
					return -1;
				}
				
				else if(o1.getLat().compareTo(o2.getLat()) > 0)
				{
					return 1;
				}
				
				else return 0;
			}
		};
		
		public static Comparator<City> LON = new Comparator<City>()
		{
			@Override
			public int compare(City o1, City o2)
			{
				//checks for empty strings
				if(o2.getElev().equals("") && (o1.getElev().equals("") == false))
				{
					return -1; 
				}
				else if(o1.getElev().equals("") && (o2.getElev().equals("") == false))
				{
					return 1;
				}
				
				else if(o2.getElev().equals("") && o1.getElev().equals(""))
				{
					return 0;
				}
				
				//if neither object contains an empty string...
				if(o1.getLon().compareTo(o2.getLon()) < 0)
				{
					return -1;
				}
				
				else if(o1.getLon().compareTo(o2.getLon()) > 0)
				{
					return 1;
				}
				
				else return 0;
			}
		};
		
		public static Comparator<City> ELEV = new Comparator<City>()
		{
			@Override
			public int compare(City o1, City o2)
			{
				//checks for empty strings
				if(o2.getElev().equals("") && (o1.getElev().equals("") == false))
				{
					return -1; 
				}
				else if(o1.getElev().equals("") && (o2.getElev().equals("") == false))
				{
					return 1;
				}
				
				else if(o2.getElev().equals("") && o1.getElev().equals(""))
				{
					return 0;
				}
				
				
				//if there are no empty strings...
				int o1elev = Integer.parseInt(o1.getElev());
				int o2elev = Integer.parseInt(o2.getElev());
				
				if(o2elev < o1elev)
				{
					return -1;
				}
				
				else if(o2elev > o2elev)
				{
					return 1;
				}
				
				else return 0;
			}
		};
		
	}



	
	
}
