package controller.account_controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import model.account.Account;
import model.people.teacher.Teacher;
import service.AccountService;
import service.AccountServiceImpl;
import view.dashboard.AdminDashboard;

public class AccountController {

    private JFrame Login;
    private JButton btnSubmit;
    private JTextField jtfUsername;
    private JPasswordField jpfPassword;
    private JLabel jlbMsg;
    private AccountService accountService = null;
    public AccountController(JFrame Login, JButton btnSubmit, JTextField jtfUsername, JPasswordField jpfPassword,JLabel jlbMsg) {
        this.Login = Login;
        this.btnSubmit = btnSubmit;
        this.jtfUsername = jtfUsername;
        this.jpfPassword = jpfPassword;
        this.jlbMsg=jlbMsg;
        accountService=new AccountServiceImpl();
    }

    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jtfUsername.getText().length() == 0 || jpfPassword.getPassword() == null) {
                    jlbMsg.setText("Please enter fill compulsory informations");
                } else {
                    Account account=accountService.Login(jtfUsername.getText(), jpfPassword.getText());
                    if(account==null){
                        jlbMsg.setText("Wrong Information");
                    } else {
                        Login.dispose();
                        AdminDashboard frame=new AdminDashboard();
                        frame.setTitle("Boarding Management");
                        //frame.setExtendedState(JFrame);
                        frame.setVisible(true);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmit.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSubmit.setBackground(new Color(100, 221, 23));
            }

        });
    }
}
