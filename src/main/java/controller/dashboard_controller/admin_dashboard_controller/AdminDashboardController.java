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
    private JPanel viewPanel;
    private String selectedPanelTitle = "";
    private List<SideFeatureOption> listItem;

    public AdminDashboardController(JPanel viewPanel, JPanel selectedPanel, JLabel selectedLabel) {
        this.viewPanel = viewPanel;
        selectedPanelTitle = "Init";
        selectedPanel.setBackground(new Color(96, 100, 191));
        selectedLabel.setBackground(new Color(96, 100, 191));
        viewPanel.removeAll();
        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(new InitPanel());
        viewPanel.validate();
        viewPanel.repaint();
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
        private JPanel view;
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
                    view = new InitPanel();
                    break;
                }
                case "Teacher": {
                    try {
                        view = new TeacherPanel();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
                case "Pupil": {
                    view = new PupilPanel();
                    break;
                }
                case "Class": {
                    view = new ClassPanel();
                    break;
                }
                case "BoardingRoom": {
                    view = new BoardingRoomPanel();
                    break;
                }
                case "Payment": {
                    view = new PaymentPanel();
                    break;    
                }
                case "Info": {
                    view = new InformationPanel();
                    break;
                }
                default: {
                    view = new InitPanel();
                    break;
                }
            }
            viewPanel.removeAll();
            viewPanel.setLayout(new BorderLayout());
            viewPanel.add(view);
            viewPanel.validate();
            viewPanel.repaint();
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
