package controller.dashboard_controller.pupil_dashboard_controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.absence.AbsenceDatabase;
import model.account.Account;

public class AbsenceRegisterController {
    private int ROW = 6;
    private int COL = 7;    
    private final int CELL_WIDTH = 80;
    private final int CELL_HEIGHT = 80;
    private final int BLANK = 10;
    private int TOP_MARGIN = 125;
    private final int LEFT_MARGIN = 50;
    private final Color ABSENCE_DAY_COLOR = Color.decode("#FF0000");
    private final Color BOARDING_DAY_COLOR = Color.decode("#00FF00");
    private final Color OUT_OF_RANGE_DAY_COLOR = Color.decode("#000000");
    private final Color DAY_OF_WEEK_COLOR = Color.decode("#0000FF");
    private final Color OFF_DAY_COLOR = Color.decode("#808080");
    private final String[] DAY_IN_WEEK = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    private LocalDate now;
    private Account account;
    private JLabel currentMonthInformationLabel;
    private JLabel changeMonthYearDataLabel;
    private JButton[][] calendarCell;
    private int currentMonth = -1, currentYear = -1;
    private JTextField monthChooserTextField, yearChooserTextField;
    private JPanel boardingDayDescriptionPanel, absentDayDescriptionPanel, offDayDescriptionPanel;
    
    public AbsenceRegisterController(Account account, JButton[][] calendarCell, JLabel changeMonthYearDataLabel, JTextField monthChooserTextField, JTextField yearChooserTextField, JLabel currentMonthInformationLabel, JPanel boardingDayDescriptionPanel, JPanel absentDayDescriptionPanel, JPanel offDayDescriptionPanel) {
        this.account = account;
        this.boardingDayDescriptionPanel = boardingDayDescriptionPanel;
        this.absentDayDescriptionPanel = absentDayDescriptionPanel;
        this.offDayDescriptionPanel = offDayDescriptionPanel;
        this.currentMonthInformationLabel = currentMonthInformationLabel;
        this.changeMonthYearDataLabel = changeMonthYearDataLabel;
        this.calendarCell = calendarCell;
        this.monthChooserTextField = monthChooserTextField;
        this.yearChooserTextField = yearChooserTextField; 
    }

    public void loadCalendar() throws ClassNotFoundException, SQLException {
        boardingDayDescriptionPanel.setBackground(BOARDING_DAY_COLOR);
        absentDayDescriptionPanel.setBackground(ABSENCE_DAY_COLOR);
        offDayDescriptionPanel.setBackground(OFF_DAY_COLOR);
        if (currentMonth == -1) {
            now = LocalDate.now();
            currentMonth = now.getMonthValue();
            currentYear = now.getYear();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(currentYear, currentMonth - 1, 1);
        String currentMonthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        currentMonthInformationLabel.setText("Absence register in: " + currentMonthName + " " + currentYear);
        
        ROW = 6; TOP_MARGIN = 125;
        int lastRow = -1;
        for (int i = 1; i <= ROW; i ++) {
            if (lastRow != -1) break; 
            for (int j = 1; j <= COL; j ++) {
                if (getDayByIndex(i, j) == getDaysInMonth()) {
                    lastRow = i;
                    break;
                }
            }
        }
        if (lastRow == 5) {
            ROW = 5; 
            TOP_MARGIN = 200;
            for (int i = 1; i <= COL; i ++) calendarCell[6][i].setBounds(0, 0, 0, 0);
        } 

        for (int j = 1; j <= COL; j ++) {
            calendarCell[0][j].setText(DAY_IN_WEEK[j - 1]);
            calendarCell[0][j].setFont(new Font("Arial", Font.BOLD, 14));
            calendarCell[0][j].setBounds(
                LEFT_MARGIN + BLANK * j + CELL_WIDTH * (j - 1), 
                TOP_MARGIN,
                CELL_WIDTH,
                CELL_HEIGHT
            );
            calendarCell[0][j].setBackground(DAY_OF_WEEK_COLOR);
        }
        for (int i = 1; i <= ROW; i ++) {
            for (int j = 1; j <= COL; j ++) {
                int day = getDayByIndex(i, j);
                String name = (day != -1) ? String.valueOf(day) : "";
                calendarCell[i][j].setText(name);
                calendarCell[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                calendarCell[i][j].setBounds(
                    LEFT_MARGIN + BLANK * j + CELL_WIDTH * (j - 1), 
                    TOP_MARGIN + BLANK * i + CELL_HEIGHT * i,
                    CELL_WIDTH,
                    CELL_HEIGHT
                );
                if (day != -1) {
                    if (LocalDate.of(currentYear, currentMonth, day).getDayOfWeek().getValue() >= 6) {
                        calendarCell[i][j].setBackground(OFF_DAY_COLOR);  
                    } else if (AbsenceDatabase.find(account.getID(), LocalDate.of(currentYear, currentMonth, day)))
                        calendarCell[i][j].setBackground(ABSENCE_DAY_COLOR);
                    else calendarCell[i][j].setBackground(BOARDING_DAY_COLOR);
                } else calendarCell[i][j].setBackground(OUT_OF_RANGE_DAY_COLOR);
            }
        }
        setEventForCalender();
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
                for (ActionListener act : calendarCell[i][j].getActionListeners())
                    calendarCell[i][j].removeActionListener(act);
                    
                    if (calendarCell[i][j].getText() == "") continue;
                int day = Integer.parseInt(calendarCell[i][j].getText());
                if (LocalDate.of(currentYear, currentMonth, day).getDayOfWeek().getValue() >= 6) continue;   
                calendarCell[i][j].addActionListener(event -> {
                    boolean isRegistAbsence = true;

                    String message = "Do you want to regist absence in day " + String.valueOf(day) + " ?";
                    if (((JButton) event.getSource()).getBackground() == ABSENCE_DAY_COLOR) {
                        message = "Do you want to undo regist absence in day " + String.valueOf(day) + " ?";
                        isRegistAbsence = false;
                    }
                    Object[] choices = {"Yes", "No"};
                    int choice = JOptionPane.showOptionDialog(null, message, "Regist Absence", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices, "Yes");
                    if (choice == 0) {
                        ((JButton) event.getSource()).setBackground(isRegistAbsence ? ABSENCE_DAY_COLOR : BOARDING_DAY_COLOR);
                        try {
                            if (isRegistAbsence) AbsenceDatabase.registAbsence(account.getID(), LocalDate.of(currentYear, currentMonth, day));
                            else AbsenceDatabase.undoRegistAbsence(account.getID(), LocalDate.of(currentYear, currentMonth, day));
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