package controller.dashboard_controller.admin_dashboard_controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.account.Account;
import model.boardingroom.BoardingroomDatabase;
import model.classroom.ClassroomDatabase;
import model.people.admin.Admin;
import model.people.admin.AdminDatabase;
import model.people.pupil.PupilDatabase;
import model.people.teacher.TeacherDatabase;

public class InitController {

    private JPanel jpnView;
    private String FileName = "text_table.txt";
    private JTextField jtfSearch;
    private JLabel teacherLabel;
    private JLabel pupilLabel;
    private JLabel classroomLabel;
    private JLabel boardingroomLabel;
    private JTable table;
    private Account account;
    private TableRowSorter<TableModel> rowSorter = null;

    List<Object[]> originalRows = new ArrayList<>();
    public InitController(JPanel jpnView, JTextField jtfSearch, JLabel teacherLabel, JLabel pupilLabel, JLabel classroomLabel, JLabel boardingroomLabel, Account account) {
        this.account = account;
        this.jpnView = jpnView;
        this.jtfSearch = jtfSearch;
        this.teacherLabel = teacherLabel;
        this.pupilLabel = pupilLabel;
        this.classroomLabel = classroomLabel;
        this.boardingroomLabel = boardingroomLabel;
    }

    public static DefaultTableModel loadFromFile(String fileName) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] columns = {"ID", "Name", "Class", "Time"};
        model.setColumnIdentifiers(columns);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");
                model.addRow(new Object[]{
                    rowData[0], rowData[1], rowData[2], rowData[3]
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }

    public static void saveToFile(JTable table, String fileName) throws IOException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int row = 0; row < rowCount; row++) {
                StringBuilder rowString = new StringBuilder();
                for (int col = 0; col < colCount; col++) {
                    rowString.append(model.getValueAt(row, col).toString());
                    if (col < colCount - 1) {
                        rowString.append(",");
                    }
                }
                writer.write(rowString.toString());
                writer.newLine();
            }
        }
    }

    public void setDataToTable() throws SQLException, ClassNotFoundException, IOException {
        System.out.println("..........................SetDatatoTable InitPanel..........................");
      //  List<Teacher> listItemTeacher = TeacherDatabase.getAllTeacher("SELECT * FROM teacher where ID=" + account.getID());
        List<Admin> listItemAdmin = AdminDatabase.getAllAdmin("SELECT * FROM admin where ID=" + account.getID());
      //  List<Pupil> listItemPupil = PupilDatabase.getAllPupil("SELECT * FROM pupil where ID=" + account.getID());
        //  System.out.println("SQL Query: " + "SELECT * FROM teacher where ID=" + account.getID());
        DefaultTableModel model = loadFromFile(FileName);
        //model.setColumnIdentifiers(listColumn);

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        //    System.out.println("Time:" + formattedTime);
        for (Admin admin : listItemAdmin) {

            model.addRow(new Object[]{
                admin.getID(),
                admin.getName(),
                "Admin",
                formattedTime // Adding the current date/time to each row
            });
        }

        table = new JTable(model);
        for (int i = 0; i < model.getRowCount(); i++) {
            Object[] row = new Object[model.getColumnCount()];
            for (int j = 0; j < model.getColumnCount(); j++) {
                row[j] = model.getValueAt(i, j);
            }
            originalRows.add(row);
        }
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable(jtfSearch.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(jtfSearch.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable(jtfSearch.getText());
            }
        });

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1100, 400));

        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane, BorderLayout.CENTER);
        jpnView.validate();
        jpnView.repaint();

        saveToFile(table, FileName);

        // Update labels with counts
        updateLabelCounts();
    }
 private void filterTable(String query) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        if (query.trim().isEmpty()) {
            model.setRowCount(0); // Reset the table to its original state
            for (Object[] row : originalRows) {
                model.addRow(row);
            }
        } else {
            List<Object[]> filteredRows = new ArrayList<>();
            for (Object[] row : originalRows) {
                boolean match = false;
                for (Object cell : row) {
                    // Case-insensitive partial string match (startsWith)
                    if (cell.toString().toLowerCase().startsWith(query.toLowerCase())) {
                        match = true;
                        break;
                    }
                }
                if (match) {
                    filteredRows.add(row);
                }
            }
            model.setRowCount(0);
            for (Object[] row : filteredRows) {
                model.addRow(row);
            }
        }
    }
    private void updateLabelCounts() throws ClassNotFoundException {
        try {
            // Get counts from the database using methods from TeacherDatabase class
            int teacherCount = TeacherDatabase.countTeachers();
            int pupilCount = PupilDatabase.countPupils(); // Assuming countPupils() is a method in PupilDatabase class
            int classroomCount = ClassroomDatabase.countClassrooms(); // Assuming countClassrooms() is a method in ClassroomDatabase class
            int boardingroomCount = BoardingroomDatabase.countBoardingrooms(); // Assuming countBoardingrooms() is a method in BoardingroomDatabase class

            // Update the labels with the counts
            teacherLabel.setText("" + teacherCount);
            pupilLabel.setText("" + pupilCount);
            classroomLabel.setText("" + classroomCount);
            boardingroomLabel.setText("" + boardingroomCount);
        } catch (SQLException ex) {
            // Handle exception
            ex.printStackTrace();
        }
    }
}
