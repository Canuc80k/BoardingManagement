/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import model.boardingroom.Boardingroom;
import model.boardingroom.BoardingroomDatabase;
import model.classroom.Classroom;
import model.classroom.ClassroomDatabase;
import model.people.pupil.Pupil;
import model.people.pupil.PupilDatabase;

public class ManagePupilController {

    private JButton saveButton;
    private JButton deleteButton;
    private JButton cancelButton;
    private JLabel messageLabel;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField parentNameTextField;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> classIDComboBox;
    private JComboBox<String> boardingRoomComboBox;
    private JTextField phoneTextField;
    private JTextField addressTextField;
    private JTextField classIDTextField;
    //private JTextField absentDayTextField;
    private JTextField boardingRoomTextField;
    private JDateChooser dobDayChooser;
    private Pupil pupil = null;
    private Account account = null;

    public ManagePupilController(JButton btnSave, JButton btnDelete, JButton btnCancel, JTextField jtfId, JTextField jtfName, JDateChooser jdcDob, JComboBox<String> jcbGender, JComboBox<String> jcbClassID,
            JTextField jtfParentName, JTextField jtfPhone, JTextField jtfAddress, JComboBox<String> jcbBoardingRoom,
            JTextField jtfUsername, JTextField jtfPassword, Pupil pupil, JLabel jlbMsg) {
        this.saveButton = btnSave;
        this.deleteButton = btnDelete;
        this.cancelButton = btnCancel;
        this.messageLabel = jlbMsg;
        this.idTextField = jtfId;
        this.nameTextField = jtfName;
        this.genderComboBox = jcbGender;
        this.parentNameTextField = jtfParentName;
        this.usernameTextField = jtfUsername;
        this.passwordTextField = jtfPassword;
        this.phoneTextField = jtfPhone;
        this.addressTextField = jtfAddress;
        //   this.classIDTextField = jtfClassID;
        this.classIDComboBox = jcbClassID;
        this.boardingRoomComboBox = jcbBoardingRoom;
        // this.absentDayTextField = jtfAbsentDay;
        //this.boardingRoomTextField = jtfBoardingRoom;
        this.dobDayChooser = jdcDob;
    }

