import java.awt.Dimension;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * This class is the view for the File Menu.
 * @author Raymond
 *
 */

public class FileMenu extends JFrame implements ActionListener {
	
	GeoModel model = new GeoModel();
	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -3676795658007443257L;
	
	
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("Menu");
	JMenuItem loadGeography = new JMenuItem("Load Geography");
	JMenuItem saveGeography = new JMenuItem("Save Geography");
	JMenuItem importGeography = new JMenuItem("Import Geography");
	JMenuItem exportGeography = new JMenuItem("Export Geography");
	JMenuItem exit = new JMenuItem("Exit Program");
	
	public FileMenu()
	{
		setMinimumSize(new Dimension(800, 1000));
		setLocationRelativeTo(null);
		setTitle("File Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		/**Set the menuItems to be enabled/disabled and add ActionListeners*/
		loadGeography.setEnabled(true);
		loadGeography.addActionListener(this);
		
		importGeography.setEnabled(true);
		importGeography.addActionListener(this);
		
		saveGeography.setEnabled(false);
		saveGeography.addActionListener(this);
		
		exportGeography.setEnabled(false);
		exportGeography.addActionListener(this);
		
		menu.add(loadGeography); 
		menu.add(saveGeography);
		menu.add(importGeography);
		menu.add(exportGeography);
		menu.add(exit);
	
		menuBar.add(menu);
		add(menuBar);
		
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		//Need a way to tell if data is already in the system.
		
		if (command.equals("Exit")) exitSystem();
		else if (command.equals("Load Geography")) loadGeography();
		else if (command.equals("Save Geography")) saveGeography();
		else if (command.equals("Import Geography")) importGeography();
		else if (command.equals("Export Geography")) exportGeography();
		
	}
	
	private void exitSystem()
	{
		System.exit(0);
	}
	private void loadGeography()
	{
		
	}
	private void saveGeography()
	{
		
	}
	private void importGeography()
	{
		
	}
	private void exportGeography()
	{
		
	}
}


