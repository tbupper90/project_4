import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import javax.swing.*;


public class StackedChartView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1824000702248768413L;
    
    private BarsPanel barsPanel;
    
    private GeoModel model;
    
    public StackedChartView(String title, LinkedHashMap<String, Region> regions,
            String sortMethod, GeoModel model) {
        
        model = newModel;
        model.addActionListener(this);
        
        setTitle(title);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    class BarsPanel extends JPanel {
        private static final long serialVersionUID = 7413611947034290104L;
        private Color[] colors = {Color.DARK_GRAY, Color.LIGHT_GRAY};
        
        private int barHeight[];
        private int barWidth = 50;
    }

}
