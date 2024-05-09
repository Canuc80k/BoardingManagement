package view.dashboard.pupil_dashboard;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;

import controller.dashboard_controller.pupil_dashboard_controller.AbsenceRegisterController;
import model.absence.AbsenceDatabase;
import model.account.Account;

public class AbsenceRegister extends javax.swing.JPanel {
    private final int ROW = 5;
    private final int COL = 7;
    private final int CELL_WIDTH = 70;
    private final int CELL_HEIGHT = 70;
    private final int BLANK = 10;
    private final int TOP_MARGIN = 300;
    private final int LEFT_MARGIN = 200;

    private LocalDate date;
    private Account account;
    private AbsenceRegisterController controller;
    private JLabel currentMonthInformationLabel;
    private JButton[][] calendarCell = new JButton[ROW + 1][COL + 1];

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        PupilDashboard p = new PupilDashboard(
            new Account("1111", "a", "a", 3)
        );
        p.setVisible(true);
    } 
    
    public AbsenceRegister(Account account) throws SQLException, ClassNotFoundException {
        this.account = account;
        init();
        controller = new AbsenceRegisterController(account, calendarCell);
        controller.setEvent();
    }

    private void init() throws ClassNotFoundException, SQLException {
        date = LocalDate.now();
        String currentMonth = Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        setLayout(null);
        currentMonthInformationLabel = new JLabel("Absence register in: " + currentMonth + " " + date.getYear());
        currentMonthInformationLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        currentMonthInformationLabel.setBounds(100, 100, 500, 100);
        this.add(currentMonthInformationLabel);
        
        for (int i = 1; i <= ROW; i ++)
            for (int j = 1; j <= COL; j ++) {
                int day = getDayByIndex(i, j);
                String name = (day != -1) ? String.valueOf(day) : "";
                calendarCell[i][j] = new JButton(name);
                calendarCell[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                calendarCell[i][j].setBounds(
                    LEFT_MARGIN + BLANK * j + CELL_WIDTH * (j - 1), 
                    TOP_MARGIN + BLANK * i + CELL_HEIGHT * (i - 1),
                    CELL_WIDTH,
                    CELL_HEIGHT
                );
                if (day != -1) {
                    if (AbsenceDatabase.find(account.getID(), LocalDate.of(date.getYear(), date.getMonthValue(), day)))
                        calendarCell[i][j].setBackground(Color.RED);
                    else calendarCell[i][j].setBackground(Color.GREEN);
                }
                this.add(calendarCell[i][j]);
            }
    }

    private int getDayByIndex(int i, int j) {
        int indexInArray = (i - 1) * 7 + j;
        int res = indexInArray - getStartPosition() + 1;
        if (res < 1 || res > getDaysInMonth()) return -1;
        return res;
    }
    private int getDaysInMonth() {return LocalDate.now().lengthOfMonth();}
    private int getStartPosition() {return LocalDate.now().withDayOfMonth(1).getDayOfWeek().getValue();}
}
