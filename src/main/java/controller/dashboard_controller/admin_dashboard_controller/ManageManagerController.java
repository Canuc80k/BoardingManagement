package controller.dashboard_controller.admin_dashboard_controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import java.util.List;
import model.boardingroom.Boardingroom;
import model.boardingroom.BoardingroomDatabase;

import model.people.manager.Manager;
import model.people.manager.ManagerDatabase;

public class ManageManagerController {

    private JButton saveButton;
    private JButton deleteButton;
    private JTextField managerIDTextField;
    private JTextField nameTextField;
    private JDateChooser dobDayChooser;
    private JTextField phoneTextField;
    private JTextField addressTextField;
    private JTextField boardingroomTextField;
    private JComboBox<String> boardingRoomComboBox;
    private JComboBox<String> genderComboBox;
    private Manager manager = null;
    private JLabel messageLabel;

    public ManageManagerController(JButton btnSave, JButton btnDelete, JTextField jtfManagerID, JTextField jtfName,
            JDateChooser jdcNgaySinh, JComboBox<String> jcbGender, JTextField jtfPhone, JTextField jtfAddress, JComboBox<String> jcbBoardingroom, Manager manager, JLabel jlbMsg) {
        this.saveButton = btnSave;
        this.deleteButton = btnDelete;
        this.managerIDTextField = jtfManagerID;
        this.nameTextField = jtfName;
        this.dobDayChooser = jdcNgaySinh;
        this.genderComboBox = jcbGender;
        this.phoneTextField = jtfPhone;
        this.addressTextField = jtfAddress;
        this.boardingRoomComboBox = jcbBoardingroom;
        this.messageLabel = jlbMsg;
    }

    public void setView(Manager manager, String editOrAdd) {
        try {
            this.manager = manager;
            managerIDTextField.setText(manager.getID());
            nameTextField.setText(manager.getName());
            phoneTextField.setText(manager.getPhone());
            addressTextField.setText(manager.getAddress());
            dobDayChooser.setDate(manager.getDoB());
              boardingRoomComboBox.removeAllItems();
            List<Boardingroom> rooms = BoardingroomDatabase.getAllBoardingrooms("Select * from boardingroom");
            for (Boardingroom room : rooms) {
                String roomNumberWithZeros = room.getRoom();
                // Remove leading zeros and add to the combo box
                int roomNumber = Integer.parseInt(roomNumberWithZeros); // Convert to integer
                boardingRoomComboBox.addItem(String.valueOf(roomNumber));
            }
            //System.out.println("combo box: " + pupil.getBoardingroom());
            boardingRoomComboBox.setSelectedItem(manager.getBoardingroom());
            genderComboBox.setSelectedIndex(manager.getGender());

        } catch (Exception ex) {
            Logger.getLogger(ManageManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setEvent(String editOrAdd) {
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (nameTextField.getText().length() == 0 || dobDayChooser.getDate() == null) {
                    messageLabel.setText("Please enter fill compulsory informations");
                } else {
                    manager = new Manager(managerIDTextField.getText(), nameTextField.getText(), new java.sql.Date(dobDayChooser.getDate().getTime()),
                                genderComboBox.getSelectedIndex(), phoneTextField.getText(), addressTextField.getText(), boardingRoomComboBox.getSelectedItem().toString());
                    int checkManager = -1;
                    if (editOrAdd.equals("add")) {
                        checkManager = ManagerDatabase.create(manager);
                    } else {
                        checkManager = ManagerDatabase.update(manager);
                    }
                    if (checkManager > 0) {
                        messageLabel.setText("Update Success");
                    } else {
                        messageLabel.setText("Update Fail");
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
                        manager = new Manager(managerIDTextField.getText(), nameTextField.getText(), new java.sql.Date(dobDayChooser.getDate().getTime()),
                                genderComboBox.getSelectedIndex(), phoneTextField.getText(), addressTextField.getText(), boardingRoomComboBox.getSelectedItem().toString());
                        int checkManager = ManagerDatabase.delete(manager);

                        if (checkManager > 0) {
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
