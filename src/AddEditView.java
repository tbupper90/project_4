import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.*;


public class AddEditView extends JFrame implements ActionListener {
	
	GeoModel model;
	String type;
	String region;
	ArrayList<Component> components = new ArrayList<Component>();
	
	Region toEdit;
	
	JPanel btnPanel = new JPanel();
	JPanel jtfPanel = new JPanel();
	JButton addBtn = new JButton("Add");
	JButton editBtn = new JButton("Edit");
	JButton cancelBtn = new JButton("Cancel");
	
	JTextField nameJtf = new JTextField();
	JTextField areaJtf = new JTextField();
	JTextField popJtf = new JTextField();
	JComboBox parentRegionsJcb = new JComboBox();
	
	
	JLabel nameJl = new JLabel("Name");
	JLabel areaJl = new JLabel("Area");
	JLabel popJl = new JLabel("Population");
	JLabel continentJl = new JLabel("Location:");
	
	
	public AddEditView(String region, String type, Region toEdit, GeoModel model) 
	{
		this.region = region;
		this.type = type;
		this.model = model;
		this.toEdit = toEdit;
		
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(400, 200));
		setLocationRelativeTo(null);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		setComponents();
		addComponents();
				
		btnPanel.setLayout(new GridLayout(1,2));
		if(type.equals("Add")) btnPanel.add(addBtn);
		if(type.equals("Edit")) btnPanel.add(editBtn);
		btnPanel.add(cancelBtn);
		
		add(btnPanel, BorderLayout.SOUTH);
		add(jtfPanel, BorderLayout.CENTER);
		
		setVisible(true);
		
		
		
	}
	
	public String getEditType()
	{
		return region;
	}
	
	public void setModel(GeoModel model)
	{
		this.model = model;
	}
	
	private void setComponents()
	{
		switch(region)
		{
		case "Continent":

			components.add(nameJl);
			components.add(nameJtf);
			components.add(areaJl);
			components.add(areaJtf);
			components.add(popJl);
			components.add(popJtf);
			break;
			
		case "Country":
			
			for(String continent : model.getContinents().keySet())
			{
				parentRegionsJcb.addItem(continent);
			}
			
			components.add(nameJl);
			components.add(nameJtf);
			components.add(areaJl);
			components.add(areaJtf);
			components.add(popJl);
			components.add(popJtf);
			components.add(continentJl);
			components.add(parentRegionsJcb);
			break;
//			
		}//end switch
	}
	
	private void addComponents()
	{
		jtfPanel.setLayout(new GridLayout(components.size() / 2, components.size() /2 ));

		for(Component component : components)
		{
			jtfPanel.add(component);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
