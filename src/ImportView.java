import java.awt.*;

import javax.swing.*;


public class ImportView extends JFrame {
    private static final long serialVersionUID = 945819704647869096L;

    private String[] labels = {"Continents",
                               "Countries",
                               "Cities",
                               "Places of Interest",
                               "Points of Interest"};
    private JRadioButton[] radioButtons = new JRadioButton[labels.length];
    private JTextField[] textFields = new JTextField[labels.length];
    JButton importBtn = new JButton("Import");
    
    public ImportView() {
        setMinimumSize(new Dimension(200, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridLayout(5, 2));
        ButtonGroup btnGroup = new ButtonGroup();

        for (int i = 0; i < labels.length; i++) {
            radioButtons[i].setText(labels[i]);
            btnGroup.add(radioButtons[i]);
            panel.add(radioButtons[i]);
            panel.add(textFields[i]);
        }
        
        add(panel, BorderLayout.CENTER);
        add(importBtn, BorderLayout.SOUTH);
    }
    
    public String[] importFile() {
        
        for (int i = 0; i < labels.length; i++) {
            if (radioButtons[i].isSelected())
                return new String[] {textFields[i].getText(), labels[i]};
        }
        return null;
    }

}
