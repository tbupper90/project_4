import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

/**

 * This is the driver class and contains the main method, as well
 * as various methods that manipulate the files
 * 
 * CS2334 Tyler Bell, Terry Thayer, Stanton Kent
 */

public class Driver 
{	
//	
//	private static ArrayList<Region> list;
//	private static ArrayList<Continent> continents = new ArrayList<Continent>();
	
	private static LinkedHashMap<String, Continent> continents = 
			new LinkedHashMap<String, Continent>();
	private static LinkedHashMap<String, Region> list = new LinkedHashMap<String, Region>();
	
	private static String continentsFile;
	private static String countriesFile;
	private static String citiesFile;
	private static String placesFile;
	private static String binaryFile;
	

	/**
	 * This is the main method
	 * @param args 
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		boolean continueTest = true;
		String dataType;
		String sortMethod;

		//get correct files
		String[] files = Prompt.getFiles();
		
		continentsFile = files[0];
		countriesFile = files[1];
		citiesFile = files[2];
		placesFile = files[3];
		binaryFile = files[4];
		
		if(files[4].equals("") == false)
		{
			readBinary(binaryFile);
			System.out.println(continents);
		}
		else
		{
			//read in the files and assign the data contained within
			readTextFile(continentsFile);
			readTextFile(countriesFile);
			readTextFile(citiesFile);
			readTextFile(placesFile);
			
		}
		

		
//		continentsFile=args[0];
//		countriesFile=args[1];
//		citiesFile=args[2];
//		placesFile=args[3];
//		readTextFile(continentsFile);
//		readTextFile(countriesFile);
//		readTextFile(citiesFile);
//		readTextFile(placesFile);
		
		do{
    		dataType = Prompt.getDataType();
    		System.out.println(dataType);
    		list = sort.sortTypeOfData(continents, dataType);

    		sortMethod = Prompt.getSortMethod(dataType);
    		list = sort.performSort(list, sortMethod);

//          System.out.println(((City)list.entrySet().iterator().next()).getLon());
    		
//    		System.out.println(list);
    		Prompt.getOutputPreference(list, dataType, sortMethod);
    		
    		continueTest = Prompt.getContinue();    		
		}while(continueTest);
		
		JOptionPane.showMessageDialog(null, "Thank you for using GeoGrapher!",
		        "GeoGrapher", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	 
	// For the unresolvable cast from object to linkedhashmap
	@SuppressWarnings("unchecked")
	private static void readBinary(String file) throws IOException, ClassNotFoundException
	{
		FileInputStream fileInputStream = new FileInputStream(file); 
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream); 
		continents = (LinkedHashMap<String,Continent>) objectInputStream.readObject(); 
		objectInputStream.close(); 
	}

	/**
	 * This method reads in, parses, and assigns variables
	 * @param file File to be read in 
	 * @throws IOException
	 */
	private static void readTextFile(String file) throws IOException
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
	
	/*
	 * this method will split up the file
	 */
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
	 */
	private static void assignVariables(String[] array, String file)
	{		
		if(file.equals(continentsFile))
		{
			continents.put(array[0], new Continent(array[0],array[1],array[2]));	
		}
		
		else if(file.equals(countriesFile))
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
		
		else if(file.equals(citiesFile))
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
		
		else if(file.equals(placesFile))
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
	
	public static LinkedHashMap<String,Continent> getContinents()
	{
		return continents;
	}
}//end class