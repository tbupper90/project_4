import java.awt.event.*;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;
import javax.swing.ListModel;


public class GeoController 
{
	GeoModel model;
	GeoView geoView;
	AddEditView aeView; 
	
	public GeoController()
	{
			
	}
	
	private class CancelListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			aeView.setVisible(false);
		}
		
	}
	
	private class AddListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(model == null) return;
			
			String region = aeView.getEditType();
//			System.out.println(region);
			switch(region)
			{
				case "Continent": 
					String name = aeView.nameJtf.getText();
					String area = aeView.areaJtf.getText();
					String pop = aeView.popJtf.getText();
				
					model.addRegion(new Continent(name,pop,area));
				
					aeView.setVisible(false);
					break;
					
				case "Country":
					String countryName = aeView.nameJtf.getText();
					String countryArea = aeView.areaJtf.getText();
					String countryPop = aeView.popJtf.getText();
					String continent = aeView.parentRegionsJcb.getSelectedItem().toString();
					
//					System.out.println(continent);
					
					Continent tmpCont = model.getContinents().get(continent);
					
//					System.out.println(tmpCont);
					
					model.addRegion(new Country(countryName,countryPop,countryArea,tmpCont));
					
					aeView.setVisible(false);
					break;
					
				case "City":
					String cityName = aeView.nameJtf.getText();
					String cityArea = aeView.areaJtf.getText();
					String cityPop = aeView.popJtf.getText();
					String cityLat = aeView.latJtf.getText();
					String cityLon = aeView.lonJtf.getText();
					String cityElev = aeView.lonJtf.getText();
					String country = aeView.parentRegionsJcb.getSelectedItem().toString();
					
					Country tmpCount = model.getCountries().get(country);
					if(cityLat.isEmpty() && cityLon.isEmpty() && cityElev.isEmpty())
						model.addRegion(new City(cityName,cityPop,cityArea,tmpCount));
					
					else
						model.addRegion(new City(cityName,cityPop,cityArea,tmpCount,cityLat,cityLon,cityElev));
					
					aeView.setVisible(false);
					break;
					
			}//end switch
		}//end actionPerformed
	}//end class
	
	private class EditListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(model == null) return;
			
			String region = aeView.getEditType();
			switch(region)
			{
				case "Continent": 
					String name = aeView.nameJtf.getText();
					String area = aeView.areaJtf.getText();
					String pop = aeView.popJtf.getText();
					
					aeView.toEdit.name = name;
					aeView.toEdit.area = area;
					aeView.toEdit.pop = pop;
					Continent tmpContinent = (Continent) aeView.toEdit;
//					model.addRegion(tmpContinent);
//					model.removeRegion(aeView.toEdit);
					
					model.regionEdited();
					
					aeView.setVisible(false);
					break;
					
				case "Country":
					String countryName = aeView.nameJtf.getText();
					String countryArea = aeView.areaJtf.getText();
					String countryPop = aeView.popJtf.getText();
					String continent = aeView.parentRegionsJcb.getSelectedItem().toString();
					
				
					model.getCountries().get(aeView.toEdit.name).continent = model.getContinents().get(continent);
					aeView.toEdit.name = countryName;
					aeView.toEdit.area = countryArea;
					aeView.toEdit.pop = countryPop;
					
					model.regionEdited();
					
					aeView.setVisible(false);
					break;
					
				case "City":
					String cityName = aeView.nameJtf.getText();
					String cityArea = aeView.areaJtf.getText();
					String cityPop = aeView.popJtf.getText();
					String country = aeView.parentRegionsJcb.getSelectedItem().toString();
					
				
					model.getCities().get(aeView.toEdit.name).country = model.getCountries().get(country);
					aeView.toEdit.name = cityName;
					aeView.toEdit.area = cityArea;
					aeView.toEdit.pop = cityPop;
					
					model.regionEdited();
					
					aeView.setVisible(false);
					break;
					
					
					
			}
		}
	}
	
	private class DeleteButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getActionCommand().contains("Continent"))
			{
				int[] i = geoView.getContinentPanel().list.getSelectedIndices();
				ListModel<String> list = geoView.getContinentPanel().list.getModel();
				
				System.out.println(list);
				
				for(int index : i)
				{
					String name = list.getElementAt(index);
					model.removeRegion(model.getContinents().get(name));
				}
			}//end if
			
			if(e.getActionCommand().contains("Countries"))
			{
				int[] i = geoView.getCountryPanel().list.getSelectedIndices();
				ListModel<String> list = geoView.getCountryPanel().list.getModel();
				
//				for(int index : i)
//				{
//					System.out.println(index);
//					System.out.println(list.getElementAt(index));
//				}
				
				for(int index : i)
				{
					String name = list.getElementAt(index);
					model.removeRegion(model.getCountries().get(name));
				}//end for
			}//end if
			
			if(e.getActionCommand().contains("Cities"))
			{
				int[] i = geoView.getCityPanel().list.getSelectedIndices();
				ListModel<String> list = geoView.getCityPanel().list.getModel();
				for(int index : i)
				{
					String name = list.getElementAt(index);
					model.removeRegion(model.getCities().get(name));
				}//end for
				
			}
		}
		
	}
	
	
	public class AddButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			if(model == null) return;
			
			if(e.getActionCommand().contains("Continent"))
			{
				aeView = new AddEditView("Continent", "Add", null, model);
			}
			
			if(e.getActionCommand().contains("Countries"))
			{
				aeView = new AddEditView("Country", "Add", null, model);
			}
			
			if(e.getActionCommand().contains("Cities"))
			{
				aeView = new AddEditView("City", "Add", null, model);
			}
			
			setAddEditView(aeView);
			
			
		}
		
	}
	
	public class EditButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(model == null) return;
			
			if(e.getActionCommand().contains("Continent"))
			{
				int[] i = geoView.getContinentPanel().list.getSelectedIndices();
				ListModel<String> list = geoView.getContinentPanel().list.getModel();
				
				for(int index : i)
				{
					Continent tmpCont = model.getContinents().get(list.getElementAt(index));
					aeView = new AddEditView("Continent", "Edit", tmpCont, model);
					setAddEditView(aeView);
					aeView.nameJtf.setText(tmpCont.name);
					aeView.areaJtf.setText(tmpCont.area);
					aeView.popJtf.setText(tmpCont.pop);
				}//for
			}//end if
			
			if(e.getActionCommand().contains("Countries"))
			{
				int[] i = geoView.getCountryPanel().list.getSelectedIndices();
				ListModel<String> list = geoView.getCountryPanel().list.getModel();
				
				for(int index : i)
				{
					Country tmpCount = model.getCountries().get(list.getElementAt(index));
					aeView = new AddEditView("Country", "Edit", tmpCount, model);
					setAddEditView(aeView);
					aeView.nameJtf.setText(tmpCount.name);
					aeView.areaJtf.setText(tmpCount.area);
					aeView.popJtf.setText(tmpCount.pop);
					aeView.parentRegionsJcb.setSelectedItem(tmpCount.getContinent().toString());
				}//end for
				
			}//end if
			
			if(e.getActionCommand().contains("Cities"))
			{
				int[] i = geoView.getCityPanel().list.getSelectedIndices();
				ListModel<String> list = geoView.getCityPanel().list.getModel();
				
				for(int index : i)
				{
					City tmpCity = model.getCities().get(list.getElementAt(index));
					aeView = new AddEditView("Country", "Edit", tmpCity, model);
					setAddEditView(aeView);
					aeView.nameJtf.setText(tmpCity.name);
					aeView.areaJtf.setText(tmpCity.area);
					aeView.popJtf.setText(tmpCity.pop);
					aeView.parentRegionsJcb.setSelectedItem(tmpCity.getCountry().toString());
				
				}//end for
			}
			
			
		}
		
	}//end Class
	
	/*
	private class ImportViewButtonListener implements ActionListener
	{
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        String[] file = IMPORTVIEW.importFile();
	        if (file != null)
	            model.readTextFile(file[0], file[1]);
	    }
	}
	*/
	
	private class ImportListener implements ActionListener
	{
		String fileName;
		String[] options;
		int choice;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (model==null)
			{
				return;
			}
			if (!(model.getContinents().isEmpty() && model.getContinents().isEmpty() && model.getContinents().isEmpty() 
					&& model.getContinents().isEmpty() && model.getContinents().isEmpty())) //There is unsaved data in the system
			{
				options = new String[] {"Export", "Save", "Discard"};
				choice = JOptionPane.showOptionDialog(null, "Would you like to export, save, or discard the current unsaved data?", "Save, export, or discard", 1, 1, 
						null, options, "Discard");
				if (choice == 0) //Export
				{
					model.exportGeography();
				}
				else if (choice == 1) //Save
				{
					model.saveGeography();
				}
				else if (choice == 2)
				{
					model.discardGeography();
				}
			}
			model.importGeography();
		}
		
		
	}
	
	private class ExportListener implements ActionListener
	{
		String fileName;
		String[] options;
		int choice;
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (model==null)
			{
				return;
			}
			if (!(model.getContinents().isEmpty() && model.getContinents().isEmpty() && model.getContinents().isEmpty() 
					&& model.getContinents().isEmpty() && model.getContinents().isEmpty())) //There is unsaved data in the system
			{
				options = new String[] {"Export", "Save", "Discard"};
				choice = JOptionPane.showOptionDialog(null, "Would you like to export, save, or discard the current unsaved data?", "Save, export, or discard", 1, 1, 
						null, options, "Discard");
				if (choice == 0) //Export
				{
					model.exportGeography();
				}
				else if (choice == 1) //Save
				{
					model.saveGeography();
				}
				else if (choice == 2)
				{
					model.discardGeography();
				}
				
			
			}
				model.exportGeography();
		}
	}
		
	
	private class LoadListener implements ActionListener
	{

		String fileName;
		String[] options;
		int choice;
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (model==null)
			{
				return;
			}
			if (!(model.getContinents().isEmpty() && model.getContinents().isEmpty() && model.getContinents().isEmpty() 
					&& model.getContinents().isEmpty() && model.getContinents().isEmpty())) //There is unsaved data in the system
			{
				options = new String[] {"Export", "Save", "Discard"};
				choice = JOptionPane.showOptionDialog(null, "Would you like to export, save, or discard the current unsaved data?", "Save, export, or discard", 1, 1, 
						null, options, "Discard");
				if (choice== 0) //Export
				{
					model.exportGeography();
				}
				else if (choice == 1) //Save
				{
					model.saveGeography();
				}
				else if (choice == 2)
				{
					model.discardGeography();
				}
				
			}
			
				model.loadGeography();
			
	}
	}
	
	private class SaveListener implements ActionListener
	{

		String[] options;
		int choice;
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (model==null)
			{
				return;
			}
			if (!(model.getContinents().isEmpty() && model.getContinents().isEmpty() && model.getContinents().isEmpty() 
					&& model.getContinents().isEmpty() && model.getContinents().isEmpty())) //There is unsaved data in the system
			{
				options = new String[] {"Export", "Save", "Discard"};
				choice = JOptionPane.showOptionDialog(null, "Would you like to export, save, or discard the current unsaved data?", "Save, export, or discard", 1, 1, 
						null, options, "Discard");
				if (choice == 0) //Export
				{
					model.exportGeography();
				}
				else if (choice == 1) //Save
				{
					model.saveGeography();
				}
				else if (choice == 2)
				{
					model.discardGeography();
				}
				
			}
			model.saveGeography();
		}
	}
	
	private class ExitListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
		
	}
	
	private class SimpleChartListener implements ActionListener
	{
        @Override
	    public void actionPerformed(ActionEvent e) {
            // Declaring map this way, then casting it only when it's passed
	        // was the only way to get this idea to work
	        LinkedHashMap<String, ? extends Region> regions = null;
	        
            // The whole command string will get passed as the title
            String command = e.getActionCommand();
	        // The first part of the split will get passed as the data type,
            // while the second part determines the regions to get from model
            String[] split = command.split("s ", 2);
	        
            switch (split[1]) {
	        case "of All Continents":
	            regions = model.getContinents();
	            break;
            case "of All Countries":
                regions = model.getCountries();
                break;
            case "of All Cities":
                regions = model.getCities();
                break;
            case "of All Places of Interest":
                regions = model.getPlaces();
                break;
	        }
            
            new SimpleChartView(command, (LinkedHashMap<String, Region>)regions,
                    split[0], model);
	    }
	}
	
	private class StackedChartListener implements ActionListener
	{
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        LinkedHashMap<String, ? extends Region> regions = null;
	        
	        String command = e.getActionCommand();
	        String[] split = command.split("s ", 2);
	        String parent;
	        
	        int[] i;
	        ListModel<String> list;
	        
	        switch (split[1]) {
	        case "of All Countries Within Continents":

	            i = geoView.getContinentPanel().list.getSelectedIndices();
                list = geoView.getContinentPanel().list.getModel();
                                
                for(int index : i)
                {
                    parent = list.getElementAt(index);
                    regions = model.getContinents().get(parent).countries;
                    command = command.replace("Continents", "");
                    new StackedChartView(command + parent,
                            (LinkedHashMap<String, Region>)regions, split[0], model);
                }
	            break;
	            
            case "of All Cities Within Countries":

                i = geoView.getCountryPanel().list.getSelectedIndices();
                list = geoView.getCountryPanel().list.getModel();
                                
                for(int index : i)
                {
                    parent = list.getElementAt(index);
                    regions = model.getCountries().get(parent).cities;
                    command = command.replace("Countries", "");
                    new StackedChartView(command + parent,
                            (LinkedHashMap<String, Region>)regions, split[0], model);
                }
                break;

            case "of All Places Within Continents":

                i = geoView.getContinentPanel().list.getSelectedIndices();
                list = geoView.getContinentPanel().list.getModel();
                                
                for(int index : i)
                {
                    parent = list.getElementAt(index);
                    regions = model.getContinents().get(parent).places;
                    command = command.replace("Continents", "");
                    new StackedChartView(command + parent,
                            (LinkedHashMap<String, Region>)regions, split[0], model);
                }
                break;
                
            case "of All Places Within Countries":

                i = geoView.getCountryPanel().list.getSelectedIndices();
                list = geoView.getCountryPanel().list.getModel();
                                
                for(int index : i)
                {
                    parent = list.getElementAt(index);
                    regions = model.getCountries().get(parent).places;
                    command = command.replace("Countries", "");
                    new StackedChartView(command + parent,
                            (LinkedHashMap<String, Region>)regions, split[0], model);
                }
                break;
                
            case "of All Places Within Cities":

                i = geoView.getCityPanel().list.getSelectedIndices();
                list = geoView.getCityPanel().list.getModel();
                                
                for(int index : i)
                {
                    parent = list.getElementAt(index);
                    regions = model.getCities().get(parent).places;
                    command = command.replace("Cities", "");
                    new StackedChartView(command + parent,
                            (LinkedHashMap<String, Region>)regions, split[0], model);
                }
                break;
	        }
	        
	    }
	}
	
	private class MapListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
            LinkedHashMap<String, ? extends Region> regions = null;
	        
	        String command = e.getActionCommand();
	        // Good thing all the action commands have a W after their region type
	        String[] split = command.split(" W", 2);
	        String parent;

	        int[] i;
            ListModel<String> list;
	        
            if (split[0].equals("Cities")) {
                switch(split[1]) {
                case "orldwide":
                    regions = model.getCities();
                    new MapView(command, (LinkedHashMap<String, Region>)regions,
                            split[0], model);
                    break;
                    
                case "ithin Continents":
                    break;
                    
                case "ithin Countries":
                    i = geoView.getCountryPanel().list.getSelectedIndices();
                    list = geoView.getCountryPanel().list.getModel();
                                    
                    for(int index : i)
                    {
                        parent = list.getElementAt(index);
                        regions = model.getCountries().get(parent).cities;
                        command = command.replace("Countries", "");
                        new MapView(command + parent,
                                (LinkedHashMap<String, Region>)regions, split[0], model);
                    }
                    break;
                    
                }
            } else if (split[0].equals("Points of Interest")) {
                switch(split[1]) {
                case "orldwide":
                    regions = model.getPoints();
                    new MapView(command, (LinkedHashMap<String, Region>)regions,
                            split[0], model);
                    break;
                    
                case "ithin Continents":
                    i = geoView.getContinentPanel().list.getSelectedIndices();
                    list = geoView.getContinentPanel().list.getModel();
                    break;
                    
                case "ithin Countries":
                    break;
                    
                case "ithin Cities":
                    break;
                }
                
            }
            
	            
	        
	    }
	}

	public void setView(GeoView newView)
	{
		this.geoView = newView;
		
		ActionListener addListener = new AddButtonListener();
		geoView.getContinentPanel().addBtn.addActionListener(addListener);
		geoView.getCountryPanel().addBtn.addActionListener(addListener);
		geoView.getCityPanel().addBtn.addActionListener(addListener);
		geoView.getPlacePanel().addBtn.addActionListener(addListener);
		geoView.getPointPanel().addBtn.addActionListener(addListener);
		
		ActionListener deleteListener = new DeleteButtonListener();
		geoView.getContinentPanel().delBtn.addActionListener(deleteListener);
		geoView.getCountryPanel().delBtn.addActionListener(deleteListener);
		geoView.getCityPanel().delBtn.addActionListener(deleteListener);
		
		
		ActionListener editListener = new EditButtonListener();
		geoView.getContinentPanel().editBtn.addActionListener(editListener);
		geoView.getCountryPanel().editBtn.addActionListener(editListener);
		geoView.getCityPanel().editBtn.addActionListener(editListener);

		/**Action Listeners for the menu items*/
		
		geoView.loadGeography.addActionListener(new LoadListener());
		
		geoView.importGeography.addActionListener(new ImportListener());
		
		geoView.exportGeography.addActionListener(new ExportListener());
		
		geoView.saveGeography.addActionListener(new SaveListener());
		
		geoView.exit.addActionListener(new ExitListener());

		
		
		/** Action Listeners for Graph menu items */
		ActionListener simpleListener = new SimpleChartListener();
		geoView.simpleAreaContinents.addActionListener(simpleListener);
		geoView.simpleAreaCountries.addActionListener(simpleListener);
        geoView.simpleAreaCities.addActionListener(simpleListener);
        geoView.simpleAreaPlaces.addActionListener(simpleListener);
        geoView.simplePopContinents.addActionListener(simpleListener);
        geoView.simplePopCountries.addActionListener(simpleListener);
        geoView.simplePopCities.addActionListener(simpleListener);

        ActionListener stackedListener = new StackedChartListener();
        geoView.stackedAreaCountriesInContinents.addActionListener(stackedListener);
        geoView.stackedAreaCitiesInCountries.addActionListener(stackedListener);
        geoView.stackedAreaPlacesInContinents.addActionListener(stackedListener);
        geoView.stackedAreaPlacesInCountries.addActionListener(stackedListener);
        geoView.stackedAreaPlacesInCities.addActionListener(stackedListener);
        geoView.stackedPopCountriesInContinents.addActionListener(stackedListener);
        geoView.stackedPopCitiesInCountries.addActionListener(stackedListener);
        
        ActionListener mapListener = new MapListener();
        geoView.mapAllCities.addActionListener(mapListener);
        geoView.mapCitiesInContinents.addActionListener(mapListener);
        geoView.mapCitiesInCountries.addActionListener(mapListener);
        geoView.mapAllPoints.addActionListener(mapListener);
        geoView.mapPointsInContinents.addActionListener(mapListener);
        geoView.mapPointsInCountries.addActionListener(mapListener);
        geoView.mapPointsInCities.addActionListener(mapListener);

	}
	
	public void setAddEditView(AddEditView newView)
	{
		aeView = newView;
		aeView.setModel(model);
		
		ActionListener cancelListener = new CancelListener();
		aeView.cancelBtn.addActionListener(cancelListener);
		
		ActionListener addListener = new AddListener();
		aeView.addBtn.addActionListener(addListener);
		
		ActionListener editListener = new EditListener();
		aeView.editBtn.addActionListener(editListener);
		
		
	}

	public void setModel(GeoModel model) 
	{
		this.model = model;
	}
	
	
	
	
}


