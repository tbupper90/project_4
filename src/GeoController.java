import java.awt.event.*;

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
//			System.out.println(type);
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
					
					Continent tmpCont = model.getContinents().get(continent);
					
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
	
	private class GeoViewDeleteListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getActionCommand().contains("Continent"))
			{
				int[] i = geoView.getContinentPanel().list.getSelectedIndices();
				ListModel<String> list = geoView.getContinentPanel().list.getModel();
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
	
	
	public class GeoViewAddListener implements ActionListener
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
	
	public class GeoViewEditListener implements ActionListener
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
	
	public void setView(GeoView newView)
	{
		this.geoView = newView;
		
		ActionListener addListener = new GeoViewAddListener();
		geoView.getContinentPanel().addBtn.addActionListener(addListener);
		geoView.getCountryPanel().addBtn.addActionListener(addListener);
		geoView.getCityPanel().addBtn.addActionListener(addListener);
		geoView.getPlacePanel().addBtn.addActionListener(addListener);
		geoView.getPointPanel().addBtn.addActionListener(addListener);
		
		ActionListener deleteListener = new GeoViewDeleteListener();
		geoView.getContinentPanel().delBtn.addActionListener(deleteListener);
		geoView.getCountryPanel().delBtn.addActionListener(deleteListener);
		geoView.getCityPanel().delBtn.addActionListener(deleteListener);
		
		
		ActionListener editListener = new GeoViewEditListener();
		geoView.getContinentPanel().editBtn.addActionListener(editListener);
		geoView.getCountryPanel().editBtn.addActionListener(editListener);
		geoView.getCityPanel().editBtn.addActionListener(editListener);

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
		System.out.println(this.model);
		
	}
	
	
}

