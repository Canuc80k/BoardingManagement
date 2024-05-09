package controller.dashboard_controller.pupil_dashboard_controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.absence.AbsenceDatabase;
import model.account.Account;
import view.dashboard.pupil_dashboard.AbsenceRegister;

public class AbsenceRegisterController {
    private final int ROW = 5;
    private final int COL = 7;    
    private final int CELL_WIDTH = 70;
    private final int CELL_HEIGHT = 70;
    private final int BLANK = 10;
    private final int TOP_MARGIN = 300;
    private final int LEFT_MARGIN = 200;

    private LocalDate now;
    private Account account;
    private JLabel changeMonthYearDataLabel;
    private JButton[][] calendarCell;
    private int currentMonth = -1, currentYear = -1;
    private JTextField monthChooserTextField, yearChooserTextField;
    
    public AbsenceRegisterController(AbsenceRegister rootPanel, Account account, JButton[][] calendarCell, JLabel changeMonthYearDataLabel, JTextField monthChooserTextField, JTextField yearChooserTextField) {
        this.account = account;
        this.changeMonthYearDataLabel = changeMonthYearDataLabel;
        this.calendarCell = calendarCell;
        this.monthChooserTextField = monthChooserTextField;
        this.yearChooserTextField = yearChooserTextField; 
    }

    public void loadCalendar() throws ClassNotFoundException, SQLException {
        if (currentMonth == -1) {
            now = LocalDate.now();
            currentMonth = now.getMonthValue();
            currentYear = now.getYear();
        }
        for (int i = 1; i <= ROW; i ++)
            for (int j = 1; j <= COL; j ++) {
                int day = getDayByIndex(i, j);
                String name = (day != -1) ? String.valueOf(day) : "";
                calendarCell[i][j].setText(name);
                calendarCell[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                calendarCell[i][j].setBounds(
                    LEFT_MARGIN + BLANK * j + CELL_WIDTH * (j - 1), 
                    TOP_MARGIN + BLANK * i + CELL_HEIGHT * (i - 1),
                    CELL_WIDTH,
                    CELL_HEIGHT
                );
                if (day != -1) {
                    if (AbsenceDatabase.find(account.getID(), LocalDate.of(currentYear, currentMonth, day)))
                    calendarCell[i][j].setBackground(Color.RED);
                    else calendarCell[i][j].setBackground(Color.GREEN);
                }
            }
    }

    public void setEvent() {
        setEventChangeMonthYear();
        setEventForCalender();
    }

    private void setEventChangeMonthYear() {
        changeMonthYearDataLabel.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                try {
                    int newMonth, newYear;
                    newMonth = Integer.parseInt(monthChooserTextField.getText());
                    newYear = Integer.parseInt(yearChooserTextField.getText());
                    currentMonth = newMonth;
                    currentYear = newYear;
                    loadCalendar();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Month and year not valid", "Regist Absence", JOptionPane.ERROR_MESSAGE);
                }
            }
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
        });
    }
    
    private void setEventForCalender() {
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

    
    private int getDayByIndex(int i, int j) {
        int indexInArray = (i - 1) * 7 + j;
        int res = indexInArray - getStartPosition() + 1;
        if (res < 1 || res > getDaysInMonth()) return -1;
        return res;
    }
    private int getDaysInMonth() {return LocalDate.of(currentYear, currentMonth, 1).lengthOfMonth();}
    private int getStartPosition() {return LocalDate.of(currentYear, currentMonth, 1).getDayOfWeek().getValue();}

}