    public void setView(Pupil pupil, String editOrAdd) {
        try {
            this.pupil = pupil;
            String pupilID = pupil.getID();
            idTextField.setText(pupil.getID());
            nameTextField.setText(pupil.getName());
            parentNameTextField.setText(pupil.getParentName());
            phoneTextField.setText(pupil.getPhone());
            addressTextField.setText(pupil.getAddress());
            dobDayChooser.setDate(pupil.getDoB());
            genderComboBox.setSelectedIndex(pupil.getGender());
            //  classIDTextField.setText(pupil.getClassID());
            classIDComboBox.removeAllItems();
            List<Classroom> classes = ClassroomDatabase.getAllClassrooms("Select * from classroom");
            for (Classroom classroom : classes) {
                classIDComboBox.addItem(classroom.getClassID());
            }
            classIDComboBox.setSelectedItem(pupil.getClassID());
//  absentDayTextField.setText(String.valueOf(pupil.getAbsentday()));
            //boardingRoomTextField.setText(pupil.getBoardingroom());
            boardingRoomComboBox.removeAllItems();
            List<Boardingroom> rooms = BoardingroomDatabase.getAllBoardingrooms("Select * from boardingroom");
            for (Boardingroom room : rooms) {
                String roomNumberWithZeros = room.getRoom();
                // Remove leading zeros and add to the combo box
                int roomNumber = Integer.parseInt(roomNumberWithZeros); // Convert to integer
                boardingRoomComboBox.addItem(String.valueOf(roomNumber));
            }
            //System.out.println("combo box: " + pupil.getBoardingroom());
            boardingRoomComboBox.setSelectedItem(pupil.getBoardingroom());
            if (editOrAdd.equals("add")) {
                account = new Account("", "", "", 3);
                usernameTextField.setText("");
                passwordTextField.setText("");
            } else {
                account = AccountDatabase.getAccountByID(pupilID);
                System.out.println(account.getID() + " " + account.getUsername() + " " + account.getPassword());
                usernameTextField.setText(account.getUsername());
                passwordTextField.setText(account.getPassword());
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ManagePupilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setEvent(String editOrAdd) {
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (nameTextField.getText().isEmpty() || dobDayChooser.getDate() == null) {
                    messageLabel.setText("Please fill in all required information");
                } else {
                    try {
                        account = new Account(idTextField.getText(), usernameTextField.getText(), passwordTextField.getText(), 3);
                        //System.out.println("Text Field" + account.getID() + " " + account.getUsername() + " " + account.getPassword());
                        pupil = new Pupil(idTextField.getText(), nameTextField.getText(), new java.sql.Date(dobDayChooser.getDate().getTime()), genderComboBox.getSelectedIndex(),
                                classIDComboBox.getSelectedItem().toString(), parentNameTextField.getText(), phoneTextField.getText(), addressTextField.getText(),
                                boardingRoomComboBox.getSelectedItem().toString());
                       // System.out.println("combo box: " + pupil.getBoardingroom());
                        int checkPupil = -1;
                        int checkAccount = -1;
                        //      System.out.println("Dang xem xet adding or editting?");

                        if (editOrAdd.equals("add")) {
                            System.out.println("......Adding............");
                            if (AccountDatabase.getAccountByID(account.getID()) != null) {
                                messageLabel.setText("Invalid ID");
                            } else {
                                checkAccount = AccountDatabase.create(account);
//                                System.out.println("Adding Pupil:");
//                                System.out.println("ID: " + pupil.getID());
//                                System.out.println("Class: " + pupil.getClassID());
//                                System.out.println("Boardingroom: " + pupil.getBoardingroom());
//                                System.out.println("Name: " + pupil.getName());
//                                System.out.println("Date of Birth: " + pupil.getDoB());
//                                System.out.println("Phone Number: " + pupil.getPhone());
//                                System.out.println("Address: " + pupil.getAddress());
//                                System.out.println("Parent Name: " + pupil.getParentName());
//                                System.out.println("Absent Day: " + pupil.getAbsentday());
                                checkPupil = PupilDatabase.create(pupil);
                            }
                        } else {
                            System.out.println("......Editting............");
                            checkAccount = AccountDatabase.update(account);
                            checkPupil = PupilDatabase.update(pupil);
                        }
                        if (checkPupil > 0 && checkAccount > 0) {
                            messageLabel.setText("Update Successful");
                        } else {
                            if (editOrAdd.equals("add")) {
                                AccountDatabase.delete(account);
                            }
                            messageLabel.setText("Update Failed");
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
                if (nameTextField.getText().isEmpty() || dobDayChooser.getDate() == null) {
                    messageLabel.setText("Can't delete null values");
                } else {
                    try {
                        pupil = new Pupil(idTextField.getText(), nameTextField.getText(), new java.sql.Date(dobDayChooser.getDate().getTime()), genderComboBox.getSelectedIndex(),
                                classIDComboBox.getSelectedItem().toString(), parentNameTextField.getText(), phoneTextField.getText(), addressTextField.getText(),
                                boardingRoomComboBox.getSelectedItem().toString());
                        account = new Account(idTextField.getText(), usernameTextField.getText(), passwordTextField.getText(), 1);
                        int checkPupil = PupilDatabase.delete(pupil);
                        int checkAccount = AccountDatabase.delete(account);
                        if (checkPupil > 0 && checkAccount > 0) {
                            messageLabel.setText("Delete Successful");
                        } else {
                            messageLabel.setText("Delete Failed");
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ManagePupilController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // deleteButton.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // deleteButton.setBackground(new Color(100, 221, 23));
            }
        });

        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Your code to handle cancel button click
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // cancelButton.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // cancelButton.setBackground(new Color(100, 221, 23));
            }
        });
    }
}
