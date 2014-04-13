import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;

public class GeoView extends JFrame implements ActionListener
{
	private ListPanel continentPanel = new ListPanel("Continents");
    private ListPanel countryPanel = new ListPanel("Countries");
    private ListPanel cityPanel = new ListPanel("Cities");
    private ListPanel placePanel = new ListPanel("Places of Interest");
    private ListPanel pointPanel = new ListPanel("Points of Interest");
	
    private GeoModel model;
    
    public GeoView() {
		setLayout(new GridLayout(1, 0));
		setMinimumSize(new Dimension(1000, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("GeoGrapher");
				
		add(continentPanel);
        add(countryPanel);
        add(cityPanel);
        add(placePanel);
        add(pointPanel);
        
        continentPanel.addBtn.setEnabled(true);
        
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
        
            addBtn.setEnabled(false);
            editBtn.setEnabled(false);
            delBtn.setEnabled(false);
            sortBtn.setEnabled(false);
            
            add(btnPanel, BorderLayout.SOUTH);
        }
        
        public void refreshList(LinkedHashMap<String, ?> map) {
        	// Converts the key names of the hashmap to a string array
        	String[] newList = map.keySet().toArray(new String[map.size()]);
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
            hasEntry = (placePanel.list.getModel().getSize() > 0);
            pointPanel.editBtn.setEnabled(hasEntry);
            pointPanel.delBtn.setEnabled(hasEntry);
    	}


    }
    
    public void setModel(GeoModel newModel) {
    	model = newModel;
    	
    	if (model != null)
    		model.addActionListener(this);
    	
    	//repaint();
    }
    
    public GeoModel getModel() {
    	return model;
    }
}