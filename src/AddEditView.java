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
	ArrayList<Component> components = new ArrayList<Component>();
	
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
	
	
	public AddEditView(String type, GeoModel model) 
	{
		this.type = type;
		this.model = model;
		
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(400, 200));
		setLocationRelativeTo(null);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		
		jtfPanel.setLayout(new GridLayout(4,4));
		setComponents(type);
		addComponents(jtfPanel);
		
		
//		jtfPanel.add(nameJl);
//		jtfPanel.add(nameJtf);
//		jtfPanel.add(areaJl);
//		jtfPanel.add(areaJtf);
//		jtfPanel.add(popJl);
//		jtfPanel.add(popJtf);
//		jtfPanel.add(continentJl);
//		jtfPanel.add(parentRegionsJcb);
		
		
		
		btnPanel.setLayout(new GridLayout(1,2));
		btnPanel.add(addBtn);
		btnPanel.add(cancelBtn);
		
		add(btnPanel, BorderLayout.SOUTH);
		add(jtfPanel, BorderLayout.CENTER);
		
		setVisible(true);
		
		
		
	}
	
	public String getEditType()
	{
		return type;
	}
	
	public void setModel(GeoModel model)
	{
		this.model = model;
	}
	
	private void setComponents(String type)
	{
		switch(type)
		{
		case "Continent":
			
		
//			for(String continent : model.getContinents().keySet())
//			{
//				parentRegionsJcb.addItem(continent);
//			}
			
			components.add(nameJl);
			components.add(nameJtf);
			components.add(areaJl);
			components.add(areaJtf);
			components.add(popJl);
			components.add(popJtf);
//			components.add(continentJl);
//			components.add(parentRegionsJcb);
		}
	}
	
	private void addComponents(JPanel panel)
	{
		for(Component component : components)
		{
			panel.add(component);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
