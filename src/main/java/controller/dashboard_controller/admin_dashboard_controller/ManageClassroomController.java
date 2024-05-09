package controller.dashboard_controller.admin_dashboard_controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.classroom.Classroom;
import model.classroom.ClassroomDatabase;

public class ManageClassroomController {

    private JButton btnSave;
    private JButton btnDelete;
    private JTextField jtfClassID;
    private JTextField jtfRoom;
    private JTextField jtfQuantity;
    private JLabel messageLabel;
    private Classroom classroom;

    public ManageClassroomController(JButton btnSave, JButton btnDelete, JTextField jtfClassID,
            JTextField jtfRoom, JTextField jtfQuantity, JLabel messageLabel, Classroom classroom) {
        this.btnSave = btnSave;
        this.btnDelete = btnDelete;
        this.jtfClassID = jtfClassID;
        this.jtfRoom = jtfRoom;
        this.jtfQuantity = jtfQuantity;
        this.messageLabel = messageLabel;
        this.classroom = classroom;
    }

    public void setView(Classroom classroom, String editOrAdd) {
        this.classroom = classroom;
        jtfClassID.setText(classroom.getClassID());
        jtfRoom.setText(classroom.getRoom());
        jtfQuantity.setText(String.valueOf(classroom.getQuantity()));
    }

    public void setEvent(String editOrAdd) {
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    classroom = new Classroom(jtfClassID.getText(), jtfRoom.getText(), Integer.parseInt(jtfQuantity.getText()));
                    int result;
                    if (editOrAdd.equals("add")) {
                        result = ClassroomDatabase.create(classroom);
                    } else {
                        result = ClassroomDatabase.update(classroom);
                    }
                    if (result > 0) {
                        messageLabel.setText("Update Success");
                        messageLabel.setForeground(Color.GREEN);
                    } else {
                        messageLabel.setText("Update Fail");
                        messageLabel.setForeground(Color.RED);
                    }
                } catch (NumberFormatException ex) {
                    messageLabel.setText("Invalid quantity");
                    messageLabel.setForeground(Color.RED);
                }
            }
        });

        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int result = ClassroomDatabase.delete(classroom);
                    if (result > 0) {
                        messageLabel.setText("Delete Success");
                        messageLabel.setForeground(Color.GREEN);
                    } else {
                        messageLabel.setText("Delete Fail");
                        messageLabel.setForeground(Color.RED);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ManageClassroomController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
