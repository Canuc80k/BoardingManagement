package controller.dashboard_controller.pupil_dashboard_controller;

import java.awt.Color;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.absence.AbsenceDatabase;
import model.account.Account;

public class AbsenceRegisterController {
    private final int ROW = 5;
    private final int COL = 7;
    private Account account;
    private JButton[][] calendarCell = new JButton[ROW + 1][COL + 1];
    
    public AbsenceRegisterController(Account account, JButton[][] calendarCell) {
        this.account = account;
        for (int i = 1; i <= ROW; i ++)
            for (int j = 1; j <= COL; j ++)
                this.calendarCell[i][j] = calendarCell[i][j];
    }

    public void setEvent() {
        for (int i = 1; i <= ROW; i ++)
            for (int j = 1; j <= COL; j ++) {
                if (calendarCell[i][j].getText() == "") continue;
                int day = Integer.parseInt(calendarCell[i][j].getText());
                calendarCell[i][j].addActionListener(event -> {
                    boolean isRegistAbsence = true;

                    String message = "Do you want to regist absence in day " + String.valueOf(day) + " ?";
                    if (((JButton) event.getSource()).getBackground() == Color.RED) {
                        message = "Do you want to undo regist absence in day " + String.valueOf(day) + " ?";
                        isRegistAbsence = false;
                    }
                    Object[] choices = {"Yes", "No"};
                    int choice = JOptionPane.showOptionDialog(null, message, "Regist Absence", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices, "Yes");
                    if (choice == 0) {
                        ((JButton) event.getSource()).setBackground(isRegistAbsence ? Color.RED : Color.GREEN);
                        try {
                            if (isRegistAbsence) AbsenceDatabase.registAbsence(account.getID(), LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), day));
                            else AbsenceDatabase.undoRegistAbsence(account.getID(), LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), day));
                        } catch(Exception e) {e.printStackTrace();}
                    }             
                });
            }
    }
}