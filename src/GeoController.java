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
	
	private class AddListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(model == null) return;
			
			String name = aeView.nameJtf.getText();
			String area = aeView.areaJtf.getText();
			String pop = aeView.popJtf.getText();
			
			model.addRegion(new Continent(name,pop,area));
			
			aeView.setVisible(false);
		
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
			
			aeView = new AddEditView("Continent", model);
			
			setAddEditView(aeView);
			
			
		}
		
	}
	
	public void setView(GeoView newView)
	{
		this.geoView = newView;
		
		ActionListener addContinentListener = new AddContinentListener();
		geoView.getContinentPanel().addBtn.addActionListener(addContinentListener);
		
		ActionListener deleteContinentListener = new DeleteContinentListener();
		geoView.getContinentPanel().delBtn.addActionListener(deleteContinentListener);
	}
	
	public void setAddEditView(AddEditView  newView)
	{
		aeView = newView;
		aeView.setModel(model);
		
		ActionListener addListener = new AddListener();
		aeView.addBtn.addActionListener(addListener);
		
	}

	public void setModel(GeoModel model) 
	{
		this.model = model;
		System.out.println(this.model);
		
	}
	
	
}

