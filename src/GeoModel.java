import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class GeoModel {
	
    
    private static LinkedHashMap<String, Continent> continents = new LinkedHashMap<String,Continent>();
    private static LinkedHashMap<String, Country> countries = new LinkedHashMap<String,Country>();
    private static LinkedHashMap<String, City> cities = new LinkedHashMap<String,City>();
    private static LinkedHashMap<String, PlaceOfInterest> places = new LinkedHashMap<String,PlaceOfInterest>();
    private static LinkedHashMap<String, PointOfInterest> points = new LinkedHashMap<String,PointOfInterest>();
    
	/** Utility field used by event firing mechanism. */
	private ArrayList<ActionListener> actionListenerList;
	
	public GeoModel()
	{
		//blank
	}
    
    public void addRegion(Region r) {
        // The getName used here returns the name of the class type,
        // not to be confused with the getName method in Region
        String type = r.getClass().getName();
        switch (type) {
        case ("Continent"):
            continents.put(r.toString(), (Continent)r);
        	
	        processEvent(
	        		new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add " + type));
            break;
        case ("Country"):
        	
        	Country tempCountry = (Country) r;
        
        
            countries.put(tempCountry.getName(), tempCountry);
           
        	
        	//add r to the countries list in the continent that is contained in the Country r. Confusing right?
        	continents.get(tempCountry.getContinent().toString()).countries.put(tempCountry.getName(), tempCountry);
        	
        	System.out.println(continents.get(tempCountry.getContinent().toString()).countries);
        	
            processEvent(
                    new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add " + type));
        	
            break;
        case ("City"):
        	City tempCity = (City) r;
        	
            cities.put(tempCity.getName(), tempCity);
        	//add r to the cities list in the city that is contained in the City r. So confusing!
        	countries.get(tempCity.getCountry().toString()).cities.put(tempCity.getName(), tempCity);
            
            processEvent(
                    new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add " + type));
        	
        	break;
        case ("PlaceOfInterest"):
        	PlaceOfInterest tempPlace = (PlaceOfInterest) r;
        	
            places.put(tempPlace.toString(), tempPlace);
            
            //Add place to each Region it is contained in
        	for(String region : tempPlace.getLocations().keySet())
        	{
        		searchAllData(region).addPlace(tempPlace.getName(), tempPlace);
        	}
        
            processEvent(
                    new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add " + type));
        	
        	break;
        case ("PointOfInterest"):
        	PointOfInterest tempPoint = (PointOfInterest) r;
        	
            points.put(tempPoint.getName(), tempPoint);
            
            //Add Point to each region it is contained in.
            for(String region : tempPoint.getLocations().keySet())
            {
            	searchAllData(region).addPoint(tempPoint.getName(), tempPoint);
            }
            	
            processEvent(
                    new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add " + type));
        
            break;
        }
        // Notify the listener
        processEvent(
                new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add " + type));
    }
    
    public void regionEdited()
    {
    	processEvent(
        		new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "region edited"));
    }
    
    public void removeRegion(Region r) {
        String rName = r.toString();
    	String type = r.getClass().getName();
        switch (type) {
       
        
        case ("Continent"):
        	//Remove countries
        	for(String country : continents.get(rName).countries.keySet())
        	{
//        		System.out.println(continents.get(rName).countries);
        		this.removeRegion(continents.get(rName).countries.get(country));
        		
        	}
        	
        	//remove continent from places
        	for(String place : continents.get(rName).places.keySet())
        	{
        		continents.get(rName).places.get(place).locations.remove(rName);
        		
        		//if there are no more locations in this Place, remove it.
        		if(continents.get(rName).places.get(place).locations.isEmpty())
        		{
        			removeRegion(continents.get(rName).places.get(place));
        		}
        	}
        	
        	//remove continent from points
        	for(String point : continents.get(rName).points.keySet())
        	{
        		continents.get(rName).points.get(point).locations.remove(rName);
        		
        		//if there are no more locations in this Place, remove it.
        		if(continents.get(rName).points.get(point).locations.isEmpty())
        		{
        			removeRegion(continents.get(rName).points.get(point));
        		}
        	}
        	
        	continents.remove(rName);
        	
        	processEvent(
                    new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "remove " + type));
            break;
           
            
            
        case ("Country"):
        	
        	if(countries.isEmpty()) break;
        
        	//remove from Continent
        	Country tmpCountry = (Country) r;
        	tmpCountry.getContinent().countries.remove(rName);
        	
        	//remove cities
        	for(String city : countries.get(rName).cities.keySet())
        	{
        		this.removeRegion(countries.get(rName).cities.get(city));
        	}
        	
        	
        	//remove from places
	    	for(String place : countries.get(rName).places.keySet())
	    	{
	    		countries.get(r.toString()).places.get(place).locations.remove(rName);
	    		
	    		//if there are no more locations in this Place, remove it.
	    		if(countries.get(r.toString()).places.get(place).locations.isEmpty())
	    		{
	    			removeRegion(countries.get(rName).places.get(place));
	    		}
	    	}
	    	
	    	//remove from points
        	for(String point : countries.get(rName).points.keySet())
        	{
        		countries.get(rName).points.get(point).locations.remove(rName);
        		
        		//if there are no more locations in this Place, remove it.
        		if(countries.get(rName).points.get(point).locations.isEmpty())
        		{
        			removeRegion(countries.get(rName).points.get(point));
        		}
        	}
	    	
	    	
            countries.remove(rName);
            
            processEvent(
                    new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "remove " + type));
            break;
      
        
        case ("City"):
        	
        	if(cities.isEmpty()) break;
        	
        
	    	//remove from city
	    	City tmpCity = (City) r;
	    	tmpCity.getCountry().cities.remove(rName);
        
        	//remove from places
	    	for(String place : cities.get(rName).places.keySet())
	    	{
	    		cities.get(rName).places.get(place).locations.remove(rName);
	    		
	    		//if there are no more locations in this Place, remove it.
	    		if(cities.get(rName).places.get(place).locations.isEmpty())
	    		{
	    			removeRegion(cities.get(rName).places.get(place));
	    		}
	    	}
	    	
	    	//remove from points
        	for(String point : cities.get(rName).points.keySet())
        	{
        		cities.get(rName).points.get(point).locations.remove(rName);
        		
        		//if there are no more locations in this Place, remove it.
        		if(cities.get(rName).points.get(point).locations.isEmpty())
        		{
        			removeRegion(cities.get(rName).points.get(point));
        		}
        	}
        	
            cities.remove(rName);
            
            processEvent(
                    new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "remove " + type));
            break;
        
        
        
        case ("PlaceOfInterest"):
        	if(places.isEmpty()) break;
        	
        	if(places.get(rName).locations.isEmpty())
        	{
        		places.remove(rName);
        	}
        	
        	else
        	{
        		for(String location : places.get(rName).locations.keySet())
        		{
        			//for each location in Place r, remove this Place from the places list in each location.
        			places.get(rName).locations.get(location).places.remove(rName);
        		}
        		
        		places.remove(rName);
        	}
        
        	processEvent(
                new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "remove " + type));
        	
            break;
        
        
        case ("PointOfInterest"):
        	
        	if(points.isEmpty()) break;
        	
        	for(String point : points.get(rName).points.keySet())
        	{
        		this.removeRegion(points.get(rName).points.get(point));       		
        	}
        
        	if(points.get(rName).location == null)
        	{
        		points.remove(rName);
        	}
        	
        	else
        	{
        		for(String location : points.get(rName).locations.keySet())
        		{
        			//for each location in Place r, remove this Place from the places list in each location.
        			points.get(rName).locations.get(location).points.remove(rName);
        		}
        		
        		points.remove(rName);
        	}
        	
        	processEvent(
                    new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "remove " + type));
        	
            break;
        }
        // Notify the listener
        processEvent(
                new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "remove " + type));        
    }
    
    public LinkedHashMap<String, Continent> getContinents() {
        return continents;
    }
    
    public LinkedHashMap<String, Country> getCountries() {
        return countries;
    }

    public LinkedHashMap<String, City> getCities() {
        return cities;
    }

    public LinkedHashMap<String, PlaceOfInterest> getPlaces() {
        return places;
    }

    public LinkedHashMap<String, PointOfInterest> getPoints() {
        return points;
    }
    
    public static boolean qcAllData()
    {
    	for(String region : continents.keySet())
    	{
//    		System.out.println(continents.get(region).name);
    		if(continents.get(region).dataQC() == false)
    		{
    			System.out.println("Error in " + continents.get(region).getName());

    			return false;
    		}
    			
    		
    	}
    	
    	for(String region : countries.keySet())
    	{
    		if(countries.get(region).dataQC() == false)
    		{
    			System.out.println("Error in countries: " + countries.get(region));
    			return false;
    		}
    			
    	}
    	

    	
    	for(String region : cities.keySet())
    	{
    		if(cities.get(region).dataQC() == false)
    		{
    			System.out.println("Error in cities");
//    			return false;
    		}
    			
    	}
    	
    	for(String region : places.keySet())
    	{
    		if(places.get(region).dataQC() == false)
    		{
    			System.out.println("Error in places");
//    			return false;
    		}
    			
    	}
    	
    	for(String region : points.keySet())
    	{
    		if(points.get(region).dataQC() == false)
    		{
    			System.out.println("Error in poinst");
//    			return false;
    		}
    			
    	}
    	
    	return true;
    }

	/**
	 * This method reads in, parses, and assigns variables
	 * @param file File to be read in 
	 * @throws IOException
	 */
	public void readTextFile(String file, String type) throws IOException
	{
		//opens and reads the file 
		FileReader fr = new FileReader(file);
		
		BufferedReader br = new BufferedReader(fr);
		
		String line; //variable that stores the line temporarily 
		
		int test = 0; //tester for loop below
		
		//iterates through all the lines in the file and parses them using parseLine()
		do
		{
			line = br.readLine();
			if(line != null)
			{
				parseLine(line, file, type);
			}
			else
			{
				test = 1;
			}
		}while(test == 0);
		
		//closes the file 		
		br.close();
		
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "load text"));
		
	}
	

	/**
	 * This method parses a line by commas
	 * @param line Line to be parsed
	 * @param file Filename
	 */
	private void parseLine(String line, String file, String type)
	{
		String[] array = line.split(", ");
		
		assignVariables(array, file, type);
	}

	/**
	 * This method will assign variable to their respective values	
	 * @param array Array that contains values
	 * @param file Name of the file
	 * @param type What kind of data the file contains
	 */
	private void assignVariables(String[] array, String file, String type)
	{	
//		System.out.println(array[0] + " " +  array[1] + " " + array[2]);
		switch(type)
		{
			case "continents":
//				System.out.println("added " + array[0]);
				continents.put(array[0], new Continent(array[0],array[1],array[2]));
				
//				processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "continent added"));
				
				break;

		
			case "countries":
				for(String continent : continents.keySet())
				{
					if(continent.equals(array[3]))
					{
						Country country = new Country(array[0],array[1],array[2],continents.get(continent));	
						continents.get(continent).countries.put(country.getName(), country);
						
						//QC
						if(continents.get(continent).dataQC() == false)
						{
							System.out.println("There is a problem with your data. Specifically " + continents.get(continent));
							
//							break;
							
						}
						
						countries.put(country.getName(), country);
						
//						processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "country added"));
						
						break;
					}//end if
				}//end for
		
			case "cities":
			
				if(array.length == 7)
				{
					
					
					for(String continent : continents.keySet())
					{
						for(String country : continents.get(continent).countries.keySet())
						{
							if(country.equals(array[3]))
							{	
								City city = new City(array[0],array[1],array[2],continents.get(continent).countries.get(country),
										array[4],array[5],array[6]);
								continents.get(continent).countries.get(country).cities.put(city.getName(), city);
								
								//QC
								if(continents.get(continent).countries.get(country).dataQC() == false)
								{
									System.out.println("There is a problem with your data. Specifically " + 
											continents.get(continent).countries.get(country));
									
//									break;
									
								}
								
								cities.put(city.getName(),city);
								
//								processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "city added"));
								
								break;
								
							}//end if
						}//end country for
					}//end continent for
				}//end if 
				
				else
				{
					
					for(String continent : continents.keySet())
					{
						for(String country : continents.get(continent).countries.keySet())
						{
							if(country.equals(array[3]))
							{
								City city = new City(array[0],array[1],array[2],continents.get(continent).countries.get(country));
								continents.get(continent).countries.get(country).cities.put(city.getName(), city);
								
								//QC
								if(continents.get(continent).countries.get(country).dataQC() == false)
								{
									System.out.println("There is a problem with your data. Specifically " + 
											continents.get(continent).countries.get(country));
									
//									break;
									
								}
								
								cities.put(city.getName(),city);
								
//								processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "city added"));
//								System.out.println(city.getName() + " added to " + continents.get(continent).countries.get(country));
							}
						}//end country for
					}//end continent for
				}//end else	
				break;
				
			case "places":
				LinkedHashMap<String,Region> regions = new LinkedHashMap<String,Region>();
				PlaceOfInterest place;
				for(int i = 3; i < array.length; i++)
				{
					if(searchAllData(array[i]) != null) regions.put(array[i],searchAllData(array[i]));
				}
				
				place = new PlaceOfInterest(array[0],array[1],array[2], regions);
				
//				System.out.println(regions);
				
				for(String region : regions.keySet())
				{
//					System.out.println(regions.get(region));
					regions.get(region).addPlace(place.getName(), place);
					
					//QC
					if(regions.get(region).dataQC() == false)
					{
						System.out.println("There is a problem with your data. Specifically " + 
								regions.get(region))
								;
						
//						break;
						
					}
					
				}
				
				
				
				places.put(place.getName(), place);
				
//				processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "place added"));
				
				break;
				
			case "points":
				LinkedHashMap<String,Region> regions1 = new LinkedHashMap<String,Region>();
				PointOfInterest point;
				for(int i = 5; i < array.length; i++)
				{
					if(searchAllData(array[i]) != null) regions1.put(array[i],searchAllData(array[i]));
				}
				
				point = new PointOfInterest(array[0],array[1],array[2],array[3], array[4], regions1);
				
				for(String region : regions1.keySet())
				{
					regions1.get(region).addPoint(point.getName(), point);
					
					//QC
					if(regions1.get(region).dataQC() == false)
					{
						System.out.println("There is a problem with your data. Specifically " + 
								regions1.get(region));
						
//						break;
						
					}//end if QC
				}//end for
				points.put(point.getName(), point);
				
//				processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "point added"));
				
				break;
				
			default: System.out.println("The type entered does not exist!!");
			
			
		}//end switch
			
	}//end Assign
    
	/**
	 * Searches all the data in GeoModel for the desired Region and returns a reference to it
	 * @param name Name of the desired Region
	 * @return Desired reference of the Region
	 */
	private static Region searchAllData(String name)
	{
		if(continents.containsKey(name)) return continents.get(name);
		else if(countries.containsKey(name)) return countries.get(name);
		else if(cities.containsKey(name)) return cities.get(name);
		else if(places.containsKey(name)) return places.get(name);
		else if(points.containsKey(name)) return points.get(name);
 		else return null;
	}
	
	/**
	 * This method reads in 5 linked hash maps from a binary file and assigns
	 * them to the appropriate lists in the model
	 * @param file Binary file to be read in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
    // For the unresolvable cast from object to linkedhashmap
	public void readBinary(String file) throws IOException, ClassNotFoundException
	{
		FileInputStream fileInputStream = new FileInputStream(file); 
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream); 
		continents = (LinkedHashMap<String,Continent>) objectInputStream.readObject();
		countries = (LinkedHashMap<String,Country>) objectInputStream.readObject();
		cities = (LinkedHashMap<String,City>) objectInputStream.readObject();
		places = (LinkedHashMap<String,PlaceOfInterest>) objectInputStream.readObject();
		points = (LinkedHashMap<String,PointOfInterest>) objectInputStream.readObject();
		objectInputStream.close(); 
		
		if(qcAllData() == false)
		{
			System.out.println("Your data doesn't make sense! Exiting the program");
		}
		
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "load binary"));
		
	}
	
	/**
	 * This writes the information to a file determined by the User
	 * @param list List Region objects are contained in	
	 * @param filename Name of file to be written
	 * @param fileType	Type of file to be written (Text or Binary)
	 * @throws IOException
	 */
	public void writeFile (String filename, String fileType)
	        throws IOException
	{
		switch(fileType)
		{
		
		
			case "Text":
				//continents.txt
				FileWriter continent = new FileWriter("continents.txt");
				BufferedWriter continentbw = new BufferedWriter(continent);
				String line = null;
				for(String region : continents.keySet())
				{
					line = continents.get(region).toString();
					continentbw.write(line);
					continentbw.newLine();
				}
				continentbw.close();
				
				//countries.txt
				FileWriter country = new FileWriter("countries.txt");
				BufferedWriter countrybw = new BufferedWriter(country);
				line = null;
				for(String region : countries.keySet())
				{
					line = countries.get(region).toString();
					countrybw.write(line);
					countrybw.newLine();
				}
				
				countrybw.close();
				
				//cities.txt
				FileWriter city = new FileWriter("cities.txt");
				BufferedWriter citybw = new BufferedWriter(city);
				line = null;
				for(String region : cities.keySet())
				{
					line = cities.get(region).toString();
					citybw.write(line);
					citybw.newLine();
				}
				citybw.close();
				
				FileWriter place = new FileWriter("places.txt");
				BufferedWriter placebw = new BufferedWriter(place);
				line = null;
				for(String region : places.keySet())
				{
					line = places.get(region).toString();
					placebw.write(line);
					placebw.newLine();
				}
				placebw.close();
				
				FileWriter point = new FileWriter("points.txt");
				BufferedWriter pointbw = new BufferedWriter(point);
				line = null;
				for(String region : points.keySet())
				{
					line = points.get(region).toString();
					pointbw.write(line);
					pointbw.newLine();
				}
				pointbw.close();
				
				processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "export text"));
				
				break;
				
			case "Binary":
				FileOutputStream fileOutputStream = new FileOutputStream(filename); 
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream); 
				objectOutputStream.writeObject(continents);
				objectOutputStream.writeObject(countries);
				objectOutputStream.writeObject(cities);
				objectOutputStream.writeObject(places);
				objectOutputStream.writeObject(points);
				objectOutputStream.close();
				
				processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "save binary"));
				
				break;
		}
		
	}
	
	/** Register an action event listener */
	public synchronized void addActionListener(ActionListener l) {
		if (actionListenerList == null)
			actionListenerList = new ArrayList<ActionListener>();

		actionListenerList.add(l);
	}

	/** Remove an action event listener */
	public synchronized void removeActionListener(ActionListener l) {
		if (actionListenerList != null && actionListenerList.contains(l))
			actionListenerList.remove(l);
	}

	/** Fire Event */
	private void processEvent(ActionEvent e) {
		ArrayList<ActionListener> list;

		synchronized (this) {
			if (actionListenerList == null) return;
			list = (ArrayList<ActionListener>)actionListenerList.clone();
		}

		for (int i = 0; i < list.size(); i++) {
			ActionListener listener = list.get(i);
			listener.actionPerformed(e);
		}
	}
	
	
//	public String toString()
//	{
//		return "I am a model";
//		
//	}

    
    
}
