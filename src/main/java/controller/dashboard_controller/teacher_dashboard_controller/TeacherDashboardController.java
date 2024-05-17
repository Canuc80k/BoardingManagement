package controller.dashboard_controller.teacher_dashboard_controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import constant.Role;
import model.account.Account;
import model.classroom.Classroom;
import model.classroom.ClassroomDatabase;
import model.people.teacher.Teacher;
import model.people.teacher.TeacherDatabase;
import view.dashboard.SideFeatureOption;
import view.dashboard.admin_dashboard.InformationPanel;
import view.dashboard.admin_dashboard.InitPanel;
import view.dashboard.admin_dashboard.PaymentPanel;
import view.dashboard.pupil_dashboard.AbsenceHistoryPanel;
import view.dashboard.teacher_dashboard.ClassroomPanel;

public class TeacherDashboardController {

    private JPanel viewPanel;
    private String selectedPanelTitle = "";
    private List<SideFeatureOption> listItem;
    private Account account = null;

    public TeacherDashboardController(JPanel viewPanel, JPanel selectedPanel, JLabel selectedLabel, Account account) throws ClassNotFoundException, SQLException {
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
                    // view = new InitPanel(account);
                    break;
                }
                case "Class": {
                    try {
                        // Retrieve the teacher's information
                        List<Teacher> listItemTeacher = TeacherDatabase.getAllTeacher("SELECT * FROM teacher WHERE id='" + account.getID() + "'");

                        String temp = null;
                        // Extract the class ID from the teacher's information
                        for (Teacher teacher : listItemTeacher) {
                            temp = teacher.getClassID();
                        }

                        // Fetch the classroom information using the class ID
                        if (temp != null) {
                            List<Classroom> classes = ClassroomDatabase.getAllClassrooms("SELECT * FROM classroom WHERE classid='" + temp + "'");
                            // Assuming you want to process only the first classroom found
                            if (!classes.isEmpty()) {
                                Classroom classroom = classes.get(0); // Get the first classroom
                               // System.out.println("checking.......constructor classroompanel");
                                view = new ClassroomPanel(classroom);
                            } else {
                                // Handle case where no classroom is found for the teacher
                                // You can show a message to the user indicating that no classroom is assigned to the teacher
                            }
                        } else {
                            // Handle case where the teacher's class ID is null
                            // You can show a message to the user indicating that the teacher's class is not assigned
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        // Handle any exceptions that occur during database operations
                        // You can show an error message to the user if needed
                    }
                    break;
                }
                case "Absence History": {
                    try {
                        view = new AbsenceHistoryPanel(account);
                    } catch (ClassNotFoundException | SQLException e1) {
                        e1.printStackTrace();
                    }
                    break;
                }
                case "Payment": {
                    view = new PaymentPanel(Role.TEACHER, account);
                    break;
                }
                case "Info": {
                    // System.out.println("111111111111111111111111111111111111111111111111checking.......constructor classroompanel");
                     
                    view = new InformationPanel(account);
                    break;
                }
                default: {
                    view = new InitPanel(account);
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
