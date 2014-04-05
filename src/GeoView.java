import javax.swing.*;
import java.awt.*;

public class GeoView extends JFrame
{
	public GeoView() {
		setLayout(new GridLayout(1, 0));
		setMinimumSize(new Dimension(1000, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("GeoGrapher");
				
		add(new ListPanel("Continents"));
		add(new ListPanel("Countries"));
		add(new ListPanel("Cities"));
		add(new ListPanel("Places of Interest"));
		add(new ListPanel("Points of Interest"));
			
		
	}
	
}

final class ListPanel extends JPanel {
	public ListPanel(String title) {
		setLayout(new BorderLayout(0, 10));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JList<String> list = new JList<String>();
		JScrollPane listScroll = new JScrollPane(list);
		
		BtnPanel btnPanel = new BtnPanel();
		
		add(new JLabel(title, JLabel.CENTER), BorderLayout.NORTH);
		add(listScroll, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);
	}
}

final class BtnPanel extends JPanel {
	public BtnPanel() {
		setLayout(new GridLayout(2, 2, 10, 10));
		
		JButton addBtn = new JButton("Add");
		JButton editBtn = new JButton("Edit");
		JButton delBtn = new JButton("Delete");
		JButton sortBtn = new JButton("Sort");
		
		addBtn.setEnabled(false);
		editBtn.setEnabled(false);
		delBtn.setEnabled(false);
		sortBtn.setEnabled(false);
		
		add(addBtn);
		add(editBtn);
		add(delBtn);
		add(sortBtn);
		
	}
}
