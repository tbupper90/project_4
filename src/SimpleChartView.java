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
    
    private GeoModel model;
    private LinkedHashMap<String, Region> regions;
    private String dataType;
    private BarsPanel barsPanel;
            

    public SimpleChartView(String title, LinkedHashMap<String, Region> regions,
            String dataType, GeoModel newModel) {
        
        model = newModel;
        model.addActionListener(this);
        
        this.regions = regions;
        this.dataType = dataType;
        
        barsPanel = new BarsPanel();
        add(barsPanel);
        
        setTitle(title);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
        
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
        
    class BarsPanel extends JPanel {
        private static final long serialVersionUID = -1079594597927132094L;
        private Color[] colors = {Color.DARK_GRAY, Color.LIGHT_GRAY};
        
        private int barHeight[];
        private int barWidth = 20;
        
        private LinkedHashMap<String, Region> regionsCopy;
        private String[] names;
        private long[] data;
        
        
        public BarsPanel() {

        }
        
        private void refreshData() {
            // Create/refresh a shallow copy that can be sorted without
            // affecting the original
            regionsCopy = new LinkedHashMap<String, Region>(regions);
            regionsCopy = sort.performSort(
                    (LinkedHashMap<String, Region>)regionsCopy, dataType);
            names = new String[regionsCopy.size()];
            data = new long[regionsCopy.size()];
            
            Region tempRegion;
            Iterator<Region> iter = regionsCopy.values().iterator();
            for (int i = 0; iter.hasNext(); i++) {
                tempRegion = iter.next();
                names[i] = tempRegion.getName();
                if (dataType.equals("Area"))
                    data[i] = Long.parseLong(tempRegion.getArea());
                if (dataType.equals("Population"))
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
