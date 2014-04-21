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
        
        
        // "this.model = model" seems to confuse the compiler
        model = newModel;
        model.addActionListener(this);
        
        this.regions = regions;
        this.dataType = dataType;
        
        barsPanel = new BarsPanel();
        add(new JScrollPane(barsPanel));
        
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
        
        private int numOfRegions;
        private int barHeight[];
        private int barWidth = 40;
        
        private LinkedHashMap<String, Region> regionsCopy;
        private String[] names;
        private long[] data;
        
        public BarsPanel() {

        }
        
        private void refreshData() {
            // Create/refresh a shallow copy of regions that can be sorted
            // without affecting the original
            regionsCopy = new LinkedHashMap<String, Region>(regions);
            regionsCopy = sort.performSort(regionsCopy, dataType);
            
            numOfRegions = regionsCopy.size();
            if (numOfRegions == 0) dispose();
            
            names = new String[numOfRegions];
            data = new long[numOfRegions];
            
            Iterator<Region> iter = regionsCopy.values().iterator();
            for (int i = 0; iter.hasNext(); i++) {
                Region tempRegion = iter.next();
                names[i] = tempRegion.getName();
                if (dataType.equals("Area"))
                    data[i] = Long.parseLong(tempRegion.getArea());
                if (dataType.equals("Population"))
                    data[i] = Long.parseLong(tempRegion.getPop());
            }
            
            barHeight = new int[numOfRegions];
            setPreferredSize(new Dimension(barWidth * numOfRegions, 300));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            refreshData();
            
            for (int i = 0; i < numOfRegions; i++) {
                barHeight[i] = (int)(data[i] / (double)data[0]
                        * getHeight() * 0.9);
                g.setColor(colors[i % colors.length]);
                g.fillRect(i * barWidth,
                        getHeight() - barHeight[i] - 5,
                        barWidth,
                        barHeight[i]);                
            }
            // Separator bar
            g.setColor(Color.BLACK);
            g.fillRect(0, getHeight() - 5, getWidth(), 5);
        }
    }
}
