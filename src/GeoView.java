import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;

public class GeoView extends JFrame implements ActionListener
{
    private static final long serialVersionUID = -8738230123623659374L;

    private GeoModel model;

    /**This is the panels for the things*/
	private ListPanel continentPanel = new ListPanel("Continents");
    private ListPanel countryPanel = new ListPanel("Countries");
    private ListPanel cityPanel = new ListPanel("Cities");
    private ListPanel placePanel = new ListPanel("Places of Interest");
    private ListPanel pointPanel = new ListPanel("Points of Interest");
    
    JMenuBar menuBar = new JMenuBar();
    /**File menu*/
    JMenu fileMenu = new JMenu("File");
    JMenuItem loadGeography = new JMenuItem("Load Geography");
    JMenuItem saveGeography = new JMenuItem("Save Geography");
    JMenuItem importGeography = new JMenuItem("Import Geography");
    JMenuItem exportGeography = new JMenuItem("Export Geography");
    JMenuItem exit = new JMenuItem("Exit Program");
    /**Graph menu*/
    JMenu graphMenu = new JMenu("Graph");
    
    JMenu simple = new JMenu("Simple Bar Chart");
    
    JMenu simpleArea = new JMenu("Area");
    JMenuItem simpleAreaContinents = new JMenuItem("Continents");
    JMenuItem simpleAreaCountries = new JMenuItem("Countries");
    JMenuItem simpleAreaCities = new JMenuItem("Cities");
    JMenuItem simpleAreaPlaces = new JMenuItem("Places of Interest");
    
    JMenu simplePop = new JMenu("Population");
    JMenuItem simplePopContinents = new JMenuItem("Continents");
    JMenuItem simplePopCountries = new JMenuItem("Countries");
    JMenuItem simplePopCities = new JMenuItem("Cities");
    
    JMenu stacked = new JMenu("Stacked Bar Chart");
    
    JMenu stackedArea = new JMenu("Area");
    JMenuItem stackedAreaCountriesInContinents = new JMenuItem("Countries Within Continents");
    JMenuItem stackedAreaCitiesInCountries = new JMenuItem("Cities Within Countries");
    JMenuItem stackedAreaPlacesInContinents = new JMenuItem("Places of Interest Within Continents");
    JMenuItem stackedAreaPlacesInCountries = new JMenuItem("Places of Interest Within Countries");
    JMenuItem stackedAreaPlacesInCities = new JMenuItem("Places of Interest Within Cities");

    JMenu stackedPop = new JMenu("Population");
    JMenuItem stackedPopCountriesInContinents = new JMenuItem("Countries Within Continents");
    JMenuItem stackedPopCitiesInCountries = new JMenuItem("Cities Within Countries");
    
    JMenuItem map = new JMenuItem("Map");
    
