package view.dashboard.pupil_dashboard;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.dashboard_controller.pupil_dashboard_controller.AbsenceRegisterController;
import model.account.Account;

public class AbsenceRegister extends javax.swing.JPanel {
    private final int ROW = 5;
    private final int COL = 7;

    private Account account;
    private AbsenceRegisterController controller;
    private JLabel currentMonthInformationLabel;
    private JLabel chooseAnotherMonthLabel;
    private JLabel minusSignLabel;
    private JTextField monthChooserTextField, yearChooserTextField;
    private JLabel changeMonthYearDataLabel;
    private JButton[][] calendarCell = new JButton[ROW + 1][COL + 1];
    
    public AbsenceRegister(Account account) throws SQLException, ClassNotFoundException {
        this.account = account;
        init();
        controller = new AbsenceRegisterController(this, this.account, calendarCell, changeMonthYearDataLabel, monthChooserTextField, yearChooserTextField);
        controller.loadCalendar();
        controller.setEvent();
    }
    
    private void init() throws ClassNotFoundException, SQLException {
        setLayout(null);
        String currentMonth = Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        currentMonthInformationLabel = new JLabel("Absence register in: " + currentMonth + " " + LocalDate.now().getYear());
        currentMonthInformationLabel.setFont(new Font("Arial", Font.BOLD, 30));
        currentMonthInformationLabel.setBounds(50, 0, 500, 100);
        this.add(currentMonthInformationLabel);
        chooseAnotherMonthLabel = new JLabel("Or you can choose another month/year");
        chooseAnotherMonthLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        chooseAnotherMonthLabel.setBounds(50, 50, 500, 100);
        this.add(chooseAnotherMonthLabel);
        monthChooserTextField = new JTextField();
        monthChooserTextField.setFont(new Font("Arial", Font.BOLD, 14));
        monthChooserTextField.setBounds(412, 90, 20, 20);
        this.add(monthChooserTextField);
        minusSignLabel = new JLabel("/");
        minusSignLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        minusSignLabel.setBounds(440, 90, 10, 20);
        this.add(minusSignLabel);
        yearChooserTextField = new JTextField();
        yearChooserTextField.setFont(new Font("Arial", Font.BOLD, 14));
        yearChooserTextField.setBounds(455, 90, 50, 20);
        this.add(yearChooserTextField);
        changeMonthYearDataLabel = new JLabel();
        try {
            BufferedImage img = ImageIO.read(getClass().getResource("/images/reload_icon.png"));
            changeMonthYearDataLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {e.printStackTrace();}
        changeMonthYearDataLabel.setBounds(514, 78, 40, 40);
        this.add(changeMonthYearDataLabel);
        for (int i = 1; i <= ROW; i ++)
            for (int j = 1; j <= COL; j ++) {
                calendarCell[i][j] = new JButton();
                this.add(calendarCell[i][j]);
            }
    }
}
