package controller.dashboard_controller.admin_dashboard_controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.dashboard_controller.SideFeatureOption;
import view.dashboard.side_feature_option.BoardingRoomPanel;
import view.dashboard.side_feature_option.ClassPanel;
import view.dashboard.side_feature_option.InformationPanel;
import view.dashboard.side_feature_option.InitPanel;
import view.dashboard.side_feature_option.PaymentPanel;
import view.dashboard.side_feature_option.PupilPanel;
import view.dashboard.side_feature_option.TeacherPanel;

public class AdminDashboardController {
    private JPanel rootPanel;
    private String selectedPanelTitle = "";
    private List<SideFeatureOption> listItem;

    public AdminDashboardController(JPanel rootPanel, JPanel selectedPanel, JLabel selectedLabel) {
        this.rootPanel = rootPanel;
        selectedPanelTitle = "Init";
        selectedPanel.setBackground(new Color(96, 100, 191));
        selectedLabel.setBackground(new Color(96, 100, 191));
        rootPanel.removeAll();
        rootPanel.setLayout(new FlowLayout());
        rootPanel.add(new InitPanel());
        rootPanel.validate();
        rootPanel.repaint();
    }

    public void setEvent(List<SideFeatureOption> listItem) {
        this.listItem = listItem;
        for (SideFeatureOption item : listItem) {
            item.getPanel().addMouseListener(
                new LabelEvent(
                    item.getTitle(), 
                    item.getPanel(), 
                    item.getLabel()
                )
            );
        }
    }

    class LabelEvent implements MouseListener {
        private JPanel root;
        private String sideFeatureOptionTitle;
        private JPanel sideFeatureOptionPanel;
        private JLabel sideFeatureOptionLabel;

        public LabelEvent(String title, JPanel panel, JLabel label) {
            this.sideFeatureOptionTitle = title;
            this.sideFeatureOptionPanel = panel;
            this.sideFeatureOptionLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (sideFeatureOptionTitle) {
                case "Init": {
                    root = new InitPanel();
                    break;
                }
                case "Teacher": {
                    try {
                        root = new TeacherPanel();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
                case "Pupil": {
                    root = new PupilPanel();
                    break;
                }
                case "Class": {
                    root = new ClassPanel();
                    break;
                }
                case "BoardingRoom": {
                    root = new BoardingRoomPanel();
                    break;
                }
                case "Payment": {
                    root = new PaymentPanel();
                    break;    
                }
                case "Info": {
                    root = new InformationPanel();
                    break;
                }
                default: {
                    root = new InitPanel();
                    break;
                }
            }
            rootPanel.removeAll();
            rootPanel.setLayout(new BorderLayout());
            rootPanel.add(root);
            rootPanel.validate();
            rootPanel.repaint();
            setChangeBackground(sideFeatureOptionTitle);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            selectedPanelTitle = sideFeatureOptionTitle;
            sideFeatureOptionPanel.setBackground(new Color(96, 100, 191));
            sideFeatureOptionLabel.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            sideFeatureOptionPanel.setBackground(new Color(96, 100, 191));
            sideFeatureOptionLabel.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!selectedPanelTitle.equalsIgnoreCase(sideFeatureOptionTitle)) {
                sideFeatureOptionPanel.setBackground(new Color(0, 153, 255));
                sideFeatureOptionLabel.setBackground(new Color(0, 153, 255));
            }
        }

    }

    private void setChangeBackground(String title) {
        for (SideFeatureOption item : listItem) {
            if (item.getTitle().equalsIgnoreCase("Init")) {
                item.getPanel().setBackground(new Color(255,255,255));
                item.getLabel().setBackground(new Color(255,255,255));
                continue;
            }
            if (item.getTitle().equalsIgnoreCase(title)) {
                item.getPanel().setBackground(new Color(96, 100, 191));
                item.getLabel().setBackground(new Color(96, 100, 191));
            } else {
                item.getPanel().setBackground(new Color(0, 153, 255));
                item.getLabel().setBackground(new Color(0, 153, 255));
            }
        }
    }
}
