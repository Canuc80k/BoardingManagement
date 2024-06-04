package controller.dashboard_controller.admin_dashboard_controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JPanel;

import constant.Role;
import model.account.Account;
import view.dashboard.SideFeatureOption;
import view.dashboard.admin_dashboard.BoardingroomPanel;
import view.dashboard.admin_dashboard.ClassroomPanel;
import view.dashboard.admin_dashboard.InformationPanel;
import view.dashboard.admin_dashboard.InitPanel;
import view.dashboard.admin_dashboard.ManagerPanel;
import view.dashboard.admin_dashboard.MealPanel;
import view.dashboard.admin_dashboard.PaymentPanel;
import view.dashboard.admin_dashboard.PupilPanel;
import view.dashboard.admin_dashboard.TeacherPanel;

public class AdminDashboardController {
    private JPanel viewPanel;
    private String selectedPanelTitle = "";
    private List<SideFeatureOption> listItem;
    private Account account = null;

    public AdminDashboardController(JPanel viewPanel, JPanel selectedPanel, JLabel selectedLabel, Account account) {
        this.viewPanel = viewPanel;
        this.account = account;
        selectedPanelTitle = "Init";
        selectedPanel.setBackground(new Color(96, 100, 191));
        selectedLabel.setBackground(new Color(96, 100, 191));
        viewPanel.removeAll();
        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(new InitPanel(account));
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
                            item.getLabel()));
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
                    view = new InitPanel(account);
                    break;
                }
                case "Teacher": {

                    try {
                        view = new TeacherPanel();

                        break;
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                case "Manager": {
                    try {
                        view = new ManagerPanel();
                        break;
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                case "Pupil": {
                    try {
                        view = new PupilPanel();
                        break;
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                case "Class": {
                    try {
                        view = new ClassroomPanel();
                        break;
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                case "BoardingRoom": {
                    try {
                        view = new BoardingroomPanel();
                        break;
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                case "Payment": {
                    view = new PaymentPanel(Role.ADMIN, account);
                    break;
                }
                case "Info": {
                    view = new InformationPanel(account);
                    break;
                }
                case "Meal":{
                try {
                    //System.out.println("Menu\n");
                    view = new MealPanel();
                    System.out.println("Menu\n");
                    break;
                    
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                default: {
                    view = new InitPanel(account);
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
                item.getPanel().setBackground(new Color(255, 255, 255));
                item.getLabel().setBackground(new Color(255, 255, 255));
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
