/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.dashboard_controller;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.people.teacher.Teacher;
import model.people.teacher.TeacherDatabase;
import java.util.List;

/**
 *
 * @author huant
 */
public class ManageTeacher_controller {

    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    // private HocVienService hocV
    private String[] listColumn = {"Teacher ID", "Name", "Date of birth", "Phone", "Address", "Class ID"};
    private TableRowSorter<TableModel> rowSorter = null;

    public ManageTeacher_controller(JPanel jpnView, JButton btnAdd, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
    }

    public void setDataToTable() throws SQLException, ClassNotFoundException {
        List<Teacher> listItem = TeacherDatabase.getAllTeacher("SELECT * FROM teacher");
       DefaultTableModel model = null;// new ClassTableModel().setTableTeacher(listItem, listColumn);
        JTable table = new JTable(model);
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                 String text=jtfSearch.getText();
                if(text.trim().length()==0){
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+ text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text=jtfSearch.getText();
                if(text.trim().length()==0){
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+ text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
        table.getTableHeader().setFont(new Font("Arrial",Font.BOLD,14));
        table.getTableHeader().setPreferredSize(new Dimension(100,50));
        table.setRowHeight(50);
        table.validate();
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1300,400));
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane);
        jpnView.validate();
        jpnView.repaint();
    }

    private static class ClassTableModel {

        public ClassTableModel() {
        }
    }
}
