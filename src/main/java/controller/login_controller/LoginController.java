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

import constant.Role;
import model.account.Account;
import view.dashboard.admin_dashboard.AdminDashboard;
import view.dashboard.pupil_dashboard.PupilDashboard;
import view.dashboard.teacher_dashboard.TeacherDashboard;

public class LoginController {
    private JFrame loginFrame;
    private JButton submitButton;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JLabel messageLabel;

    public LoginController(JFrame loginFrame, JButton submitButton, JTextField usernameTextField,
            JPasswordField passwordTextField, JLabel messageLabel) {
        this.loginFrame = loginFrame;
        this.submitButton = submitButton;
        this.usernameTextField = usernameTextField;
        this.passwordTextField = passwordTextField;
        this.messageLabel = messageLabel;
    }

    private boolean validateLoginInformation() {
        if (usernameTextField.getText().length() == 0 || passwordTextField.getPassword() == null) {
            messageLabel.setText("Please fill required informations");
            return false;
        }
        return true;
    }

    public void login() {
        if (!validateLoginInformation()) return;
                
        Account account = null;
        boolean isLoginByID = false;
        try {
            if (usernameTextField.getText().charAt(0) >= '0' && usernameTextField.getText().charAt(0) <= '9')
                isLoginByID = true;
            account = Account.login(
                    usernameTextField.getText(),
                    String.valueOf(passwordTextField.getPassword()),
                    isLoginByID);
            if (account == null) {
                messageLabel.setText("Username or password is wrong");
                return;
            }
        } catch (ClassNotFoundException | SQLException e1) {e1.printStackTrace();}
        
        loginFrame.dispose();
        if (account.getRole() == Role.ADMIN) {
            AdminDashboard frame = new AdminDashboard(account);
            frame.setVisible(true);
        }
        if (account.getRole() == Role.TEACHER) {
            TeacherDashboard frame = new TeacherDashboard(account);
            frame.setVisible(true);
        }
        if (account.getRole() == Role.PUPIL) {
            PupilDashboard frame = new PupilDashboard(account);
            frame.setVisible(true);
        }
    }

    public void setEvent() {
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                login();
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
