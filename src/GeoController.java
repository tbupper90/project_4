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
					
					
			}
		}
	}
	
	private class DeleteContinentListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			int[] i = geoView.getContinentPanel().list.getSelectedIndices();
			ListModel<String> list = geoView.getContinentPanel().list.getModel();
			for(int index : i)
			{
				String name = list.getElementAt(index);
				model.removeRegion(model.getContinents().get(name));
			}

		}
		
	}
	
	
	public class AddContinentListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			if(model == null) return;
			
			aeView = new AddEditView("Continent", "Add", null, model);
			
			setAddEditView(aeView);
			
			
		}
		
	}
	
	public class EditContinentListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			if(model == null) return;
			
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
				
			}
			

		}
		
	}
	
	public void setView(GeoView newView)
	{
		this.geoView = newView;
		
		ActionListener addContinentListener = new AddContinentListener();
		geoView.getContinentPanel().addBtn.addActionListener(addContinentListener);
		
		ActionListener deleteContinentListener = new DeleteContinentListener();
		geoView.getContinentPanel().delBtn.addActionListener(deleteContinentListener);
		
		ActionListener editContinentListener = new EditContinentListener();
		geoView.getContinentPanel().editBtn.addActionListener(editContinentListener);
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

