package controller.dashboard_controller.pupil_dashboard_controller;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import constant.AvailableMonth;
import constant.PaymentState;
import model.account.Account;
import model.payment.PaymentDatabase;

public class PaymentController {
    private Account account;
    private JLabel stateLabel, needToPayLabel, hasPaidLabel, payBackLabel;
    JComboBox<String> monthChooser;

    public PaymentController(Account account, JLabel stateLabel, JLabel needToPayLabel, JLabel hasPaidLabel, JLabel payBackLabel, JComboBox<String> monthChooser) throws ClassNotFoundException, SQLException {
        this.account = account;
        this.stateLabel = stateLabel;        
        this.needToPayLabel = needToPayLabel;
        this.hasPaidLabel = hasPaidLabel;
        this.payBackLabel = payBackLabel;
        this.monthChooser = monthChooser;
        setEvent();
        getPaymentDataFromMonthIndex(monthChooser.getSelectedIndex());
    }

    private void setEvent() {
        monthChooser.addActionListener(e -> {
            try {
                getPaymentDataFromMonthIndex(monthChooser.getSelectedIndex());
            } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void getPaymentDataFromMonthIndex(int idx) throws ClassNotFoundException, SQLException {
        model.payment.Payment payment = PaymentDatabase.get(account.getID(), AvailableMonth.date.get(idx));
        if (payment.getState() == PaymentState.DAY_HASNT_COME_YET) {
            stateLabel.setText("The boarding fee payment date for the month you selected has not yet arrived");
            hasPaidLabel.setText("You has paid: " + "0$");
            needToPayLabel.setText("You need to pay: " + "Unknown");
            payBackLabel.setText("You will repayed: " + "Unknown");
        }
        if (payment.getState() == PaymentState.HASNT_PAY) {
            stateLabel.setText("You have not paid for the month you selected");
            hasPaidLabel.setText("You has paid: " + "0$");
            needToPayLabel.setText("You need to pay: " + payment.getTotalPay() + "$");
            payBackLabel.setText("You will repayed: " + payment.getPayback() + "$");
        }
        if (payment.getState() == PaymentState.HAS_PAY_HASNT_PAYBACK) {
            stateLabel.setText("You have paid for the month you selected but school has not returned the excess money");
            hasPaidLabel.setText("You has paid: " + payment.getTotalPay() + "$");
            needToPayLabel.setText("Actual boarding fee: " + payment.getReceived() + "$");
            payBackLabel.setText("You will repayed: " + payment.getPayback() + "$");
        }        
        if (payment.getState() == PaymentState.HAS_PAYBACK) {
            stateLabel.setText("You have paid the fee for the month you selected and school has returned the excess amount");
            hasPaidLabel.setText("You has paid: " + payment.getTotalPay() + "$");
            needToPayLabel.setText("Actual boarding fee: " + payment.getReceived() + "$");
            payBackLabel.setText("Has Repayed: " + payment.getPayback() + "$");
        }
    }
}
