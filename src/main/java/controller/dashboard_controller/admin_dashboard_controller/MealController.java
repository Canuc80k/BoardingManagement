package controller.dashboard_controller.admin_dashboard_controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.meal.MealDatabase;
import model.menu.Menu;
import model.menu.MenuDatabase;
import model.people.teacher.Teacher;
import model.people.teacher.TeacherDatabase;
import view.dashboard.admin_dashboard.ManageTeacherJFrame;

public class MealController {

    private JPanel viewPanel;
    private JComboBox menuComboBox;
    private JButton addMenuButton;
    private JButton addDishButton;
    private JButton deleteButton;
    private JButton saveButton;
    private JTextField foodNameTextField;
    private JLabel mealPhotoLabel;
    private JButton choosePhotoButton;
    private JTable table;
    private String[] listColumn = {"Food Name"};
    private String foodname;
    private String imgPath;

    public MealController(JPanel viewPanel, JComboBox menuComboBox, JButton addMenuButton, JButton addDishButton, JButton deleteButton, JButton saveButton, JTextField foodNameTextField, JLabel mealPhotoLabel, JButton choosePhotoButton) {
        this.viewPanel = viewPanel;
        this.menuComboBox = menuComboBox;
        this.addMenuButton = addMenuButton;
        this.addDishButton = addDishButton;
        this.deleteButton = deleteButton;
        this.saveButton = saveButton;
        this.foodNameTextField = foodNameTextField;
        this.mealPhotoLabel = mealPhotoLabel;
        this.choosePhotoButton = choosePhotoButton;
        this.id = menuComboBox.getSelectedItem().toString();
    }

    public void setComboBox() throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        List<Menu> temp = MenuDatabase.getAllMenuItems("SELECT * FROM menu ");

        for (Menu menu : temp) {
            String menuID = menu.getMenuID();
            System.out.print(menuID + "\n");
            if (!list.contains(menuID)) { // Check for duplicates
                list.add(menuID);
            }
        }

        // Assuming menuComboBox is your JComboBox
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(list.toArray(new String[0]));
        menuComboBox.setModel(model);
    }
    private String id = null;

    public void setDataToTable() throws SQLException, ClassNotFoundException {
        List<Menu> listItem = MenuDatabase.getAllMenuItems("SELECT * FROM menu where menuid='" + id + "'");

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(listColumn);
        for (Menu menu : listItem) {
            model.addRow(new Object[]{
                menu.getFoodName(),});
        }
        table = new JTable(model);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    // Retrieve data from the selected row in the model
                    String temp = model.getValueAt(selectedRowIndex, 0).toString();
                    foodNameTextField.setText(temp);
                    foodname = temp;
                    System.out.println("food clicked :" + foodname + "\n");
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
        viewPanel.removeAll();
        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(scrollPane);
        viewPanel.validate();
        viewPanel.repaint();
        MealDatabase mealDatabase = new MealDatabase(); // Create an instance
        mealDatabase.displayPhoto(id, mealPhotoLabel, 300, 300);
    }

    public void setEvent() {
        addMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MealDatabase mealDatabase = new MealDatabase(); // Create an instance
                String temp = mealDatabase.generateNewMenuID(); // Call the method on the instance
                if (temp != null) {
                    menuComboBox.addItem(temp);
                } else {
                    // Handle the case when unable to generate new MenuID
                    System.out.println("Unable to generate new MenuID.");
                }
            }

        });
        menuComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle selection change event here
                id = (String) menuComboBox.getSelectedItem();
                try {
                    //System.out.println("Selected item: " + selectedItem);
                    setDataToTable();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(MealController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        addDishButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Menu menu = new Menu(id, foodNameTextField.getText());
                MenuDatabase.createMenuItem(menu);
            }

        });
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Menu menu = new Menu(id, foodNameTextField.getText());
                MenuDatabase.updateMenuItem(id, foodname, menu.getFoodName());
            }

        });
        choosePhotoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] imgPathTemp = new String[1]; // Create a string array to hold the image path
                MealDatabase mealDatabase = new MealDatabase(); // Create an instance
                mealDatabase.browseImage(mealPhotoLabel, 300, 300, imgPathTemp); // Pass the imgPath array
                //System.out.println(imgPath[0] + "\n"); // Print the selected image path
                imgPath = imgPathTemp[0];
                System.out.println("imgpath: " + imgPath + "\n");
                mealDatabase.addOrUpdateMeal(id, imgPath); //
            }

        });
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("food deleted :" + foodname + "\n");
                System.out.println(MenuDatabase.deleteMenuItem(id, foodname) + "\n");// Create an instance

            }

        });
    }

}
