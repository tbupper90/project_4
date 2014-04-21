import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.swing.*;


public class MapView extends JFrame implements ActionListener {
    private static final long serialVersionUID = -7081517290750627650L;
    
    private GeoModel model;
    private LinkedHashMap<String, Region> regions;
    private String dataType;
    private MapPanel mapPanel;

    private BufferedImage img;

    public MapView(String title, LinkedHashMap<String, Region> regions,
            String dataType, GeoModel newModel) {
        
        // "this.model = model" seems to confuse the compiler
        model = newModel;
        model.addActionListener(this);
        
        this.regions = regions;
        this.dataType = dataType;
        
        try {
            img = ImageIO.read(new File("Worldmap.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
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
            setPreferredSize(new Dimension(1029, 518));
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
            
            boolean noGPSData = true;
            
            Iterator<Region> iter = regionsCopy.values().iterator();
            for (int i = 0; iter.hasNext(); i++) {
                // A very dirty workaround...
                City tempCity;
                PointOfInterest tempPoint;
                if (dataType.equals("Cities")) {
                    tempCity = (City)iter.next();
                    names[i] = tempCity.getName();
                    lonString = tempCity.getLon();
                    latString = tempCity.getLat();
                } else if (dataType.equals("Points of Interest")) {
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
                    } else
                    if (lonString.contains("W")) {
                        tempLon = (180 - tempLon) % 359;
                    }
                    if (latString.contains("S")) {
                        tempLat = (90 + tempLat) % 179;
                    } else
                    if (latString.contains("N")) {
                        tempLat = (90 - tempLat) % 179;
                    }
                    plotLon[i] = tempLon;
                    plotLat[i] = tempLat;
                    noGPSData = false;
                }
            }
            
            if (noGPSData) dispose();
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            refreshData();
            
            FontMetrics font = g.getFontMetrics();
            g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
            
            for (int i = 0; i < numOfRegions; i++) {
                if (plotLon[i] >= 0 && plotLat[i] >= 0) {
                    g.setColor(semiWhite);
                    g.fillOval(plotLon[i] * getWidth() / 360 - 4,
                               plotLat[i] * getHeight() / 180 - 4, 9, 9);
                    g.fillRect(plotLon[i] * getWidth() / 360 + 4,
                               plotLat[i] * getHeight() / 180 - 10,
                               font.stringWidth(names[i]) + 1,
                               12);
                    g.setColor(colors[i % colors.length]);
                    g.fillOval(plotLon[i] * getWidth() / 360 - 3,
                               plotLat[i] * getHeight() / 180 - 3, 7, 7);
                    g.drawString(names[i],
                                 plotLon[i] * getWidth() / 360 + 5,
                                 plotLat[i] * getHeight() / 180);                    
                }
            }
        }
    }

}
