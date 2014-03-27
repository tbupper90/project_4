import java.util.*;


/**
 * This class contains all the sort methods
 *
 */
public class sort 
{

	public static ArrayList<Region> sortByArea(ArrayList<Region> list)
	{
		Collections.sort(list, Region.Comparators.AREA);
		return list;
	}
	
	public static ArrayList<Region> sortByPopulation(ArrayList<Region> list)
	{
		Collections.sort(list, Region.Comparators.POP);
		return list;
	}
	
	public static ArrayList<City> sortByLat(ArrayList<City> cities)
	{
		Collections.sort(cities, City.Comparators.LAT);
		return cities;	
	}
	
	public static ArrayList<City> sortByLon(ArrayList<City> cities)
	{
		Collections.sort(cities, City.Comparators.LON);
		return cities;
	}
	
	public static ArrayList<City> sortByElev(ArrayList<City> cities)
	{
		Collections.sort(cities, City.Comparators.ELEV);
		return cities;
	}
	
	public static ArrayList<Region> sortByLexi(ArrayList<Region> list)
	{
		Collections.sort(list, Region.Comparators.NAME);
		return list;
	}
	
	public static ArrayList<Region> sortRandomly(ArrayList<Region> list)
	{
		Collections.shuffle(list);
		return list;
	}
	
	public static LinkedHashMap<String, Region> sortTypeOfData(LinkedHashMap<String, Continent> continents, String dataType)
	{
		//list that will be returned 
		LinkedHashMap<String, Region> newList = new LinkedHashMap<String, Region>();
		
		//String array used to check for countries within continents, and cities within countries.
		String[] dataTypeArray = dataType.split("_");
				
		switch(dataType)
		{
		case "All continents": 
			newList.putAll(continents);
			return newList;
			
		case "All countries":
			for(String continent : continents.keySet())
			{
				newList.putAll(continents.get(continent).countries);
			}
			return newList;
			
		case "All cities":
			for(String continent : continents.keySet())
			{
				for(String country : continents.get(continent).countries.keySet())
				{
					newList.putAll(continents.get(continent).countries.get(country).cities);
				}
			}
			return newList;
    		
		case "All places of interest":    		
			for(String continent : continents.keySet())
			{
				for(String country : continents.get(continent).countries.keySet())
				{
					newList.putAll(continents.get(continent).countries.get(country).places);
				}
			}
    		return newList;
		}
		//had to make a different switch for the countries within and cities within. Will look at revising
		switch(dataTypeArray[1])
		{
			case "countrieswithin":
				for(String continent : continents.keySet())
				{
					if(continents.get(continent).getName().equalsIgnoreCase(dataTypeArray[2]))
					{
						newList.putAll(continents.get(continent).countries);
						return newList;
					}
				}
			
			case "citieswithin":
				for(String continent : continents.keySet())
				{
					for(String country : continents.get(continent).countries.keySet())
					{
						if(continents.get(continent).countries.get(country).getName().
								equalsIgnoreCase(dataTypeArray[2]))
						{
							newList.putAll(continents.get(continent).countries.get(country).cities);
							return newList;
						}
					}
				}
			
				
				
		}//end switch
		
		return null;
	}//end method
	
	
	public static ArrayList<Region> toArrayList(LinkedHashMap<String, Region> list)
	{
		ArrayList<Region> newList = new ArrayList<Region>();
		
		for(String key : list.keySet())
		{
			newList.add(list.get(key));
		}
		
		return newList;
	}
	
	public static LinkedHashMap<String,Region> toLHMap(ArrayList<Region> list)
	{
		LinkedHashMap<String,Region> newList = new LinkedHashMap<String,Region>();
		
		for(Region region : list)
		{
			newList.put(region.getName(), region);
		}
		
		return newList;
	}
	
	public static LinkedHashMap<String,Region> performSort(LinkedHashMap<String,Region> list, String sortMethod)
	{
		ArrayList<Region> arrayList = toArrayList(list);
		
		ArrayList<Region> newList = new ArrayList<Region>();
		ArrayList<City> cityList = new ArrayList<City>();	
		
		
		
		switch(sortMethod)
		{
		case "Area":
			newList = sortByArea(arrayList);
			return toLHMap(newList);
		
		case "Population":
			newList = sortByPopulation(arrayList);
			return toLHMap(newList);
		
		case "Lexicographic":
			newList = sortByLexi(arrayList);
			return toLHMap(newList);
		
		case "Latitude": 
			
		    for(Region city : arrayList)
			{
				cityList.add((City) city);
			}
			
			cityList = sortByLat(cityList);
			
			for(City city : cityList)
			{
				newList.add(city);
			}
			return toLHMap(newList);
			
		case "Longitude":
			
		    for(Region city : arrayList)
			{
				cityList.add((City) city);
			}
			
			cityList = sortByLon(cityList);
			
			for(City city : cityList)
			{
				newList.add(city);
			}
			return toLHMap(newList);	
		
		case "Elevation":
			
		    for(Region city : arrayList)
			{
				cityList.add((City) city);
			}
			
			cityList = sortByElev(cityList);
			
			for(City city : cityList)
			{
				newList.add(city);
			}
			return toLHMap(newList);
		
		case "Random":
		    newList = sortRandomly(arrayList);
		    return toLHMap(newList);
		    
		}
		
		return null;
					
	}//end method
	

	
	
}//end class
