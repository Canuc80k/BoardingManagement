package controller.dashboard_controller.admin_dashboard_controller;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import constant.AvailableMonth;
import constant.Role;
import model.account.Account;
import model.payment.PaymentDatabase;
import view.dashboard.admin_dashboard.ManagePaymentJFrame;

public class ManagePaymentController {
    private javax.swing.JButton payButton;
    private javax.swing.JComboBox<String> payComboBox;
    private javax.swing.JButton refundButton;
    private javax.swing.JComboBox<String> refundComboBox;
    private Account account;
    private ManagePaymentJFrame frame;
    private int role;

    public ManagePaymentController(int role, ManagePaymentJFrame frame, Account account, JButton payButton, JComboBox<String> payComboBox, JButton refundButton, JComboBox<String> refundComboxBox) {
        this.role = role;
        this.frame = frame;
        this.account = account;
        this.payButton = payButton;
        this.payComboBox = payComboBox;
        this.refundButton = refundButton;
        this.refundComboBox = refundComboxBox;
    }

    public void setEvent() {
        if (role == Role.TEACHER) {
            payButton.setVisible(false);
            refundButton.setVisible(false);
            payComboBox.setVisible(false);
            refundComboBox.setVisible(false);
            return;
        }
        
        for (int i = 0; i < AvailableMonth.month.size(); i ++) {
            payComboBox.addItem(AvailableMonth.month.get(i));
            refundComboBox.addItem(AvailableMonth.month.get(i));
        }
        payButton.addActionListener(e -> {
            try {
                if (PaymentDatabase.getState(account.getID(), AvailableMonth.date.get(payComboBox.getSelectedIndex())) >= 2) {
                    JOptionPane.showMessageDialog(null, "You can't pay this month", "Mange Payment", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
            }

            Object[] choices = {"Yes", "No"};
            int choice = JOptionPane.showOptionDialog(null, "Are you sure", "Manage Payment", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices, "Yes");
            if (choice == 1) return;
            
            try {PaymentDatabase.add(account.getID(), AvailableMonth.date.get(payComboBox.getSelectedIndex()));}
            catch (Exception ex) {}

            try {PaymentDatabase.updateState(account.getID(), AvailableMonth.date.get(payComboBox.getSelectedIndex()), 2);}
            catch (Exception ex) {ex.printStackTrace();}
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Pay success", "Manage Payment", JOptionPane.INFORMATION_MESSAGE);
        });

        refundButton.addActionListener(e -> {
            try {
                if (PaymentDatabase.getState(account.getID(), AvailableMonth.date.get(refundComboBox.getSelectedIndex())) != 2) {
                    JOptionPane.showMessageDialog(null, "You can't refund this month", "Mange Payment", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
            }

            Object[] choices = {"Yes", "No"};
            int choice = JOptionPane.showOptionDialog(null, "Are you sure", "Manage Payment", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices, "Yes");
            if (choice == 1) return;

            try {PaymentDatabase.add(account.getID(), AvailableMonth.date.get(refundComboBox.getSelectedIndex()));}
            catch (Exception ex) {}

            try {PaymentDatabase.updateState(account.getID(), AvailableMonth.date.get(refundComboBox.getSelectedIndex()), 3);}
            catch (Exception ex) {ex.printStackTrace();}
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Refund success", "Manage Payment", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
