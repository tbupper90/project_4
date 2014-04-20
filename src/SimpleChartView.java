import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * This class displays a simple bar chart
 */
public class SimpleChartView extends JFrame implements ActionListener {
    private static final long serialVersionUID = -844411455751569515L;
    
    private BarsPanel barsPanel;
    private JScrollPane barsScroll = new JScrollPane(barsPanel);
    
    private GeoModel model;

    public SimpleChartView(LinkedHashMap<String, Region> regions, String sortMethod) {
        barsPanel = new BarsPanel(regions, sortMethod);
        add(barsScroll);
        
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
        private Color[] colors = {Color.DARK_GRAY, Color.LIGHT_GRAY};
        
        private int barHeight[];
        private int barWidth = 20;
        
        private LinkedHashMap<String, Region> regions;
        private LinkedHashMap<String, Region> regionsCopy;
        private String[] names;
        private long[] data;
        
        private String sortMethod;
        
        public BarsPanel(LinkedHashMap<String, Region> regions, String sortMethod) {
            this.regions = regions;
            this.sortMethod = sortMethod;
        }
        
        private void refreshData() {
            // Create/refresh a shallow copy that can be sorted without
            // affecting the original
            regionsCopy = new LinkedHashMap<String, Region>(regions);
            regionsCopy = sort.performSort(
                    (LinkedHashMap<String, Region>)regionsCopy, sortMethod);
            
            Region tempRegion;
            Iterator<Region> iter = regionsCopy.values().iterator();
            for (int i = 0; iter.hasNext(); i++) {
                tempRegion = iter.next();
                names[i] = tempRegion.getName();
                if (sortMethod == "Area")
                    data[i] = Long.parseLong(tempRegion.getArea());
                if (sortMethod == "Population")
                    data[i] = Long.parseLong(tempRegion.getPop());
            }
            
            barHeight = new int[regionsCopy.size()];
            setPreferredSize(new Dimension(barWidth * regionsCopy.size(), 300));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            refreshData();
            
            for (int i = 0; i < regionsCopy.size(); i++) {
                barHeight[i] = (int)(data[i] / (double)data[0]
                        * getHeight() * 0.9);
                g.setColor(colors[i % colors.length]);
                g.fillRect(i * barWidth,
                        getHeight() - barHeight[i] - 5,
                        barWidth,
                        barHeight[i]);
                
            }
        }
    }
}
