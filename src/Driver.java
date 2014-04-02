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
	
	private static GeoView mainView = new GeoView();
	private static GeoController mainController = new GeoController();

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
		System.exit(0);
	}
	
	// For the unresolvable cast from object to linkedhashmap
	@SuppressWarnings("unchecked")
	private static void readBinary(String file) throws IOException, ClassNotFoundException
	{

	}

	/**
	 * This method reads in, parses, and assigns variables
	 * @param file File to be read in 
	 * @throws IOException
	 */
	private static void readTextFile(String file) throws IOException
	{
		//opens and reads the file 
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

	}

	/**
	 * This method will assign variable to their repective values	
	 * @param array Array that contains values
	 * @param file Name of the file
	 */
	private static void assignVariables(String[] array, String file)
	{		
		
	}//end Assign
	
	public static LinkedHashMap<String,Continent> getContinents()
	{
		return continents;
	}
}//end class