package controller.dashboard_controller.pupil_dashboard_controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import constant.Role;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.account.Account;
import view.dashboard.SideFeatureOption;
import view.dashboard.admin_dashboard.InformationPanel;
import view.dashboard.admin_dashboard.InitPanel;
import view.dashboard.pupil_dashboard.AbsenceHistoryPanel;
import view.dashboard.pupil_dashboard.AbsenceRegisterPanel;
import view.dashboard.pupil_dashboard.PaymentPanel;
import view.dashboard.teacher_dashboard.MenuInitPanel;

public class PupilDashboardController {
    private JPanel viewPanel;
    private String selectedPanelTitle = "";
    private List<SideFeatureOption> listItem;
    private Account account=null;
    public PupilDashboardController(JPanel viewPanel, JPanel selectedPanel, JLabel selectedLabel, Account account) throws ClassNotFoundException, SQLException, IOException {
        this.viewPanel = viewPanel;
        this.account=account;
        selectedPanelTitle = "Init";
        selectedPanel.setBackground(new Color(96, 100, 191));
        selectedLabel.setBackground(new Color(96, 100, 191));
        viewPanel.removeAll();
        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(new MenuInitPanel(account));
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
            System.out.println(sideFeatureOptionTitle);
            switch (sideFeatureOptionTitle) {
                case "Init": {
                try {
                    view = new MenuInitPanel(account);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(PupilDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
                }
                case "Absence register": {
                    try {
                        view = new AbsenceRegisterPanel(account);
                    } catch (Exception e1) {e1.printStackTrace();}
                    break;
                }
                case "Absence history": {
                    try {
                        view = new AbsenceHistoryPanel(account);
                    } catch (Exception e1) {e1.printStackTrace();}
                    break;
                }
                case "Payment": {
                    try {
                        view = new PaymentPanel(account, Role.PUPIL);
                    } catch (ClassNotFoundException | SQLException e1) {
                        e1.printStackTrace();
                    }
                    break;
                }
                case "Info": {
                    view = new InformationPanel(account);
                    break;
                }
                default: {
                try {
                    view = new MenuInitPanel(account);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(PupilDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
                }
            }
            viewPanel.removeAll();
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
