/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.dashboard_controller.admin_dashboard_controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.account.Account;
import model.people.People;

public class EditAccountController<T extends People> {
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JPasswordField confirmTextField;
    private JLabel confirmLabel;
    private Account account;

    public EditAccountController(JTextField usernameTextField, JPasswordField passwordTextField, JPasswordField confirmTextField, JLabel confirmLabel, T person, Account account) {
        this.usernameTextField = usernameTextField;
        this.passwordTextField = passwordTextField;
        this.confirmTextField = confirmTextField;
        this.confirmLabel = confirmLabel;
        this.account = account;
    }

    public void setView() {
        usernameTextField.setText(account.getUsername());
        passwordTextField.setText(account.getPassword());
        confirmTextField.setText(account.getPassword());
    }

    public static int update(Account account) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "UPDATE loginaccount SET Password = ?, Username = ? WHERE ID = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, account.getPassword());
           
            ps.setString(2, account.getUsername());
            ps.setString(3, account.getID());

            int rowsUpdated = ps.executeUpdate(); // Execute the update query

            ps.close();
            con.close();

            return rowsUpdated; // Return the number of rows updated
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setEvent() {
        confirmLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicking");
                account.setUsername(usernameTextField.getText());
                account.setPassword(String.valueOf(passwordTextField.getPassword()));
                String checkPassword = String.valueOf(confirmTextField.getPassword());
                //person.setDoB(new java.sql.Date(dobDateChooser.getDate().getTime()));
                
                int check = -1;
                if(!account.getPassword().equals(checkPassword))System.out.println("2 password is different");
                else {
                check = update(account);
                if (check > 0 ) {
                    System.out.println("update success");
                } else {
                    System.out.println("update fail");
                }
                }
            }

        });
    }
}
