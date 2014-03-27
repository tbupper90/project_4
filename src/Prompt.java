import java.util.*;
import java.awt.*;

import javax.swing.*;

import java.io.*;


/**
 * THis class will get the files as well as user input
 *
 */
public class Prompt 
{

	/**
	 * THis method will get the files	
	 * @return String array containing file names
	 */
	public static String[] getFiles()
	{
	    String[] fieldTitles = {"Continents", "Countries", "Cities", "Places", "Single Binary"};
	    JTextField[] textFields = new JTextField[fieldTitles.length];
        String[] fileNames = new String[fieldTitles.length];

        boolean fileError;
        int result;
	    
        JPanel panel = new JPanel();
        JPanel inputPanel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(inputPanel, BorderLayout.CENTER);
        inputPanel.setLayout(new GridLayout(fieldTitles.length + 1, 0));
        
        
        for (int i = 0; i < fieldTitles.length; i++)
        {
            textFields[i] = new JTextField(10);
            inputPanel.add(new JLabel(fieldTitles[i] + ":"));
            inputPanel.add(textFields[i]);            
        }
        panel.add(new JLabel("*Inputting binary file will override other fields"), BorderLayout.SOUTH);
        do {
            fileError = false;
            result = JOptionPane.showConfirmDialog(null, panel, 
                    "GeoData", JOptionPane.DEFAULT_OPTION);
            if (result == JOptionPane.CLOSED_OPTION)
            {
                System.exit(0);
            }
            
            //gets the file names
            for(int j = 0; j < fieldTitles.length; j++)
            {
            	fileNames[j] = textFields[j].getText();
            }
            
            //checks to see if there is a valid binary file
            for (int i = 0; i < fieldTitles.length - 1; i++)
            {
//            	fileNames[i] = textFields[i].getText();
                
            	//If there is a valid binary file, don't check for other files
                if (new File(fileNames[4]).exists() == true)
                {
                	break;
                	 
                }
                // Display an error message when a file is not found
                if (new File(fileNames[i]).exists() == false) 
                {
                   JOptionPane.showMessageDialog(null, fieldTitles[i] +
                           " file " + "\"" + fileNames[i] + "\"" +
                           " not found", "GeoGrapher",
                           JOptionPane.ERROR_MESSAGE);
                   fileError = true;
                }
            }        
            
            
        } while (fileError);
        
        return fileNames;		
	}
	

	 /**
	  * This method will get the data type the user inputs
	  * @return Data type user is interested in
	  */
	public static String getDataType()
	{
        String[] dataChoices = {"All continents", "All countries", "All cities", "All places of interest",
                "All countries within a continent",
                "All cities within a country"};
        // Create radio buttons, a group in which to tie them together,
        // and a panel on which to place them
        JRadioButton[] buttons = new JRadioButton[dataChoices.length];
        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(dataChoices.length,1));
        // Construct each button, add it to the group, and add it to the panel
        for (int i = 0; i < dataChoices.length; i++)
        {
            buttons[i] = new JRadioButton(dataChoices[i]);
            buttonGroup.add(buttons[i]);
            panel.add(buttons[i]);
        }
        buttons[0].setSelected(true);
        
        // Create a "JOptionPane" on which to put the panel
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("Show information for:");
        optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        optionPane.add(panel, 1);
        // Create a JDialog on which to display the JOptionPane, with panel 
        JDialog continueDialog = optionPane.createDialog(null,
                "GeoGrapher");
        continueDialog.setVisible(true);

        // Set the return String equal to the choice made
        String result = "";
        for (int i = 0; i < dataChoices.length; i++)
        {
            if (buttons[i].isSelected()) result = dataChoices[i];
        }
        
        // Override choices for countries and cities within areas
        if (buttons[4].isSelected())
        {
            result = "_countrieswithin_" + JOptionPane.showInputDialog(null,
                    "Which continent?", "GeoGrapher",
                    JOptionPane.QUESTION_MESSAGE);
            // Add while loop later to confirm valid entry
        }
        else if (buttons[5].isSelected())
        {
            result = "_citieswithin_" + JOptionPane.showInputDialog(null,
                    "Which country?", "GeoGrapher",
                    JOptionPane.QUESTION_MESSAGE);          
            // Add while loop later to confirm valid entry
        }
        	
