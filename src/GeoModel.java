import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;


public class GeoModel {
    
    private static LinkedHashMap<String, Continent> continents;
    private static LinkedHashMap<String, Country> countries;
    private static LinkedHashMap<String, City> cities;
    private static LinkedHashMap<String, PlaceOfInterest> places;
    private static LinkedHashMap<String, PointOfInterest> points;
    
    public void add(Region r) {
        // The getName used here returns the name of the class type,
        // not to be confused with the getName method in Region
        String type = r.getClass().getName();
        switch (type) {
        case ("Continent"):
            continents.put(r.toString(), (Continent)r);
            break;
        case ("Country"):
            countries.put(r.toString(), (Country)r);
            break;
        case ("City"):
            cities.put(r.toString(), (City)r);
            break;
        case ("PlaceOfInterest"):
            places.put(r.toString(), (PlaceOfInterest)r);
            break;
        case ("PointOfInterest"):
            points.put(r.toString(), (PointOfInterest)r);
            break;
        }
        // Notify the listener
        processEvent(
                new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add " + type));
    }
    
    public void remove(Region r) {
        String type = r.getClass().getName();
        switch (type) {
        case ("Continent"):
            continents.remove(r.toString());
            break;
        case ("Country"):
            countries.remove(r.toString());
            break;
        case ("City"):
            cities.remove(r.toString());
            break;
        case ("PlaceOfInterest"):
            places.remove(r.toString());
            break;
        case ("PointOfInterest"):
            points.remove(r.toString());
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

	/**
	 * This method reads in, parses, and assigns variables
	 * @param file File to be read in 
	 * @throws IOException
	 */
	public static void readTextFile(String file) throws IOException
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
				parseLine(line, file);
			}
			else
			{
				test = 1;
			}
		}while(test == 0);
		
		//closes the file 		
		br.close();
	}
	

	/**
	 * This method parses a line by commas
	 * @param line Line to be parsed
	 * @param file Filename
	 */
	private static void parseLine(String line, String file)
	{
		String[] array = line.split(", ");
		
		assignVariables(array, file);
	}

	/**
	 * This method will assign variable to their repective values	
	 * @param array Array that contains values
	 * @param file Name of the file
	 * @param fileType What kin
	 */
	private static void assignVariables(String[] array, String file)
	{		
		if(file.equals("continentsFile"))
		{
			continents.put(array[0], new Continent(array[0],array[1],array[2]));	
		}
		
		else if(file.equals("countriesFile"))
		{
			Country country = new Country(array[0],array[1],array[2],array[3]);
			
			for(String continent : continents.keySet())
			{
				if(continent.equals(country.getContinent()))
				{
					continents.get(continent).countries.put(country.getName(), country);
					break;
				}
			}
			
		}//end else if
		
		else if(file.equals("citiesFile"))
		{
			
			if(array.length == 7)
			{
				City city = new City(array[0],array[1],array[2],array[3],array[4],array[5],array[6]);
				
				for(String continent : continents.keySet())
				{
					for(String country : continents.get(continent).countries.keySet())
					{
						if(country.equals(city.getCountry()))
						{	
							continents.get(continent).countries.get(country).cities.put(city.getName(), city);
							System.out.println(city.getName() + " added to " + continents.get(continent).countries.get(country));
						}
					}//end country for
				}//end continent for
			}//end if 
			
			else
			{
				City city = new City(array[0],array[1],array[2],array[3]);
				
				for(String continent : continents.keySet())
				{
					for(String country : continents.get(continent).countries.keySet())
					{
						if(country.equals(city.getCountry()))
						{
							continents.get(continent).countries.get(country).cities.put(city.getName(), city);
							System.out.println(city.getName() + " added to " + continents.get(continent).countries.get(country));
						}
					}//end country for
				}//end continent for
			}//end else
			
		}
		
		else if(file.equals("placesFile"))
		{
			LinkedHashMap<String,String> countries = new LinkedHashMap<String,String>();
			PlaceOfInterest place;
			for(int i = 3; i < array.length; i++)
			{
				countries.put(array[i],array[i]);
			}
			
			place = new PlaceOfInterest(array[0], array[1],array[2],countries);
			
			for(int i = 3; i < array.length; i++)
			{			
				for(String continent : continents.keySet())
				{
					if(continents.get(continent).countries.containsKey(array[i]))
					{
						continents.get(continent).countries.get(array[i]).places.put(place.getName(), place);
						System.out.println(place + " added to " + continents.get(continent).countries.get(array[i]));
					}//end if
				}//end for
			}//end for
			
			
		}
		
	}//end Assign
    
	/**
	 * This method reads in 5 linked hash maps from a binary file and assigns
	 * them to the appropriate lists in the model
	 * @param file Binary file to be read in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
    // For the unresolvable cast from object to linkedhashmap
	@SuppressWarnings("unchecked")
	public static void readBinary(String file) throws IOException, ClassNotFoundException
	{
		FileInputStream fileInputStream = new FileInputStream(file); 
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream); 
		continents = (LinkedHashMap<String,Continent>) objectInputStream.readObject();
		countries = (LinkedHashMap<String,Country>) objectInputStream.readObject();
		cities = (LinkedHashMap<String,City>) objectInputStream.readObject();
		places = (LinkedHashMap<String,PlaceOfInterest>) objectInputStream.readObject();
		points = (LinkedHashMap<String,PointOfInterest>) objectInputStream.readObject();
		objectInputStream.close(); 
	}
	
	/**
	 * This writes the information to a file determined by the User
	 * @param list List Region objects are contained in	
	 * @param filename Name of file to be written
	 * @param fileType	Type of file to be written (Text or Binary)
	 * @throws IOException
	 */
	public static void fileWriter (LinkedHashMap<String,Region> list, String filename, String fileType)
	        throws IOException
	{
		switch(fileType)
		{
		
		
			case "Text":
				FileWriter outfile = new FileWriter(filename);
				BufferedWriter bw = new BufferedWriter(outfile);
				String line = null;
				for(String key : list.keySet())
				{
					line = list.get(key).toString();
					bw.write(line);
					bw.newLine();
				}
				bw.close();
			case "Binary":
				FileOutputStream fileOutputStream = new FileOutputStream(filename); 
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream); 
				objectOutputStream.writeObject(continents);
				objectOutputStream.writeObject(countries);
				objectOutputStream.writeObject(cities);
				objectOutputStream.writeObject(places);
				objectOutputStream.writeObject(points);
				objectOutputStream.close();
		}
		
	}
	
	private void processEvent(ActionEvent e) {
        // Fire appropriate events so that GeoView knows to update its lists
    }
    
    
}
