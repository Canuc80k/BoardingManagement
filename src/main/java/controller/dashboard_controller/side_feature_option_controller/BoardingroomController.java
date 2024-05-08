package controller.dashboard_controller.side_feature_option_controller;

import java.awt.BorderLayout;
import java.awt.Color;
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

import model.boardingroom.Boardingroom;
import model.boardingroom.BoardingroomDatabase;
import view.dashboard.side_feature_option.ManageBoardingroomJFrame;

public class BoardingroomController {

    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnRefresh;
    private JTextField jtfSearch;
    private JTable table;
    private String[] listColumn = {"Room", "Manager ID", "Quantity"};
    private TableRowSorter<TableModel> rowSorter = null;

    public BoardingroomController(JPanel jpnView, JButton btnAdd, JButton btnDetail, JTextField jtfSearch, JButton btnRefresh) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnRefresh = btnRefresh;
    }

    public void setDataToTable() throws SQLException, ClassNotFoundException {
        List<Boardingroom> listItem = BoardingroomDatabase.getAllBoardingrooms("Select * from boardingroom");
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(listColumn);
        for (Boardingroom boardingroom : listItem) {
            model.addRow(new Object[]{
                boardingroom.getRoom(),
                boardingroom.getManagerID(),
                boardingroom.getQuantity()
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
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    // Retrieve data from the selected row in the model
                    String room = model.getValueAt(selectedRowIndex, 0).toString();
                    String managerID = model.getValueAt(selectedRowIndex, 1).toString();
                    int quantity = Integer.parseInt(model.getValueAt(selectedRowIndex, 2).toString());

                    // Create a new Boardingroom object with the parsed data
                    Boardingroom boardingroom = new Boardingroom(room, managerID, quantity);

                    // Open the ManageBoardingroomJFrame to display detailed boarding room information
                    ManageBoardingroomJFrame frame = new ManageBoardingroomJFrame(boardingroom, "edit");
                    frame.setTitle("Boarding Room Information");
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

    public void setEvent() {
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ManageBoardingroomJFrame frame = new ManageBoardingroomJFrame((new Boardingroom("", "", 0)), "add");
                frame.setTitle("Boarding Room Information");
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
                    Logger.getLogger(BoardingroomController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(BoardingroomController.class.getName()).log(Level.SEVERE, null, ex);
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
        }
        );

    }
}
