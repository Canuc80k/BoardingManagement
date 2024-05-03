package controller.dashboard_controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.people.pupil.Pupil;
import model.people.pupil.PupilDatabase;
import view.dashboard.admin_dashboard.ManagePupilJFrame;

public class PupilController {

    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnRefresh;
    private JTextField jtfSearch;
    private JTable table;
    private String[] listColumn = {"ID", "Name", "Date of Birth", "Class", "Parent Name", "Phone", "Address", "Boarding Room", "Absent Day"};
    private TableRowSorter<TableModel> rowSorter = null;

    public PupilController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnRefresh) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnRefresh = btnRefresh;
    }

    public void setDataToTable() {
        try {
            List<Pupil> listItem = PupilDatabase.getAllPupils("SELECT * FROM pupil");
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(listColumn);
            for (Pupil pupil : listItem) {
                model.addRow(new Object[]{
                    pupil.getID(),
                    pupil.getName(),
                    pupil.getDoB(),
                    pupil.getClassID(),
                    pupil.getParentName(),
                    pupil.getPhone(),
                    pupil.getAddress(),
                    pupil.getBoardingroom(),
                    pupil.getAbsentday()
                });
            }

            table = new JTable(model);
            rowSorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(rowSorter);
            jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String text = jtfSearch.getText();
                    if (text.trim().length() == 0) {
                        rowSorter.setRowFilter(null);
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    String text = jtfSearch.getText();
                    if (text.trim().length() == 0) {
                        rowSorter.setRowFilter(null);
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }
            });
            // Other settings for table (font, size, etc.)
           table.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int selectedRowIndex = table.getSelectedRow();
            selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

            // Retrieve data from the selected row in the model
            String id = model.getValueAt(selectedRowIndex, 0).toString();
            String name = model.getValueAt(selectedRowIndex, 1).toString();
        //    Date dobString = model.getValueAt(selectedRowIndex, 2).toString();
            String classId = model.getValueAt(selectedRowIndex, 3).toString();
            String parentName = model.getValueAt(selectedRowIndex, 4).toString();
            String phone = model.getValueAt(selectedRowIndex, 5).toString();
            String address = model.getValueAt(selectedRowIndex, 6).toString();
            String boardingRoom = model.getValueAt(selectedRowIndex, 7).toString();
            int absentDay = Integer.parseInt(model.getValueAt(selectedRowIndex, 8).toString());
                Date dateOfBirth = (Date) model.getValueAt(selectedRowIndex, 2);
            // Create a new Pupil object with the parsed data
            Pupil pupil = new Pupil(id, name, dateOfBirth, classId, parentName, phone, address, boardingRoom, absentDay);

            // Open the ManagePupilJFrame to display detailed pupil information for editing
            ManagePupilJFrame frame = new ManagePupilJFrame(pupil, "edit");
            frame.setTitle("Pupil Information");
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
});

        table.getTableHeader().setFont(new Font("Arrial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
            // Set up UI for the JPanel
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.getViewport().add(table);
            scrollPane.setPreferredSize(new Dimension(1300, 400));
            jpnView.removeAll();
            jpnView.setLayout(new BorderLayout());
            jpnView.add(scrollPane);
            jpnView.validate();
            jpnView.repaint();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PupilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setEvent() {
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open the frame to manage pupils for adding new pupil
                ManagePupilJFrame frame = new ManagePupilJFrame(new Pupil("","",null,"","","","","",0), "add");
                frame.setTitle("Pupil Information");
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

            // Other mouse listener methods for button events (enter, exit, etc.)
        });

        btnRefresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // Refresh button clicked, update the table data
                    setDataToTable();
                } catch (Exception ex) {
                    Logger.getLogger(PupilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Other mouse listener methods for button events (enter, exit, etc.)
        });
    }
}

