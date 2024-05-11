/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.dashboard_controller.admin_dashboard_controller;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.account.Account;
import model.account.AccountDatabase;
import model.people.People;
import model.people.admin.Admin;
import model.people.admin.AdminDatabase;
import model.people.pupil.Pupil;
import model.people.pupil.PupilDatabase;
import model.people.teacher.Teacher;
import model.people.teacher.TeacherDatabase;

/**
 *
 * @author huant
 */
public class EditUserController<T extends People> {

    private JTextField idTextField;
    private JTextField nameTextField;
    private JDateChooser dobDateChooser;
    private JTextField phoneTextField;
    private JTextField addressTextField;
    private JButton confirmButton;
    private T person;
    private static Account account;

    public EditUserController(JTextField idTextField, JTextField nameTextField, JDateChooser dobDateChooser, JTextField phoneTextField, JTextField addressTextField, JButton confirmButton, T person, Account account) {
        this.idTextField = idTextField;
        this.nameTextField = nameTextField;
        this.dobDateChooser = dobDateChooser;
        this.phoneTextField = phoneTextField;
        this.addressTextField = addressTextField;
        this.confirmButton = confirmButton;
        this.person = person;
        EditUserController.account = account;
    }

    public void setView() {
        idTextField.setText(person.getID());
        nameTextField.setText(person.getName());
        dobDateChooser.setDate(person.getDoB());
        phoneTextField.setText(person.getPhone());
        addressTextField.setText(person.getAddress());
    }

    public static int update(People person) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
            String type = "admin";
            if (account.getRole() == 2) {
                type = "teacher";
            } else if (account.getRole() == 3) {
                type = "pupil";
            }
            String query = "UPDATE " + type + " SET name=?, dateofbirth=?, phonenumber=?, address=? WHERE ID=?";
            System.out.println(query);
            PreparedStatement pstmt = con.prepareStatement(query);

            System.out.println("Updating pupil Name: " + person.getName());
            System.out.println("Date of Birth: " + person.getDoB());
//            System.out.println("Gender: " + pupil.getGender());
//            System.out.println("Class ID: " + pupil.getClassID());
//            System.out.println("Parent Name: " + pupil.getParentName());
            System.out.println("Phone: " + person.getPhone());
            System.out.println("Address: " + person.getAddress());
//            System.out.println("Boarding Room: " + pupil.getBoardingroom());
            System.out.println("ID: " + person.getID());
            pstmt.setString(1, person.getName());
            pstmt.setDate(2, new java.sql.Date(person.getDoB().getTime()));
            // pstmt.setInt(3, person.getGender());
            pstmt.setString(3, person.getPhone());
            pstmt.setString(4, person.getAddress());
            pstmt.setString(5, person.getID());

            int rowsUpdated = pstmt.executeUpdate(); // Execute the update query

            pstmt.close();
            con.close();

            return rowsUpdated; // Return the number of rows updated
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setEvent() {
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicking");
                person.setName(nameTextField.getText());
                person.setAddress(addressTextField.getText());
                person.setPhone(phoneTextField.getText());
                person.setDoB(new java.sql.Date(dobDateChooser.getDate().getTime()));

                int check = -1;
                check = update(person);
                if (check > 0) {
                    System.out.println("update success");
                } else {
                    System.out.println("update fail");
                }

            }

        });
    }
}
