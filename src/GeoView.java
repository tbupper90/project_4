import javax.swing.*;
import java.awt.*;

public class GeoView extends JFrame
{
	private ListPanel continentPanel = new ListPanel("Continents");
    private ListPanel countryPanel = new ListPanel("Countries");
    private ListPanel cityPanel = new ListPanel("Cities");
    private ListPanel placePanel = new ListPanel("Places of Interest");
    private ListPanel pointPanel = new ListPanel("Points of Interest");
	
    public GeoView() {
		setLayout(new GridLayout(1, 0));
		setMinimumSize(new Dimension(1000, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("GeoGrapher");
				
		add(continentPanel);
        add(countryPanel);
        add(cityPanel);
        add(placePanel);
        add(pointPanel);
        
        continentPanel.getAddBtn().setEnabled(true);
        
        setVisible(true);
		
	}
    
    public ListPanel getContinentPanel() {
        return continentPanel;
    }

    public ListPanel getCountryPanel() {
        return countryPanel;
    }

    public ListPanel getCityPanel() {
        return cityPanel;
    }

    public ListPanel getPlacePanel() {
        return placePanel;
    }

    public ListPanel getPointPanel() {
        return pointPanel;
    }

    class ListPanel extends JPanel {
        private JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton delBtn = new JButton("Delete");
        JButton sortBtn = new JButton("Sort");
        private JList<String> list = new JList<String>();
        
        public ListPanel(String title) {
            setLayout(new BorderLayout(0, 10));
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                   
            // Title
            add(new JLabel(title, JLabel.CENTER), BorderLayout.NORTH);

            // List
            JScrollPane listScroll = new JScrollPane(list);
            add(listScroll, BorderLayout.CENTER);

            // Buttons
            btnPanel.setLayout(new GridLayout(2, 2, 10, 10));
            
            btnPanel.add(addBtn);
            btnPanel.add(editBtn);
            btnPanel.add(delBtn);
            btnPanel.add(sortBtn);
        
            addBtn.setEnabled(false);
            editBtn.setEnabled(false);
            delBtn.setEnabled(false);
            sortBtn.setEnabled(false);
            
            add(btnPanel, BorderLayout.SOUTH);
        }

        public JButton getAddBtn() {
            return addBtn;
        }
        
        public JButton getEditBtn() {
            return editBtn;
        }
        
        public JButton getDelBtn() {
            return delBtn;
        }
        
        public JButton getSortBtn() {
            return sortBtn;
        }
        
        // Method to return JList contents
        
    }
}