    public GeoView() {
    	JPanel panel = new JPanel(new GridLayout(1, 0));
		setMinimumSize(new Dimension(1000, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("GeoGrapher");
				
		panel.add(continentPanel);
		panel.add(countryPanel);
		panel.add(cityPanel);
		panel.add(placePanel);
		panel.add(pointPanel);
        
        continentPanel.addBtn.setEnabled(true);
        
        /**Set the menuItems to be enabled/disabled and add ActionListeners*/
		loadGeography.setEnabled(true);
		
		importGeography.setEnabled(true);
		
		saveGeography.setEnabled(false);
		
		exportGeography.setEnabled(false);
		
		fileMenu.add(loadGeography); 
		fileMenu.add(saveGeography);
		fileMenu.add(importGeography);
		fileMenu.add(exportGeography);
		fileMenu.add(exit);
	
		menuBar.add(fileMenu);
		
		graphMenu.add(simple);

		simple.add(simpleArea);

		simpleArea.add(simpleAreaContinents);
        simpleArea.add(simpleAreaCountries);
        simpleArea.add(simpleAreaCities);
        simpleArea.add(simpleAreaPlaces);

        simple.add(simplePop);

        simplePop.add(simplePopContinents);
        simplePop.add(simplePopCountries);
        simplePop.add(simplePopCities);
        
        graphMenu.add(stacked);

        stacked.add(stackedArea);

        stackedArea.add(stackedAreaCountriesInContinents);
        stackedArea.add(stackedAreaCitiesInCountries);
        stackedArea.add(stackedAreaPlacesInContinents);
        stackedArea.add(stackedAreaPlacesInCountries);
        stackedArea.add(stackedAreaPlacesInCities);

        stacked.add(stackedPop);

        stackedPop.add(stackedPopCountriesInContinents);
        stackedPop.add(stackedPopCitiesInCountries);

        simpleAreaContinents.setActionCommand("Areas of All Continents");
        simpleAreaCountries.setActionCommand("Areas of All Countries");
        simpleAreaCities.setActionCommand("Areas of All Cities");
        simpleAreaPlaces.setActionCommand("Areas of All Places of Interest");

        simplePopContinents.setActionCommand("Populations of All Continents");
        simplePopCountries.setActionCommand("Populations of All Countries");
        simplePopCities.setActionCommand("Populations of All Cities");

        stackedAreaCountriesInContinents.setActionCommand("Areas of All Countries Within Continents");
        stackedAreaCitiesInCountries.setActionCommand("Areas of All Cities Within Countries");
        stackedAreaPlacesInContinents.setActionCommand("Areas of All Places Within Continents");
        stackedAreaPlacesInCountries.setActionCommand("Areas of All Places Within Countries");
        stackedAreaPlacesInCities.setActionCommand("Areas of All Places Within Cities");
        
        stackedPopCountriesInContinents.setActionCommand("Populations of All Countries Within Continents");
        stackedPopCitiesInCountries.setActionCommand("Populations of All Cities Within Countries");
        
        graphMenu.add(map);

        menuBar.add(graphMenu);

        add(menuBar, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		
		
        
        setVisible(true);
        
        
		
	}
    
    public ListPanel getContinentPanel() {
        return continentPanel;
    }

    public ListPanel getCountryPanel() {
        return countryPanel;
    }

    public ListPanel getCityPanel() {
        return cityPanel;
    }

    public ListPanel getPlacePanel() {
        return placePanel;
    }

    public ListPanel getPointPanel() {
        return pointPanel;
    }
    

    class ListPanel extends JPanel {
        private static final long serialVersionUID = 6675385658735931034L;
        
        private JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton delBtn = new JButton("Delete");
        JButton viewBtn = new JButton("View");
        JList<String> list = new JList<String>();
        
        public ListPanel(String title) {
            setLayout(new BorderLayout(0, 10));
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                   
            // Title
            add(new JLabel(title, JLabel.CENTER), BorderLayout.NORTH);

            // List
            JScrollPane listScroll = new JScrollPane(list);
            add(listScroll, BorderLayout.CENTER);

            // Buttons
            btnPanel.setLayout(new GridLayout(2, 2, 10, 10));
            
            btnPanel.add(addBtn);
            btnPanel.add(editBtn);
            btnPanel.add(delBtn);
            btnPanel.add(viewBtn);
            
            addBtn.setActionCommand("Add " + title);
            editBtn.setActionCommand("Edit " + title);
            delBtn.setActionCommand("Delete " + title);
            viewBtn.setActionCommand("View " + title);
            
            addBtn.setEnabled(false);
            editBtn.setEnabled(false);
            delBtn.setEnabled(false);
            viewBtn.setEnabled(false);
            
            add(btnPanel, BorderLayout.SOUTH);
        }
        
        public void refreshList(LinkedHashMap<String, ?> map) {
        	// Converts the key names of the hashmap to a string array
        	
        	String[] newList = map.keySet().toArray(new String[0]);
        	
//        	String[] newList = new String[map.size()];
//        	
//        	LinkedHashMap<String, Region> newMap = (LinkedHashMap<String, Region>) map;
//        	
//        	int i=0;
//        	for(String region : newMap.keySet())
//        	{
//        		newList[i] = newMap.get(region).name;
//        		System.out.println(newMap.get(region).name);
//        		i++;
//        	}
        	
        	list.setListData(newList);
        	
        	
        	
        	
        }
        
    }
    
    public void actionPerformed(ActionEvent e) {
    	String command = e.getActionCommand();
    	boolean hasEntry;
    	
    	if (command.contains("Continent")) {
    		continentPanel.refreshList(model.getContinents());   		
            // Enable/disable buttons based on whether there are any continents
            hasEntry = (continentPanel.list.getModel().getSize() > 0);
            continentPanel.editBtn.setEnabled(hasEntry);
            continentPanel.delBtn.setEnabled(hasEntry);
            continentPanel.viewBtn.setEnabled(hasEntry);
            countryPanel.addBtn.setEnabled(hasEntry);
            placePanel.addBtn.setEnabled(hasEntry);
            pointPanel.addBtn.setEnabled(hasEntry);
            saveGeography.setEnabled(hasEntry);
            exportGeography.setEnabled(hasEntry);
            // Enable/disable menu items
            simpleAreaContinents.setEnabled(hasEntry);
            simplePopContinents.setEnabled(hasEntry);
    	}
    	
    	if (command.contains("Country")) {
    		countryPanel.refreshList(model.getCountries());
            // Enable/disable buttons based on whether there are any countries
            hasEntry = (countryPanel.list.getModel().getSize() > 0);
            countryPanel.editBtn.setEnabled(hasEntry);
            countryPanel.delBtn.setEnabled(hasEntry);
            countryPanel.viewBtn.setEnabled(hasEntry);
            cityPanel.addBtn.setEnabled(hasEntry);
            // Enable/disable menu items
            simpleAreaCountries.setEnabled(hasEntry);
            simplePopCountries.setEnabled(hasEntry);
            stackedAreaCountriesInContinents.setEnabled(hasEntry);
            stackedPopCountriesInContinents.setEnabled(hasEntry);
    	}

    	if (command.contains("City")) {
    		cityPanel.refreshList(model.getCities());
            // Enable/disable buttons based on whether there are any cities
            hasEntry = (cityPanel.list.getModel().getSize() > 0);
            cityPanel.editBtn.setEnabled(hasEntry);
            cityPanel.delBtn.setEnabled(hasEntry);
            cityPanel.viewBtn.setEnabled(hasEntry);
            // Enable/disable menu items
            simpleAreaCities.setEnabled(hasEntry);
            simplePopCities.setEnabled(hasEntry);
            stackedAreaCitiesInCountries.setEnabled(hasEntry);
            stackedPopCitiesInCountries.setEnabled(hasEntry);
    	}

    	if (command.contains("PlaceOfInterest")) {
    		placePanel.refreshList(model.getPlaces());
            // Enable/disable buttons based on whether there are any places
            hasEntry = (placePanel.list.getModel().getSize() > 0);
            placePanel.editBtn.setEnabled(hasEntry);
            placePanel.delBtn.setEnabled(hasEntry);
            placePanel.viewBtn.setEnabled(hasEntry);
            // Enable/disable menu items
            simpleAreaPlaces.setEnabled(hasEntry);
            stackedAreaPlacesInContinents.setEnabled(hasEntry);
            stackedAreaPlacesInCountries.setEnabled(hasEntry);
            stackedAreaPlacesInCities.setEnabled(hasEntry);
    	}

    	if (command.contains("PointOfInterest")) {
    		pointPanel.refreshList(model.getPoints());
            // Enable/disable buttons based on whether there are any places
            hasEntry = (pointPanel.list.getModel().getSize() > 0);
            pointPanel.editBtn.setEnabled(hasEntry);
            pointPanel.delBtn.setEnabled(hasEntry);
            pointPanel.viewBtn.setEnabled(hasEntry);
    	}
    	

    }
    
    public void setModel(GeoModel newModel) {
    	model = newModel;
    	
    	if (model != null)
    		model.addActionListener(this);
    	
    }
    
    public GeoModel getModel() {
    	return model;
    }
}