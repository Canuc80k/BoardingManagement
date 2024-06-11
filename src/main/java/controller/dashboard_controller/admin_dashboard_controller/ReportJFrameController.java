package controller.dashboard_controller.admin_dashboard_controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.account.Account;
import model.classroom.Classroom;
import model.people.pupil.Pupil;
import model.people.pupil.PupilDatabase;
import model.people.teacher.Teacher;
import model.people.teacher.TeacherDatabase;
import view.dashboard.admin_dashboard.ManagePupilJFrame;

public class ReportJFrameController {

    private  JPanel jpnView;
    private  JTextField jtfSearch;
    private JButton exportButton;
    private  Classroom classroom;
    private JTable table;
    private JComboBox dateComboBox;
    private  JButton refeshButton;
    private Account account;
    private String[] listColumn = {"Name", "Class", "Boarding Room", "Date", "Absence Days", "Status"};
    private TableRowSorter<TableModel> rowSorter = null;

    public ReportJFrameController(JPanel jpnView, JTextField jtfSearch, JButton exportButton, JComboBox dateComboBox, JButton refeshButton, Classroom classroom,Account account) {
        System.out.println("Constructed\n");
        this.jpnView = jpnView;
        this.jtfSearch = jtfSearch;
        this.refeshButton = refeshButton;
        this.dateComboBox = dateComboBox;
        this.exportButton = exportButton;
        this.classroom = classroom;
        this.account=account;
    }

    public void setDataToTable() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/boardingmanagement"; // URL kết nối đến cơ sở dữ liệu
        String username = "root"; // Tên đăng nhập
        String password = ""; // Mật khẩu
        String classID = classroom.getClassID();
        String date = dateComboBox.getSelectedItem().toString();
        String dateTemp = date;
        date += "-01";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT "
                    + "pupil.Name, "
                    + "pupil.Class, "
                    + "pupil.BoardingRoom, "
                    + "payment.Date, "
                    + "payment.AbsenceDay, "
                    + "CASE "
                    + "    WHEN payment.Status = 0 THEN 'Chưa nộp' "
                    + "    WHEN payment.Status = 2 THEN 'Đã nộp' "
                    + "    WHEN payment.Status = 3 THEN 'Đã nhận tiền thừa trả lại' "
                    + "    ELSE 'Không xác định' "
                    + "END AS Status "
                    + "FROM "
                    + "pupil "
                    + "LEFT JOIN "
                    + "payment ON pupil.ID = payment.PupilID AND payment.Date = ?"
                    + "WHERE "
                    + "pupil.Class = ? ";
                    
            
            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            model.setColumnIdentifiers(listColumn);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(2, classID);
            statement.setString(1, date);
            statement.executeQuery();
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String classValue = resultSet.getString("Class");
                    String boardingRoom = resultSet.getString("BoardingRoom");
                    String paymentDate = resultSet.getString("Date");
                    int absenceDay = resultSet.getInt("AbsenceDay");
                    String status = resultSet.getString("Status");
                    model.addRow(new Object[]{
                        name,
                        classValue,
                        boardingRoom,
                        dateTemp,
                        absenceDay,
                        status,});
                }
            }
            table = new JTable(model);
        }

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
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    // Retrieve data from the selected row in the model
                    String id = model.getValueAt(selectedRowIndex, 0).toString();
                    String name = model.getValueAt(selectedRowIndex, 1).toString();
                    Date dateOfBirth = (Date) model.getValueAt(selectedRowIndex, 2);
                    int gender = ("Male".equals(model.getValueAt(selectedRowIndex, 3).toString())) ? 0 : 1;

                    String classId = model.getValueAt(selectedRowIndex, 4).toString();
                    String parentName = model.getValueAt(selectedRowIndex, 5).toString();
                    String phone = model.getValueAt(selectedRowIndex, 6).toString();
                    String address = model.getValueAt(selectedRowIndex, 7).toString();
                    String boardingRoom = model.getValueAt(selectedRowIndex, 8).toString();
                    //int absentDay = Integer.parseInt(model.getValueAt(selectedRowIndex, 9).toString());

                    // Create a new Pupil object with the parsed data
                    Pupil pupil = new Pupil(id, name, dateOfBirth, gender, classId, parentName, phone, address, boardingRoom);

                    // Open the ManagePupilJFrame to display detailed pupil information for editing
                    ManagePupilJFrame frame = new ManagePupilJFrame(pupil, "edit");
                    frame.setTitle("Pupil Information");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            }
        });

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        // Set up UI for the JPanel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1000, 400));
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane);
        jpnView.validate();
        jpnView.repaint();
    }

    public void setEvent() throws IOException {
        refeshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // Refresh button clicked, update the table data
                    setDataToTable();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(PupilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Other mouse listener methods for button events (enter, exit, etc.)
        });
        exportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    ExportController controller = new ExportController(table, classroom,account);
                } catch (IOException ex) {
                    Logger.getLogger(ReportJFrameController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ReportJFrameController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ReportJFrameController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }
}
