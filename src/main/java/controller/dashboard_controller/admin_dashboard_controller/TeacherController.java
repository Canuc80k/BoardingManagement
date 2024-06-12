package controller.dashboard_controller.admin_dashboard_controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.people.teacher.Teacher;
import model.people.teacher.TeacherDatabase;
import view.dashboard.admin_dashboard.ManageTeacherJFrame;
import java.util.ArrayList;
import java.util.List;

public class TeacherController {

    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnRefresh;
    private JTextField jtfSearch;
    private JTable table;
    private String[] listColumn = {"Teacher ID", "Name", "Date of birth", "Gender", "Phone", "Address", "Class ID"};
    private TableRowSorter<TableModel> rowSorter = null;

    public TeacherController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnRefresh) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnRefresh = btnRefresh;
    }

    public void setDataToTable() throws SQLException, ClassNotFoundException {
        List<Teacher> listItem = TeacherDatabase.getAllTeacher("SELECT * FROM teacher");
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(listColumn);
        for (Teacher teacher : listItem) {
            model.addRow(new Object[]{
                teacher.getID(),
                teacher.getName(),
                teacher.getDoB(),
                (teacher.getGender() == 0) ? "Male" : "Female",
                teacher.getPhone(),
                teacher.getAddress(),
                teacher.getClassID(),});
        }

        table = new JTable(model);

        // Manual Sorting
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                sortTableByColumn(column);
            }
        });

        // Manual Searching
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

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();

                    // Retrieve data from the selected row
                    String id = model.getValueAt(selectedRowIndex, 0).toString();
                    String name = model.getValueAt(selectedRowIndex, 1).toString();
                    Date dateOfBirth = (Date) model.getValueAt(selectedRowIndex, 2);
                    int gender = ("Male".equals(model.getValueAt(selectedRowIndex, 3).toString())) ? 0 : 1;
                    String phone = model.getValueAt(selectedRowIndex, 4).toString();
                    String address = model.getValueAt(selectedRowIndex, 5).toString();
                    String classId = model.getValueAt(selectedRowIndex, 6).toString();

                    // Create a new Teacher object with the parsed data
                    Teacher teacher = new Teacher(id, name, dateOfBirth, gender, phone, address, classId);

                    // Open the ManageTeacherJFrame to display detailed teacher information
                    ManageTeacherJFrame frame = new ManageTeacherJFrame(teacher, "edit");
                    frame.setTitle("Teacher Information");
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
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1100, 400));
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane);
        jpnView.validate();
        jpnView.repaint();
    }

    private void sortTableByColumn(int column) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Object[]> rows = new ArrayList<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            Object[] row = new Object[model.getColumnCount()];
            for (int j = 0; j < model.getColumnCount(); j++) {
                row[j] = model.getValueAt(i, j);
            }
            rows.add(row);
        }

        rows.sort((row1, row2) -> {
            Object cell1 = row1[column];
            Object cell2 = row2[column];

            if (cell1 instanceof Comparable && cell2 instanceof Comparable) {
                return ((Comparable) cell1).compareTo(cell2);
            }
            return cell1.toString().compareTo(cell2.toString());
        });

        model.setRowCount(0);
        for (Object[] row : rows) {
            model.addRow(row);
        }
    }

  private void filterTable(String query) {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    List<Object[]> originalRows = new ArrayList<>();

    for (int i = 0; i < model.getRowCount(); i++) {
      Object[] row = new Object[model.getColumnCount()];
      for (int j = 0; j < model.getColumnCount(); j++) {
        row[j] = model.getValueAt(i, j);
      }
      originalRows.add(row);
    }

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

    public void setEvent() {
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ManageTeacherJFrame frame = new ManageTeacherJFrame((new Teacher("", "", null, 0, "", "", "")), "add");
                frame.setTitle("Teacher Information");
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setBackground(new Color(100, 221, 23));
            }

        });
        btnRefresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    System.out.println("Refresh clicked");
                    // Retrieve the updated data from the database
                    setDataToTable();// Create a new RowSorter for the updated model
                } catch (SQLException ex) {
                    Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnRefresh.setBackground(new Color(252, 44, 3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnRefresh.setBackground(Color.GRAY);
            }
        });

    }
}
