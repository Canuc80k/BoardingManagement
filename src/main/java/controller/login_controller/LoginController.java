package controller.login_controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.account.Account;
import service.AccountService;
import service.AccountServiceImpl;
import view.dashboard.AdminDashboard;

public class LoginController {
    private JFrame loginFrame;
    private JButton submitButton;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JLabel messageLabel;
    private AccountService accountService = null;

    public LoginController(JFrame loginFrame, JButton submitButton, JTextField usernameTextField, JPasswordField passwordTextField,JLabel messageLabel) {
        this.loginFrame = loginFrame;
        this.submitButton = submitButton;
        this.usernameTextField = usernameTextField;
        this.passwordTextField = passwordTextField;
        this.messageLabel = messageLabel;
        accountService = new AccountServiceImpl();
    }

    public void setEvent() {
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (usernameTextField.getText().length() == 0 || passwordTextField.getPassword() == null) {
                    messageLabel.setText("Please fill required informations");
                    return;
                } 

                Account account = accountService.Login(usernameTextField.getText(), passwordTextField.getText());
                if (account == null) {
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
