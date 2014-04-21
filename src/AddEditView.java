import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


public class AddEditView extends JFrame implements ActionListener {
	
	/**
     * 
     */
    private static final long serialVersionUID = -1568841983837685841L;
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
	JTextField latJtf = new JTextField();
	JTextField lonJtf = new JTextField();
	JTextField elevJtf = new JTextField();
	JTextField descriptionJtf = new JTextField();

	JComboBox<String> parentRegionsJcb = new JComboBox<String>(); 	
	
	JLabel nameJl = new JLabel("Name");
	JLabel areaJl = new JLabel("Area");
	JLabel popJl = new JLabel("Population");
	JLabel locationJl = new JLabel("Location:");
	JLabel latJl = new JLabel("Latitude:");
	JLabel lonJl = new JLabel("Longitude:");
	JLabel elevJl = new JLabel("Elevation");
	JLabel descriptionJl = new JLabel("Description");
	
	JList multipleLocsList;
	
	
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
			components.add(locationJl);
			components.add(parentRegionsJcb);
			break;
//			
		case "City":
			
			for(String country : model.getCountries().keySet())
			{
				parentRegionsJcb.addItem(country);
			}
			
			components.add(nameJl);
			components.add(nameJtf);
			
			components.add(areaJl);
			components.add(areaJtf);
			
			components.add(popJl);
			components.add(popJtf);
			
			components.add(locationJl);
			components.add(parentRegionsJcb);
			
			components.add(latJl);
			components.add(latJtf);
			
			components.add(lonJl);
			components.add(lonJtf);
			
			components.add(elevJl);
			components.add(elevJtf);
			
			break;
			
		case "Place":
			ArrayList<String> locations = new ArrayList<String>();
			
			for(String continent : model.getContinents().keySet())
				locations.add(continent);
			for(String country : model.getCountries().keySet())
				locations.add(country);
			for(String city : model.getCities().keySet())
				locations.add(city);
						
			String[] locs = locations.toArray(new String[0]);
			
			multipleLocsList = new JList(locs);
			
			JScrollPane listScroll = new JScrollPane(multipleLocsList);

			
			components.add(nameJl);
			components.add(nameJtf);
			
			components.add(areaJl);
			components.add(areaJtf);
			
			components.add(descriptionJl);
			components.add(descriptionJtf);
			
			components.add(locationJl);
			components.add(listScroll);
			
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
