package controller.dashboard_controller.admin_dashboard_controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.boardingroom.Boardingroom;
import model.boardingroom.BoardingroomDatabase;

public class ManageBoardingroomController {

    private JButton btnSave;
    private JButton btnDelete;
    private JTextField jtfRoom;
    private JTextField jtfManagerID;
    private JTextField jtfQuantity;
    private JLabel messageLabel;
    private Boardingroom boardingroom;
    
    public ManageBoardingroomController(JButton btnSave, JButton btnDelete, JTextField jtfRoom,
            JTextField jtfManagerID, JTextField jtfQuantity, JLabel messageLabel, Boardingroom boardingroom) {
        this.btnSave = btnSave;
        this.btnDelete = btnDelete;
        this.jtfRoom = jtfRoom;
        this.jtfManagerID = jtfManagerID;
        this.jtfQuantity = jtfQuantity;
        this.messageLabel = messageLabel;
        this.boardingroom = boardingroom;
    }

    public void setView(Boardingroom boardingroom, String editOrAdd) {
        this.boardingroom = boardingroom;
        jtfRoom.setText(boardingroom.getRoom());
        jtfManagerID.setText(boardingroom.getManagerID());
        jtfQuantity.setText(String.valueOf(boardingroom.getQuantity()));
    }

    public void setEvent(String editOrAdd) {
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    boardingroom = new Boardingroom(jtfRoom.getText(), jtfManagerID.getText(), Integer.parseInt(jtfQuantity.getText()));
                    int result;
                    if (editOrAdd.equals("add")) {
                        result = BoardingroomDatabase.create(boardingroom);
                    } else {
                        result = BoardingroomDatabase.update(boardingroom);
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
                    int result = BoardingroomDatabase.delete(boardingroom);
                    if (result > 0) {
                        messageLabel.setText("Delete Success");
                        messageLabel.setForeground(Color.GREEN);
                    } else {
                        messageLabel.setText("Delete Fail");
                        messageLabel.setForeground(Color.RED);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ManageBoardingroomController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
