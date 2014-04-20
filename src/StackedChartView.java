import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class StackedChartView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1824000702248768413L;
    
    private GeoModel model;
    private LinkedHashMap<String, Region> regions;
    private String dataType;
    private BarsPanel barsPanel;
    
    public StackedChartView(String title, LinkedHashMap<String, Region> regions,
            String dataType, GeoModel newModel) {
        
        // "this.model = model" seems to confuse the compiler
        model = newModel;
        model.addActionListener(this);
        
        this.regions = regions;
        this.dataType = dataType;
        
        barsPanel = new BarsPanel();
        add(barsPanel);
        
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
        private Color[] colors = {Color.DARK_GRAY, Color.GRAY};
        
        private int numOfRegions;
        private int barHeight[];
        private int barY[];
        private int barWidth = 50;
        
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
            barY = new int[numOfRegions];
            int nextY = 0;
            for (int i = numOfRegions - 1; i >= 0; i--) {
                barHeight[i] = (int)Math.pow(data[i] /
                                             (double)data[numOfRegions-1] *
                                             20736,
                                             1.0 / 4);
                barY[i] = nextY;
                nextY += barHeight[i];
            }
            
            setPreferredSize(new Dimension(400, nextY));
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            refreshData();
            
            FontMetrics font = g.getFontMetrics();
            String dataString;
            
            int center = getWidth() / 2;
            for (int i = numOfRegions - 1; i >= 0; i--) {
                g.setColor(colors[i % colors.length]);
                g.fillRect(center - barWidth / 2, barY[i], barWidth, barHeight[i]);
                
                dataString = String.valueOf(data[i]);
                g.drawString(dataString,
                        center - barWidth / 2 - font.stringWidth(dataString),
                        barY[i] + barHeight[i] / 2 + font.getAscent() / 2);
                g.drawString(names[i],
                        center + barWidth / 2,
                        barY[i] + barHeight[i] / 2 + font.getAscent() / 2);
            }
        }
    }

}
