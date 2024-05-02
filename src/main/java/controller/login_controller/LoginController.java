package controller.login_controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.account.Account;
import view.dashboard.admin_dashboard.AdminDashboard;

public class LoginController {
    private JFrame loginFrame;
    private JButton submitButton;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JLabel messageLabel;

    public LoginController(JFrame loginFrame, JButton submitButton, JTextField usernameTextField, JPasswordField passwordTextField,JLabel messageLabel) {
        this.loginFrame = loginFrame;
        this.submitButton = submitButton;
        this.usernameTextField = usernameTextField;
        this.passwordTextField = passwordTextField;
        this.messageLabel = messageLabel;
    }

    public void setEvent() {
        
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (usernameTextField.getText().length() == 0 || passwordTextField.getPassword() == null) {
                    messageLabel.setText("Please fill required informations");
                    return;
                } 

                try {
                    boolean loginByID = false;
                    if (usernameTextField.getText().charAt(0) >= '0' 
                        && usernameTextField.getText().charAt(0) <= '9') 
                            loginByID = true;

                    boolean loginSuccess = Account.login(
                        usernameTextField.getText(), 
                        String.valueOf(passwordTextField.getPassword()), 
                        loginByID
                    );
                    if (!loginSuccess) {
                        messageLabel.setText("Username or password is wrong");
                        return;
                    }
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                    messageLabel.setText("Username or password is wrong");
                    return;
                }

                loginFrame.dispose();
                AdminDashboard frame = new AdminDashboard();
                frame.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                submitButton.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                submitButton.setBackground(new Color(100, 221, 23));
            }
        });
    }
}
