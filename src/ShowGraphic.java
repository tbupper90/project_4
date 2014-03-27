import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This class displays requested information using graphical elements.
 */
public class ShowGraphic 
{
	/**
	 * Displays a bar graph with requested information.
	 * @param list The list with information to display
	 * @param sortMethod How the information should be sorted
	 */
    public static void makeBarGraph(final String[] names,final long[] data, String title)
	{
        // ***NOTE: Still needs window title
        
        // The barsPanel class below that extends JPanel can't read non-final
        final int entries = names.length;
        final long[] barData = data;
        final int barWidth = 40;
        
        
        JDialog barDialog = new JDialog(null, title, JDialog.DEFAULT_MODALITY_TYPE);
		barDialog.setSize(600, 400);
		barDialog.setLocationRelativeTo(null);
		barDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		// Top panel that displays maximum value
        JPanel maxPanel = new JPanel();
        maxPanel.setLayout(new GridLayout());
        maxPanel.add(new JLabel("Maximum value: " + names[0] +
                ", " + data[0], JLabel.CENTER));
        
        // Bottom panel that displays minimum value
        JPanel minPanel = new JPanel();
        minPanel.setLayout(new GridLayout());
        minPanel.add(new JLabel("Minimum value: " + names[names.length-1] +
                ", " + data[names.length-1], JLabel.CENTER));
        
        // Middle panel that displays bars
        final JPanel barsPanel = new JPanel()
        {
			private static final long serialVersionUID = -593564669223958876L;
			int barLength[] = new int[entries];
            Color[] colors = {Color.DARK_GRAY, Color.LIGHT_GRAY};
            @Override
            public void paintComponent(final Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < entries; i++) {
                	final int j = i;
                	final String name = names[i];
                    barLength[i] = (int)(barData[i] / (double)barData[0]
                                         * getHeight() * 0.9);
                    g.setColor(colors[i % colors.length]);
                    g.fillRect(i * barWidth,                // x coordinate
                            getHeight() - barLength[i] - 5, // y coordinate
                            barWidth,                       // width
                            barLength[i]);                  // height
                    final MouseMotionListener ml = new MouseMotionListener(){
                        @Override
                        public void mouseMoved(MouseEvent e) {
                        	
                        	Rectangle rect = new Rectangle();
                            rect.setBounds(j * barWidth, getHeight() - barLength[j] - 5, barWidth, barLength[j]);
                            if(rect.contains(e.getPoint()))  
                                {setToolTipText(name + ", data: " + data[j]);
                                removeMouseMotionListener(this);
                                }
                            else
                            	removeMouseMotionListener(this);  
                              
                            
							
                        }

        				@Override
        				public void mouseDragged(MouseEvent f) {
        					removeMouseMotionListener(this);
        					
        				}};
        			this.addMouseMotionListener(ml);
                    
                   
                }
                // Separator bar
                g.setColor(Color.BLACK);
                g.fillRect(0, getHeight() - 5, getWidth(), 5);
                
                 
                  
            }     
            
        };
        
		barsPanel.setPreferredSize(new Dimension(barWidth * entries, 300));
		
		// Put panel with the bars onto a JScrollPane
        JScrollPane barScrollPane = new JScrollPane();
        barScrollPane.setViewportView(barsPanel);
        
		// Add components to the JDialog
        barDialog.add(maxPanel, BorderLayout.NORTH);
        barDialog.add(barScrollPane, BorderLayout.CENTER);
        barDialog.add(minPanel, BorderLayout.SOUTH);

        // Set barDialog to modal so program doesn't continue before it's closed
		//barDialog.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
		
