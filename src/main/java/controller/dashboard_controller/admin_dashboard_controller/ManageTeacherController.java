package controller.dashboard_controller.admin_dashboard_controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import java.util.List;

import model.account.Account;
import model.account.AccountDatabase;
import model.classroom.Classroom;
import model.classroom.ClassroomDatabase;
import model.people.teacher.Teacher;
import model.people.teacher.TeacherDatabase;

public class ManageTeacherController {

    private JButton saveButton;
    private JButton deleteButton;
    private JTextField teacherIDTextField;
    private JTextField nameTextField;
    private JDateChooser dobDayChooser;
    private JTextField phoneTextField;
    private JTextField addressTextField;
    private JTextField classIDTextField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> classIDComboBox;
    private Teacher teacher = null;
    private JLabel messageLabel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private Account account = null;

    public ManageTeacherController(JButton btnSave, JButton btnDelete, JTextField jtfTeacherID, JTextField jtfName,
            JDateChooser jdcNgaySinh, JComboBox<String> jcbGender, JTextField jtfPhone, JTextField jtfAddress, JComboBox<String> jcbClassID, JTextField jtfUsername, JTextField jtfPassword, Teacher teacher, JLabel jlbMsg) {
        this.saveButton = btnSave;
        this.deleteButton = btnDelete;
        this.teacherIDTextField = jtfTeacherID;
        this.nameTextField = jtfName;
        this.dobDayChooser = jdcNgaySinh;
        this.genderComboBox = jcbGender;
        this.phoneTextField = jtfPhone;
        this.addressTextField = jtfAddress;
        this.classIDComboBox = jcbClassID;
        this.usernameTextField = jtfUsername;
        this.passwordTextField = jtfPassword;
        this.messageLabel = jlbMsg;
    }

    public void setView(Teacher teacher, String editOrAdd) {
        try {
            this.teacher = teacher;
            String teacherID = teacher.getID();
            teacherIDTextField.setText(teacher.getID());
            nameTextField.setText(teacher.getName());
            phoneTextField.setText(teacher.getPhone());
            addressTextField.setText(teacher.getAddress());
            dobDayChooser.setDate(teacher.getDoB());
            classIDComboBox.removeAllItems();
            List<Classroom> classes = ClassroomDatabase.getAllClassrooms("Select * from classroom");
            for (Classroom classroom : classes) {
                classIDComboBox.addItem(classroom.getClassID());
            }
            classIDComboBox.setSelectedItem(teacher.getClassID());
            genderComboBox.setSelectedIndex(teacher.getGender());
            if (editOrAdd.equals("add")) {
                account = new Account("", "", "", 2);
                usernameTextField.setText("");
                passwordTextField.setText("");
            } else {
                account = AccountDatabase.getAccountByID(teacherID);
                System.out.println(account.getID() + " " + account.getUsername() + " " + account.getPassword());
                usernameTextField.setText(account.getUsername());
                passwordTextField.setText(account.getPassword());
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ManageTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setEvent(String editOrAdd) {
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (nameTextField.getText().length() == 0 || dobDayChooser.getDate() == null) {
                    messageLabel.setText("Please enter fill compulsory informations");
                } else {
                    try {
                        account = new Account(teacherIDTextField.getText(), usernameTextField.getText(), passwordTextField.getText(), 2);
                        teacher = new Teacher(teacherIDTextField.getText(), nameTextField.getText(), new java.sql.Date(dobDayChooser.getDate().getTime()),
                                genderComboBox.getSelectedIndex(), phoneTextField.getText(), addressTextField.getText(), classIDComboBox.getSelectedItem().toString());
                        int checkTeacher = -1;
                        int checkAccount = -1;
                        if (editOrAdd.equals("add")) {
                            if (AccountDatabase.getAccountByID(account.getID()) != null) {
                                messageLabel.setText("ID invalid");
                            } else {
                                checkAccount = AccountDatabase.create(account);
                                checkTeacher = TeacherDatabase.create(teacher);
                            }
                        } else {
                            checkAccount = AccountDatabase.update(account);
                            checkTeacher = TeacherDatabase.update(teacher);
                        }
                        if (checkTeacher > 0 && checkAccount > 0) {
                            messageLabel.setText("Update Success");
                        } else {
                            if(editOrAdd.equals("add"))AccountDatabase.delete(account);
                            messageLabel.setText("Update Fail");
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                saveButton.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                saveButton.setBackground(new Color(100, 221, 23));
            }
        });
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (nameTextField.getText().length() == 0 || dobDayChooser.getDate() == null) {
                    messageLabel.setText("Can't delete null values");
                } else {
                    try {
                        teacher = new Teacher(teacherIDTextField.getText(), nameTextField.getText(), new java.sql.Date(dobDayChooser.getDate().getTime()),
                                genderComboBox.getSelectedIndex(), phoneTextField.getText(), addressTextField.getText(), classIDComboBox.getSelectedItem().toString());

                        account = new Account(teacherIDTextField.getText(), usernameTextField.getText(), passwordTextField.getText(), 2);
                        int checkTeacher = TeacherDatabase.delete(teacher);
                        int checkAccount = AccountDatabase.delete(account);

                        if (checkTeacher > 0 && checkAccount > 0) {
                            messageLabel.setText("Delete Success");
                        } else {
                            messageLabel.setText("Delete Fail");
                        }
                    } catch (ClassNotFoundException ex) {
                        messageLabel.setText("Exception");
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Button.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // saveButton.setBackground(new Color(100, 221, 23));
            }
        });
    }
}
