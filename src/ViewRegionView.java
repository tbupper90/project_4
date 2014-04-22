import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ViewRegionView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 3812185516506663582L;
    
    private GeoModel model;
    private Region region;
    private String type;
    
    private JPanel viewPanel;
    
    private String name;
    private String pop;
    private String area;
    private String description;
    private String lat;
    private String lon;
    private String elev;
    private String location;

    public ViewRegionView(Region r, GeoModel newModel) {
        
        model = newModel;
        model.addActionListener(this);
        
        region = r;
        type = r.getClass().getName();
        
        viewPanel = new ViewPanel();
        add(new JScrollPane(viewPanel));
        
        setTitle("Data for " + r.getName());
        setSize(200, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    class ViewPanel extends JPanel {
        
        JLabel nameLabel = new JLabel();
        JLabel popLabel = new JLabel();
        JLabel areaLabel = new JLabel();
        JLabel latLabel = new JLabel();
        JLabel lonLabel = new JLabel();
        JLabel elevLabel = new JLabel();
        JLabel locLabel = new JLabel();
        JLabel descLabel = new JLabel();
        
        
        public ViewPanel() {
            setMinimumSize(new Dimension(200, 200));
            
            JPanel labels = new JPanel(new GridLayout(0, 1));
            JPanel data = new JPanel(new GridLayout(0, 1));

            labels.add(new JLabel("Name:  "));
            data.add(nameLabel);
            labels.add(new JLabel("Population:  "));
            data.add(popLabel);
            labels.add(new JLabel("Area:  "));
            data.add(areaLabel);
            labels.add(new JLabel("Latitude:  "));
            data.add(latLabel);
            labels.add(new JLabel("Longitude:  "));
            data.add(lonLabel);
            labels.add(new JLabel("Elevation:  "));
            data.add(elevLabel);
            labels.add(new JLabel("Location:  "));
            data.add(locLabel);
            labels.add(new JLabel("Description:  "));
            data.add(descLabel);
            
            add(labels);
            add(data);
        }

        public void refreshData() {
//          if (region == null) dispose();
            
            name = region.getName();
            
            pop = "N/A";
            area = "N/A";
            description = "N/A";
            lat = "N/A";
            lon = "N/A";
            elev = "N/A";
            location = "";
            
            switch (type) {
            case "Continent":
                pop = region.getPop();
                area = region.getArea();
                break;
            case "Country":
                pop = region.getPop();
                area = region.getArea();
                location = ((Country)region).getContinent().toString();
                break;
            case "City":
                pop = region.getPop();
                area = region.getArea();
                lat = ((City)region).getLat();
                lon = ((City)region).getLon();
                elev = ((City)region).getElev();
                location = ((City)region).getCountry().toString();
                break;
            case "PlaceOfInterest":
                area = region.getArea();
                description = ((PlaceOfInterest)region).getDescription();
                for (String name: ((PlaceOfInterest)region).getLocations().keySet()) {
                    location = location + name + ", ";
                }
                location = location.substring(0, location.length()-2);
                break;
            case "PointOfInterest":
                description = ((PointOfInterest)region).getDescription();
                lat = ((PointOfInterest)region).getLat();
                lon = ((PointOfInterest)region).getLon();
                elev = ((PointOfInterest)region).getElev();
                location = ((PointOfInterest)region).getLocation().toString();
                break;
            }
            
            nameLabel.setText(name);
            popLabel.setText(pop);
            areaLabel.setText(area);
            latLabel.setText(lat);
            lonLabel.setText(lon);
            elevLabel.setText(elev);
            locLabel.setText(location);
            descLabel.setText(description);
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            refreshData();
        }
    }
    

}
