import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * This class displays a simple bar chart
 */
public class SimpleChartView extends JFrame implements ActionListener {
    private static final long serialVersionUID = -844411455751569515L;
    
    private BarsPanel barsPanel = new BarsPanel();
    
    private GeoModel model;

    public SimpleChartView() {
        add(barsPanel);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }
    
    public void setModel(GeoModel newModel) {
        model = newModel;
        
        if (model != null)
            model.addActionListener(this);
        
        repaint();
    }
    
    public GeoModel getModel() {
        return model;
    }
    
    class BarsPanel extends JPanel {
        private static final long serialVersionUID = -1079594597927132094L;
        
        Color[] colors = {Color.DARK_GRAY, Color.LIGHT_GRAY};
        
        public BarsPanel() {
            
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }
}
