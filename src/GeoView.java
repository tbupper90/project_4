import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;

public class GeoView extends JFrame implements ActionListener
{
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
    JMenuItem simpleAreaPoints = new JMenuItem("Points of Interest");
    
    JMenu simplePop = new JMenu("Population");
    JMenuItem simplePopContinents = new JMenuItem("Continents");
    JMenuItem simplePopCountries = new JMenuItem("Countries");
    JMenuItem simplePopCities = new JMenuItem("Cities");
    JMenuItem simplePopPlaces = new JMenuItem("Places of Interest");
    JMenuItem simplePopPoints = new JMenuItem("Points of Interest");
    
    JMenu stacked = new JMenu("Stacked Bar Chart");
    
    JMenu stackedArea = new JMenu("Area");
    
    JMenu stackedPop = new JMenu("Population");
    
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
        simpleArea.add(simpleAreaPoints);
		simple.add(simplePop);
        simplePop.add(simplePopContinents);
        simplePop.add(simplePopCountries);
        simplePop.add(simplePopCities);
        simplePop.add(simplePopPlaces);
        simplePop.add(simplePopPoints);
        
        graphMenu.add(stacked);
        stacked.add(stackedArea);
        stacked.add(stackedPop);
        
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
        private JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton delBtn = new JButton("Delete");
        JButton sortBtn = new JButton("Sort");
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
            btnPanel.add(sortBtn);
            
            addBtn.setActionCommand("Add " + title);
            editBtn.setActionCommand("Edit " + title);
            delBtn.setActionCommand("Delete " + title);
            sortBtn.setActionCommand("Sort " + title);
            
            addBtn.setEnabled(false);
            editBtn.setEnabled(false);
            delBtn.setEnabled(false);
            sortBtn.setEnabled(false);
            
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
            countryPanel.addBtn.setEnabled(hasEntry);
            placePanel.addBtn.setEnabled(hasEntry);
            pointPanel.addBtn.setEnabled(hasEntry);
            saveGeography.setEnabled(hasEntry);
            exportGeography.setEnabled(hasEntry);
    	}
    	
    	if (command.contains("Country")) {
    		countryPanel.refreshList(model.getCountries());
            // Enable/disable buttons based on whether there are any countries
            hasEntry = (countryPanel.list.getModel().getSize() > 0);
            countryPanel.editBtn.setEnabled(hasEntry);
            countryPanel.delBtn.setEnabled(hasEntry);
            cityPanel.addBtn.setEnabled(hasEntry);
    	}

    	if (command.contains("City")) {
    		cityPanel.refreshList(model.getCities());
            // Enable/disable buttons based on whether there are any cities
            hasEntry = (cityPanel.list.getModel().getSize() > 0);
            cityPanel.editBtn.setEnabled(hasEntry);
            cityPanel.delBtn.setEnabled(hasEntry);
    	}

    	if (command.contains("PlaceOfInterest")) {
    		placePanel.refreshList(model.getPlaces());
            // Enable/disable buttons based on whether there are any places
            hasEntry = (placePanel.list.getModel().getSize() > 0);
            placePanel.editBtn.setEnabled(hasEntry);
            placePanel.delBtn.setEnabled(hasEntry);
    	}

    	if (command.contains("PointOfInterest")) {
    		pointPanel.refreshList(model.getPoints());
            // Enable/disable buttons based on whether there are any places
            hasEntry = (pointPanel.list.getModel().getSize() > 0);
            pointPanel.editBtn.setEnabled(hasEntry);
            pointPanel.delBtn.setEnabled(hasEntry);
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