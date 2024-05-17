package controller.dashboard_controller.admin_dashboard_controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.classroom.Classroom;
import model.people.pupil.Pupil;
import model.people.pupil.PupilDatabase;
import model.people.teacher.Teacher;
import model.people.teacher.TeacherDatabase;
import view.dashboard.admin_dashboard.ManagePupilJFrame;

public class ReportJFrameController {

    private final JPanel jpnView;
    private final JTextField jtfSearch;
    private JButton exportButton;
    private final Classroom classroom;
    private JTable table;
    private final String[] listColumn = {"ID", "Name", "Date of Birth", "Gender", "Class", "Parent Name", "Phone", "Address", "Boarding Room"};
    private TableRowSorter<TableModel> rowSorter = null;

    public ReportJFrameController(JPanel jpnView, JTextField jtfSearch, JButton exportButton, Classroom classroom) {
        this.jpnView = jpnView;
        this.jtfSearch = jtfSearch;
        this.exportButton = exportButton;
        this.classroom = classroom;

    }

    public void setDataToTable() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/your_database"; // URL kết nối đến cơ sở dữ liệu
        String user = "root"; // Tên đăng nhập
        String password = ""; // Mật khẩu
        String query = "SELECT " +
                       "pupil.Name, " +
                       "pupil.Class, " +
                       "pupil.BoardingRoom, " +
                       "payment.Received, " +
                       "payment.TotalPay, " +
                        "payment.PayBack, " +
                       "payment.Status, " +
                       "FROM pupil " +
                       "LEFT JOIN payment ON pupil.ID = payment.PupilID " +
                       "WHERE pupil.Class = '"+classroom.getClassID()+"'";
        List<Pupil> listItemPupil = PupilDatabase.getAllPupil("SELECT * FROM pupil WHERE class = '" + classroom.getClassID() + "'");

        List<Teacher> listItemTeacher = TeacherDatabase.getAllTeacher("SELECT * FROM teacher where classid='" + classroom.getClassID() + "'");
        //DefaultTableModel model = new DefaultTableModel();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        model.setColumnIdentifiers(listColumn);
        for (Pupil pupil : listItemPupil) {
            //System.out.println("ID:"+classroom.getClassID()+" Pupil ClassID:"+pupil.getClassID());
            model.addRow(new Object[]{
                pupil.getID(),
                pupil.getName(),
                pupil.getDoB(),
                // pupil.getGender(),
                (pupil.getGender() == 0) ? "Male" : "Female",
                pupil.getClassID(),
                pupil.getParentName(),
                pupil.getPhone(),
                pupil.getAddress(),
                pupil.getBoardingroom(), // pupil.getAbsentday()
            });
        }
        for (Teacher teacher : listItemTeacher) {
            //System.out.println("Test Teacher.....");
           // jlbTeacher.setText(teacher.getName());
        }
        table = new JTable(model);
        //table.setEnabled(false);
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
        scrollPane.setPreferredSize(new Dimension(1100, 400));
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane);
        jpnView.validate();
        jpnView.repaint();
    }

//    public void setEvent() {
//
//    }
}