		return result;
	}
	

	/**
	 * This will get the way the data will be sorted
	 * @param dataType Type of data user is interested in
	 * @return Way to be sorted as string
	 */
	public static String getSortMethod(String dataType)
	{
        String[] sortChoices;
        if (dataType.contains("cities"))
        {
            sortChoices = new String[] {"Area", "Population", "Latitude",
                                        "Longitude", "Elevation",
                                        "Lexicographic", "Random"};
        }
        else if(dataType.contains("places of interest"))
        {
        	sortChoices = new String[] {"Area", "Lexicographic",
            	"Random"};
        }
        else
        {
            sortChoices = new String[] {"Area", "Population", "Lexicographic",
                                        "Random"};
        }

        // Create radio buttons, a group in which to tie them together,
        // and a panel on which to place them
        JRadioButton[] buttons = new JRadioButton[sortChoices.length];
        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(sortChoices.length,1));
        // Construct each button, add it to the group, and add it to the panel
        for (int i = 0; i < sortChoices.length; i++)
        {
            buttons[i] = new JRadioButton(sortChoices[i]);
            buttonGroup.add(buttons[i]);
            panel.add(buttons[i]);
        }
        buttons[0].setSelected(true);
        
        // Create a "JOptionPane" on which to put the panel
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("Sort by:");
        optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        optionPane.add(panel, 1);
        // Create a JDialog on which to display the JOptionPane, with panel 
        JDialog continueDialog = optionPane.createDialog(null,
                "GeoGrapher");
        continueDialog.setVisible(true);

        // Set the return String equal to the choice made
        String result = "";
        for (int i = 0; i < sortChoices.length; i++)
        {
            if (buttons[i].isSelected()) result = sortChoices[i];
        }            
        
        return result;
	}
	

	/**
	 * this will determine whether to output to console, file, or get further data
	 * @param list List to be printed from 
	 * @param sortMethod Way the list was sorted
	 * @throws IOException
	 */
	public static void getOutputPreference(LinkedHashMap<String,Region> list,
	        String dataType, String sortMethod) throws IOException
	{
		String[] options;
		
		if (sortMethod == "Area" || sortMethod == "Population" ||
		    sortMethod == "Latitude" || sortMethod == "Longitude")
		{
		    options = new String[] {"Print to Screen",
                    "Print to Text File",
                    "Search for Particular Region",
                    "Serialize All Data",
                    "Graphical Display"};	    
		}
		else
		{
    	    options = new String[] {"Print to Screen",
    				"Print to Text File",
    				"Search for Particular Region",
    				"Serialize All Data"};
		}
		
	    JRadioButton[] buttons = new JRadioButton[options.length];
        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(options.length,1));
        // Construct each button, add it to the group, and add it to the panel
        for (int i = 0; i < options.length; i++)
        {
            buttons[i] = new JRadioButton(options[i]);
            buttonGroup.add(buttons[i]);
            panel.add(buttons[i]);
        }
        buttons[0].setSelected(true);
        
        // Create a "JOptionPane" on which to put the panel
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("What would you like to do?:");
        optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        optionPane.add(panel, 1);
        // Create a JDialog on which to display the JOptionPane, with panel 
        JDialog continueDialog = optionPane.createDialog(null,
                "GeoGrapher");
        continueDialog.setVisible(true);
		
		
		if(buttons[0].isSelected()) // Print to Screen
		{
		    String lists = "";
			for(String key : list.keySet())
			{
				lists = lists.concat(list.get(key).toString() + "\n");
			}
			lists = lists.substring(0, lists.length()-1);
			JScrollPane scrollPane = new JScrollPane(new JTextArea(lists));
			scrollPane.setPreferredSize(new Dimension(250,100));
			JOptionPane.showMessageDialog(null, scrollPane, "GeoGrapher",
			        JOptionPane.INFORMATION_MESSAGE);
		}
		else if(buttons[1].isSelected()) // Print to Text File
		{
			
			String filename = JOptionPane.showInputDialog(null,
			        "What is the filename?", "GeoGrapher",
			        JOptionPane.QUESTION_MESSAGE);
			fileWriter(list,filename,"Text");
			
		}
		else if(buttons[2].isSelected()) // Search for Particular Region
		{
			
			String region = JOptionPane.showInputDialog(null,
			        "What region would you like to know more about?",
			        "GeoGrapher", JOptionPane.QUESTION_MESSAGE);
			searchRegion(list, region, sortMethod);
		}
		
		else if(buttons[3].isSelected()) // Serialize All Data
		{
			String filename = JOptionPane.showInputDialog(null,
			        "What is the filename?", "GeoGrapher",
			        JOptionPane.QUESTION_MESSAGE);
			fileWriter(list,filename,"Binary");
		}
		
		else if (buttons[4].isSelected()) // Graphical Display
		{
		    String[] names = new String[list.size()];
		    // Can't iterate through HashMaps by index number, so...
		    int i = 0;
		    for (String key: list.keySet()) {
		        names[i] = list.get(key).getName();
		        i++;
		    }
		    long[] data = new long[list.size()];
		    String title = "Title";
		    
		    if (sortMethod == "Latitude" || sortMethod == "Longitude")
		    {
                title = "Location of ";
                if (dataType.contains("_citieswithin_")) {
                	title = title.concat("all cities within " + dataType.substring(14));
                } else {
    		    	title = title.concat(dataType.toLowerCase());
                }
                
		    	// Since Lat and Lon only apply to cities,
		        // there's no need to check dataType
		        String[][] lonLat = new String[list.size()][2];
		        i = 0;
		        for (String key: list.keySet()) {
		            lonLat[i][0] = ((City)list.get(key)).getLon(); // "x"
                    lonLat[i][1] = ((City)list.get(key)).getLat(); // "y"
                    if (lonLat[i][0].equals("")) break;
                    i++;
		        }
		        // Remake arrays to exclude entries with no latitudes/longitudes
		        names = Arrays.copyOf(names, i);
		        lonLat = Arrays.copyOf(lonLat, i);
		        
		        ShowGraphic.makeWorldMap(names, lonLat, title);
		    }
		    else if (sortMethod == "Population")
		    {
                title = "Population of ";
		    	if (dataType.contains("_countrieswithin_")) {
                	title = title.concat("all countries within " + dataType.substring(17));
		    	} else if (dataType.contains("_citieswithin_")) {
                	title = title.concat("all cities within " + dataType.substring(14));
                } else {
    		    	title = title.concat(dataType.toLowerCase());
                }
                
		    	i = 0;
                for (String key: list.keySet()) {
                    data[i] = Long.parseLong(list.get(key).getPop());
                    i++;
                }
		        ShowGraphic.makeBarGraph(names, data, title);
		    }
		    else if (sortMethod == "Area")
		    {
                title = "Area of ";
		    	if (dataType.contains("_countrieswithin_")) {
                	title = title.concat("all countries within " + dataType.substring(17));
		    	} else if (dataType.contains("_citieswithin_")) {
                	title = title.concat("all cities within " + dataType.substring(14));
                } else {
    		    	title = title.concat(dataType.toLowerCase());
                }

		    	i = 0;
	            for (String key: list.keySet()) {
	                data[i] = Long.parseLong(list.get(key).getArea());
	                i++;
	            }
		        if (dataType.contains("within"))
		        {
		            ShowGraphic.makeSegmentGraph(names, data, title);
		        }
		        else
		        {
		            ShowGraphic.makeBarGraph(names, data, title);
		        }
		    }
		}

		return;
	}
	
	/**
	 * This writes the information to a file determined by the User
	 * @param list List Region objects are contained in	
	 * @param filename Name of file to be written
	 * @param fileType	Type of file to be written (Text or Binary)
	 * @throws IOException
	 */
	public static void fileWriter (LinkedHashMap<String,Region> list, String filename,String fileType)
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
				objectOutputStream.writeObject(Driver.getContinents()); 
				objectOutputStream.close();
		}
	}

	/**
	 * Searches for a region if the user is looking for a specific one
	 * @param list List of Region objects
	 * @param region Region name the user is interested in
	 * @param sortMethod Way the list was sorted
	 * @return 
	 */
	
	public static String searchRegion(LinkedHashMap<String,Region> list, String region,
	        String sortMethod)
	{
		
		
		if(list.containsKey(region))
		{
//			long start = System.nanoTime();
			
			String info = list.get(region).getName() + ",\n" + 
			"Area: " + list.get(region).getArea() + ",\n" +
	        "Populaion: " +list.get(region).getPop();
			
//			String info = ""; 
//			for(String key : list.keySet())
//			{
//				if(region.equalsIgnoreCase(key));
//				{
//					 info = list.get(region).getName();
//				}
//			
//			}
			
//			
//			long end = System.nanoTime();
			
//			System.out.println(end - start);
			
			JOptionPane.showMessageDialog(null, info);
			return list.get(region).toString().toLowerCase();
		}
		
		
		region = JOptionPane.showInputDialog(null,
        "Invalid Region name, please enter a valid Region:", "GeoGrapher",
        JOptionPane.QUESTION_MESSAGE);
		return searchRegion(list, region, sortMethod);
		
//		String check = null;
//		if(sortMethod.equals("Lexicographic"))
//		{	
//			int i = Collections.binarySearch(list, new Country(region, null, null, null), 
//					Region.Comparators.NAME);
//			
//			String info = list.get(i).getName() + ", " + 
//					list.get(i).getArea() + ", " +
//			        list.get(i).getPop();
//			JOptionPane.showMessageDialog(null, info);
//			return list.get(i).toString().toLowerCase();
//		}
//		
//		
//		for(int i = 0; i <list.size()-1;i++)
//			{
//			check = list.get(i).toString().toLowerCase();
//			
//			if(check.contains(region.toLowerCase()))
//				{
//					String info = check.toUpperCase() + ", " +
//					        list.get(i).getArea() + ", " +
//					        list.get(i).getPop();
//					JOptionPane.showMessageDialog(null, info);
//					return check;
//				}
//			}
//		
//		
//		region = JOptionPane.showInputDialog(null,
//		        "Invalid Region name, please enter a valid Region:", "GeoData",
//		        JOptionPane.QUESTION_MESSAGE);
//		return searchRegion(list, region, sortMethod);
	}
	
	
	/**
	 * This will determine whether to continue with the program
	 * @return true if continue, false if not
	 */
	public static boolean getContinue()
	{
        int result = JOptionPane.showConfirmDialog(null,
                "Would you like to get more information?", "GeoGrapher", 
                JOptionPane.YES_NO_OPTION); 
        
        return (result == JOptionPane.YES_OPTION);

	}
	
	
	
	
}