		barDialog.setVisible(true);
	}
	
    /**
     * Displays a single bar composed of smaller segments
     * @param list The list with information to display
     * @param sortMethod How the information should be sorted
     */
    public static void makeSegmentGraph(String[] names, long[] data, String title)
    {
        // Initialize variables accessible by overridden JPanels
        final int entries = names.length;
        final String[] segNames = names;
        final long[] segData = data;
        
        final int[] segLength = new int[entries];
        final int[] segmentY = new int[entries];
        int segStep = 0;
        for (int i = entries - 1; i >= 0; i--)
        {                                // Take the current data point...
            segLength[i] = (int)Math.pow(segData[i] /
                                         // divided by the smallest data point...
                                         (double)segData[entries - 1] *
                                         // multiplied by 12^4 (font size 12)...
                                         20736,
                                         // and calculate the fourth root.
                                         1.0/ 4);
            segmentY[i] = segStep;
            segStep += segLength[i];
        }
        
        JDialog segDialog = new JDialog(null, title, JDialog.DEFAULT_MODALITY_TYPE);
        segDialog.setSize(600, 400);
        segDialog.setLocationRelativeTo(null);
        segDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        // Label above the segment graph
        JPanel labelPanel = new JPanel()
        {
			private static final long serialVersionUID = 8780747432464208L;
			String label = "*Note: Graph segments are scaled by a factor of the fourth root";
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                FontMetrics labelMetric = g.getFontMetrics();
                g.drawString(label, (getWidth() - labelMetric.stringWidth(label)) / 2, 12);
            }
        };
        labelPanel.setPreferredSize(new Dimension(0, 15));
        
        // Main panel that displays the segments
        JPanel segPanel = new JPanel()
        {
			private static final long serialVersionUID = 4555257909438952550L;
			String dataString;
            int segCenter;
            Color[] colors = {Color.DARK_GRAY, Color.GRAY};
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                FontMetrics segMetric = g.getFontMetrics();
                //segCenter = segMetric.stringWidth(String.valueOf(segData[0])) + 25;
                segCenter = getWidth() / 2;
                for (int i = entries - 1; i >= 0; i--) {
                    g.setColor(colors[i % colors.length]);
                    g.fillRect(segCenter - 25, segmentY[i], 50, segLength[i]);
                    dataString = String.valueOf(segData[i]);
                    g.drawString(dataString,
                            segCenter - 25 - segMetric.stringWidth(dataString),
                            segmentY[i] + segLength[i] / 2 +
                            segMetric.getAscent() / 2);                    
                    g.drawString(segNames[i],
                            segCenter + 25,
                            segmentY[i] + segLength[i] / 2 +
                            segMetric.getAscent() / 2);
                    
                }
            }
        };
        segPanel.setPreferredSize(new Dimension(400, segStep));
        
        
        JScrollPane segScrollPane = new JScrollPane();
        segScrollPane.setViewportView(segPanel);
        
        segDialog.add(labelPanel, BorderLayout.NORTH);
        segDialog.add(segScrollPane, BorderLayout.CENTER);
        
        //segDialog.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        
        segDialog.setVisible(true);
    }
    /**
     * Displays a map with requested information.
     * @param cities The list of cities to display
     */
	public static void makeWorldMap(String[] names, String[][] lonLat, String title) throws IOException
	{
        // lonLat[?][0] is x, and lonLat[?][1] is y
        
	    // For extended JPanel to read
	    final String[] mapNames = names;
	    final int numPlot = names.length;
	    // Convert coordinate data to something useful
	    final int[][] mapPlots = new int[numPlot][2];
	    int tempLon;
        int tempLat;
	    for (int i = 0; i < numPlot; i++) {
	        tempLon = (int)Float.parseFloat(lonLat[i][0].substring(1));
	        if (lonLat[i][0].contains("E")) {
	            tempLon = (180 + tempLon) % 359;
	        } else if (lonLat[i][0].contains("W")) {
	            tempLon = (180 - tempLon) % 359;
	        }
            tempLat = (int)Float.parseFloat(lonLat[i][1].substring(1));
            if (lonLat[i][1].contains("S")) {
                tempLat = (90 + tempLat) % 179;
            } else if (lonLat[i][1].contains("N")) {
                tempLat = (90 - tempLat) % 179;
            }	        
	        mapPlots[i][0] = tempLon;
	        mapPlots[i][1] = tempLat;
	    }
	    
        final BufferedImage img = ImageIO.read(new File("Worldmap.jpg"));

        JDialog mapDialog = new JDialog(null, title, JDialog.DEFAULT_MODALITY_TYPE);
        mapDialog.setSize(1200, 615);
        mapDialog.setLocationRelativeTo(null);
        mapDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Citation below the map
        JPanel imageCitation = new JPanel()
        {
        	private static final long serialVersionUID = 8303017413811758055L;
			String label = "Image source: http://en.wikipedia.org/wiki/Equirectangular_projection";
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                FontMetrics labelMetric = g.getFontMetrics();
                g.drawString(label, (getWidth() - labelMetric.stringWidth(label)) / 2, 12);
            }
        };
        imageCitation.setPreferredSize(new Dimension(0, 15));
        
        JPanel mapPanel = new JPanel()
        {
			private static final long serialVersionUID = 4238985815794046739L;
			int plotLon;
            int plotLat;
            Color[] colors = {Color.RED, Color.BLUE, Color.DARK_GRAY};
            Color semiWhite = new Color(255, 255, 255, 148);
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                FontMetrics mapMetric = g.getFontMetrics();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
                for (int i = 0; i < numPlot; i++) {
                    plotLon = mapPlots[i][0];
                    plotLat = mapPlots[i][1];
                    g.setColor(semiWhite);
                    g.fillOval(plotLon * getWidth() / 360 - 4,
                               plotLat * getHeight() / 180 - 4, 9, 9);
                    g.fillRect(plotLon * getWidth() / 360 + 4,
                               plotLat * getHeight() / 180 - 10,
                               mapMetric.stringWidth(mapNames[i]) + 1,
                               12);
                    g.setColor(colors[i % colors.length]);
                    g.fillOval(plotLon * getWidth() / 360 - 3,
                               plotLat * getHeight() / 180 - 3, 7, 7);
                    g.drawString(mapNames[i],
                                 plotLon * getWidth() / 360 + 5,
                                 plotLat * getHeight() / 180);                    
                }
            }
        };
        mapPanel.setPreferredSize(new Dimension(1029, 518));
        
        JScrollPane mapScrollPane = new JScrollPane();
        mapScrollPane.setViewportView(mapPanel);
        
        mapDialog.add(mapScrollPane, BorderLayout.CENTER);
        mapDialog.add(imageCitation, BorderLayout.SOUTH);
        
        //mapDialog.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        mapDialog.setVisible(true);
	    
	}
}
