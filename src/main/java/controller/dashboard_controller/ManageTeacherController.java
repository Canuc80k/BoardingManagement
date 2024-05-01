package controller.dashboard_controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.people.teacher.Teacher;
import model.people.teacher.TeacherDatabase;
import java.util.List;
import service.TeacherService;
import service.TeacherServiceImpl;

import utility.ClassTableModel;
import view.dashboard.ManageTeacherJFrame;

public class ManageTeacherController {

    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private TeacherService teacherService = null;
    private String[] listColumn = {"Teacher ID", "Name", "Date of birth", "Phone", "Address", "Class ID"};
    private TableRowSorter<TableModel> rowSorter = null;

    public ManageTeacherController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.teacherService = new TeacherServiceImpl();
    }

    public void setDataToTable() throws SQLException, ClassNotFoundException {
        List<Teacher> listItem = teacherService.getList();
        DefaultTableModel model = new ClassTableModel().setTableTeacher(listItem, listColumn);// new ClassTableModel().setTableTeacher(listItem, listColumn);
        JTable table = new JTable(model);
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
                    String dateString = model.getValueAt(selectedRowIndex, 2).toString();
                    String email = model.getValueAt(selectedRowIndex, 3).toString();
                    String phone = model.getValueAt(selectedRowIndex, 4).toString();

                    // Parse the date string into a Date object (assuming dateString is in "yyyy-MM-dd" format)
                    Date dateOfBirth = (Date) model.getValueAt(selectedRowIndex, 2);

                    // Create a new Teacher object with the parsed data
                    Teacher teacher = new Teacher(id, name, dateOfBirth, email, phone);

                    // Open the ManageTeacherJFrame to display detailed teacher information
                    ManageTeacherJFrame frame = new ManageTeacherJFrame(teacher);
                    frame.setTitle("Teacher Information");
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
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1300, 400));
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane);
        jpnView.validate();
        jpnView.repaint();
    }
    public void setEvent(){
        btnAdd.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                ManageTeacherJFrame frame=new ManageTeacherJFrame((new Teacher("","",null,"","")));
                frame.setTitle("Teacher Information");
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               btnAdd.setBackground(new Color(0,200,83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setBackground(new Color(100,221,23));
            }
            
        });
    }
}
