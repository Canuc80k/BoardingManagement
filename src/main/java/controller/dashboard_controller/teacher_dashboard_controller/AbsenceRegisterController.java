package controller.dashboard_controller.teacher_dashboard_controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.absence.AbsenceDatabase;
import view.dashboard.teacher_dashboard.ClassroomPanel;

public class AbsenceRegisterController {
    private JButton absenceRegisterButton;
    private ClassroomPanel classroomPanel;
    private JTable table;

    public AbsenceRegisterController(ClassroomPanel classroomPanel, JButton absenceRegisterButton) {
        this.absenceRegisterButton = absenceRegisterButton;
        this.classroomPanel = classroomPanel;
        table = this.classroomPanel.getController().getTable();
        table.removeMouseListener(table.getMouseListeners()[0]);
    }

    public void setEvent() {
        absenceRegisterButton.addActionListener(e -> {
            String id = table.getModel().getValueAt(table.getSelectedRows()[0], 0).toString();

            Object[] choices = {"Yes", "No"};
            int choice = JOptionPane.showOptionDialog(null, "Do you wanna register absence for student have id: " + id, "Absence Register", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices, "Yes");
            if (choice == 0) {
                if (LocalDateTime.now().getHour() >= 8) {
                    JOptionPane.showMessageDialog(null, "Cant register after 8am", "Absence Register", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
                    AbsenceDatabase.registAbsence(id, LocalDate.now());
                    JOptionPane.showMessageDialog(null, "Absence Register Success", "Absence Register", JOptionPane.INFORMATION_MESSAGE);
                } catch (ClassNotFoundException | SQLException e1) {
                    JOptionPane.showMessageDialog(null, "This student already register absence for today", "Absence Register", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
        });
    }
}
