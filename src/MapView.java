import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.swing.*;


public class MapView extends JFrame implements ActionListener {
    private static final long serialVersionUID = -7081517290750627650L;
    
    private GeoModel model;
    private LinkedHashMap<String, Region> regions;
    private String dataType;
    private MapPanel mapPanel;
    
    public MapView(String title, LinkedHashMap<String, Region> regions,
            String dataType, GeoModel newModel) {
        
        // "this.model = model" seems to confuse the compiler
        model = newModel;
        model.addActionListener(this);
        
        this.regions = regions;
        this.dataType = dataType;
        
        mapPanel = new MapPanel();
        add(new JScrollPane(mapPanel));
        
        setTitle(title);
        setSize(1200, 615);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    class MapPanel extends JPanel {
        private static final long serialVersionUID = 7358977136519026537L;
        
        private int numOfRegions;
        private Color[] colors = {Color.RED, Color.BLUE, Color.DARK_GRAY};
        private Color semiWhite = new Color(255, 255, 255, 148);
        
        private LinkedHashMap<String, Region> regionsCopy;
        private String[] names;
        private int[] plotLon;
        private int[] plotLat;
        
        public MapPanel() {
            
        }
        
        private void refreshData() {
            // Create/refresh a shallow copy of regions that can be sorted
            // without affecting the original
            regionsCopy = new LinkedHashMap<String, Region>(regions);
            
            numOfRegions = regionsCopy.size();
            if (numOfRegions == 0) dispose();
            
            names = new String[numOfRegions];
            plotLon = new int[numOfRegions];
            plotLat = new int[numOfRegions];
            
            String lonString = "";
            String latString = "";
            int tempLon = 0;
            int tempLat = 0;
            
            Iterator<Region> iter = regionsCopy.values().iterator();
            for (int i = 0; iter.hasNext(); i++) {
                // A very dirty workaround...
                City tempCity;
                PointOfInterest tempPoint;
                if (dataType == "Cities") {
                    tempCity = (City)iter.next();
                    names[i] = tempCity.getName();
                    lonString = tempCity.getLon();
                    latString = tempCity.getLat();
                } else if (dataType == "Points of Interest") {
                    tempPoint = (PointOfInterest)iter.next();
                    names[i] = tempPoint.getName();
                    lonString = tempPoint.getLon();
                    latString = tempPoint.getLat();
                }
                
                plotLon[i] = -1;
                plotLat[i] = -1;
                if (lonString != "" && latString != "") {
                    tempLon = (int)Float.parseFloat(lonString.substring(1));
                    tempLat = (int)Float.parseFloat(latString.substring(1));                    
                    // Convert GPS coordinates to something usable
                    if (lonString.contains("E")) {
                        tempLon = (180 + tempLon) % 359;
                    } else if (lonString.contains("W")) {
                        tempLon = (180 - tempLon) % 359;
                    }
                    if (latString.contains("S")) {
                        tempLat = (90 + tempLat) % 179;
                    } else if (latString.contains("N")) {
                        tempLat = (90 - tempLat) % 179;
                    }
                    plotLon[i] = tempLon;
                    plotLat[i] = tempLat;
                }
            }
            
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            refreshData();
            
            
        }
    }

}
