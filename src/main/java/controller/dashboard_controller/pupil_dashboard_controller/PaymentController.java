package controller.dashboard_controller.pupil_dashboard_controller;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import constant.AvailableMonth;
import constant.PaymentState;
import model.account.Account;
import model.boardingpay.BoardingPay;
import model.payment.Payment;
import model.payment.PaymentDatabase;

public class PaymentController {
    private final int CELL_WIDTH = 100;
    private final int CELL_HEIGHT = 100;
    private final int BLANK = 20;
    private int TOP_MARGIN = 600;
    private final int LEFT_MARGIN = 50;
    private Account account;
    private JLabel stateLabel, needToPayLabel, hasPaidLabel, payBackLabel, equationLabel, moreInformationLabel, largeMoreInformationLabel;
    JComboBox<String> monthChooser;
    JButton[] statusButton;

    public PaymentController(Account account, JLabel stateLabel, JLabel needToPayLabel, JLabel hasPaidLabel, JLabel payBackLabel, JComboBox<String> monthChooser, JButton[] statusButton, JLabel equationLabel, JLabel moreInformationLabel, JLabel largeMoreInformationLabel) throws ClassNotFoundException, SQLException {
        this.account = account;
        this.stateLabel = stateLabel;        
        this.needToPayLabel = needToPayLabel;
        this.hasPaidLabel = hasPaidLabel;
        this.payBackLabel = payBackLabel;
        this.monthChooser = monthChooser;
        this.statusButton = statusButton;
        this.equationLabel = equationLabel;
        this.moreInformationLabel = moreInformationLabel;
        this.largeMoreInformationLabel = largeMoreInformationLabel;
    }

    private void setupStatusButton() throws ClassNotFoundException, SQLException {
        for (int i = 1; i <= 9; i ++) {
            statusButton[i].setText(AvailableMonth.date.get(i - 1).getMonthValue() + "/" + AvailableMonth.date.get(i - 1).getYear());
            statusButton[i].setFont(new Font("Arial", Font.BOLD, 14));
            statusButton[i].setBounds(
                LEFT_MARGIN + BLANK * i + CELL_WIDTH * (i - 1), 
                TOP_MARGIN,
                CELL_WIDTH,
                CELL_HEIGHT
                );
                if (true) {
                    // AvailableMonth.date.get(i);
                    // System.out.println(AvailableMonth.date.get(i - 1));
                }
                statusButton[i].setBackground(PaymentState.getColor(PaymentDatabase.getState(account.getID(), AvailableMonth.date.get(i - 1))));
                statusButton[i].addActionListener(e -> {
                    for (int j = 1; j <= 9; j ++) {
                        if (statusButton[j].equals(e.getSource())) {
                            monthChooser.setSelectedIndex(j - 1);
                    }
                }
            }); 
        }
    }

    public void setEvent() throws ClassNotFoundException, SQLException {
        setPaymentDataFromMonthIndex(monthChooser.getSelectedIndex());
        monthChooser.addActionListener(e -> {
            try {
                setPaymentDataFromMonthIndex(monthChooser.getSelectedIndex());
            } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
            }
        });
        setPaymentDataFromMonthIndex(monthChooser.getSelectedIndex());
        setupStatusButton();
    }

    private void setPaymentDataFromMonthIndex(int idx) throws ClassNotFoundException, SQLException  {
        Payment payment = PaymentDatabase.get(account.getID(), AvailableMonth.date.get(idx));
        if (payment.getState() == PaymentState.DAY_HASNT_COME_YET) {
            stateLabel.setText("The boarding fee payment date for the month you selected has not yet arrived");
            hasPaidLabel.setText("");
            needToPayLabel.setText("");
            payBackLabel.setText("");
            equationLabel.setText("");
            moreInformationLabel.setText("");
            largeMoreInformationLabel.setText("");
        }
        if (payment.getState() == PaymentState.HASNT_PAY) {
            stateLabel.setText("You have not paid for the month you selected");
            hasPaidLabel.setText("You has paid: " + "0$");
            needToPayLabel.setText("You need to pay: " + payment.getTotalPay() + "$");
            payBackLabel.setText("");
            equationLabel.setText("");
            moreInformationLabel.setText("");
            largeMoreInformationLabel.setText("");
        }
        if (payment.getState() == PaymentState.HAS_PAY_HASNT_PAYBACK) {
            stateLabel.setText("You have paid for the month you selected but school has not returned the excess money");
            hasPaidLabel.setText("You has paid: " + payment.getTotalPay() + "$");
            needToPayLabel.setText("Note: The boarding fee for holidays or absence registered days will be refunded later");
            payBackLabel.setText("");
            equationLabel.setText("");
            moreInformationLabel.setText("");
            largeMoreInformationLabel.setText("");
        }        
        if (payment.getState() == PaymentState.HAS_PAYBACK) {
            stateLabel.setText("You have paid the fee for the month you selected and school has returned the excess amount");
            hasPaidLabel.setText("You has paid: " + payment.getTotalPay() + "$");
            needToPayLabel.setText("Actual boarding fee: " + payment.getReceived() + "$");
            payBackLabel.setText("Has Repayed: " + payment.getPayback() + "$");
            equationLabel.setText("The formula for calculating boarding fees is: number of boarding days * boarding fee per day + cleaning fee");
            moreInformationLabel.setText("In this month: " + payment.getBoardingDay() + " days * " + BoardingPay.boardingFee + "$ + " + BoardingPay.cleaningFee + "$ = " + payment.getReceived() + "$");
            largeMoreInformationLabel.setText("More Information:");
        }
    }
}
