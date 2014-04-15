import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.*;


public class AddEditView extends JFrame implements ActionListener {
	
	GeoModel model;
	
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
	JLabel continentJl = new JLabel("Continent");
	
	
	public AddEditView(String type) 
	{
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(400, 200));
		setLocationRelativeTo(null);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jtfPanel.setLayout(new GridLayout(4,4));
		
		jtfPanel.add(nameJl);
		jtfPanel.add(nameJtf);
		jtfPanel.add(areaJl);
		jtfPanel.add(areaJtf);
		jtfPanel.add(popJl);
		jtfPanel.add(popJtf);
		jtfPanel.add(continentJl);
		jtfPanel.add(parentRegionsJcb);
		
		
		
		btnPanel.setLayout(new GridLayout(1,2));
		btnPanel.add(addBtn);
		btnPanel.add(cancelBtn);
		
		add(btnPanel, BorderLayout.SOUTH);
		add(jtfPanel, BorderLayout.CENTER);
		
		setVisible(true);
		
		
		
	}
	
	public void setModel(GeoModel model)
	{
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
