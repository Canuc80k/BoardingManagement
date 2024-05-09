package controller.dashboard_controller;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SideFeatureOption {
    private String title;
    private JPanel panel;
    private JLabel label;

    public SideFeatureOption(String title, JPanel panel, JLabel label) {
        this.title = title;
        this.panel = panel; 
        this.label = label;
    }

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public JPanel getPanel() {return panel;}
    public void setPanel(JPanel panel) {this.panel = panel;}
    public JLabel getLabel() {return label;}
    public void setLabel(JLabel label) {this.label = label;}
}
