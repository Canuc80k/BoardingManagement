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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.meal.MealDatabase;
import model.menu.Menu;
import model.menu.MenuDatabase;

public class MealController {

    private static final Logger LOGGER = Logger.getLogger(MealController.class.getName());

    private JPanel viewPanel;
    private JComboBox<String> menuComboBox;
    private JComboBox<String> dayComboBox;
    private JButton addMenuButton;
    private JButton removeMenuButton;
    private JButton addDishButton;
    private JButton deleteButton;
    private JButton saveButton;
    private JTextField foodNameTextField;
    private JLabel mealPhotoLabel;
    private JButton choosePhotoButton;
    private JTable table;
    private String[] listColumn = {"Food Name"};
    private String foodName;
    private String imgPath;
    private String day;
    private String id;

    public MealController(JPanel viewPanel, JComboBox<String> menuComboBox, JButton addMenuButton, JButton removeMenuButton,
            JButton addDishButton, JButton deleteButton, JComboBox<String> dayComboBox,
            JButton saveButton, JTextField foodNameTextField, JLabel mealPhotoLabel, JButton choosePhotoButton) {
        this.viewPanel = viewPanel;
        this.menuComboBox = menuComboBox;
        this.dayComboBox = dayComboBox;
        this.addMenuButton = addMenuButton;
        this.removeMenuButton = removeMenuButton;
        this.addDishButton = addDishButton;
        this.deleteButton = deleteButton;
        this.saveButton = saveButton;
        this.foodNameTextField = foodNameTextField;
        this.mealPhotoLabel = mealPhotoLabel;
        this.choosePhotoButton = choosePhotoButton;
        this.id = menuComboBox.getSelectedItem().toString();
    }

    public void setComboBox() {
        try {
            List<String> list = new ArrayList<>();
            List<Menu> temp = MenuDatabase.getAllMenuItems("SELECT * FROM menu");
            MealDatabase mealDatabase = new MealDatabase();
            List<String> temp1 = MealDatabase.getAllMenuItemsInMealTable("Select menuID from  meal");
            for (Menu menu : temp) {

                String menuID = menu.getMenuID();
                System.out.println("ID :" + menuID + "\n");
                if (!list.contains(menuID)) {
                    list.add(menuID);
                }
            }
            System.out.println("ID .................................\n");
            for (String i : temp1) {
                //String menuID = menu.getMenuID();
                System.out.println("ID :" + i + "\n");
                if (!list.contains(i)) {
                    list.add(i);
                }
            }
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(list.toArray(new String[0]));
            menuComboBox.setModel(model);
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error setting combo box.", ex);
        }
    }

    public void setDataToTable() {
        try {
            List<Menu> listItem = MenuDatabase.getAllMenuItems("SELECT * FROM menu WHERE MenuID = '" + id + "'");
            MealDatabase mealDatabase = new MealDatabase();
            day = mealDatabase.getDay(id);
            if (day != null) {
                dayComboBox.setSelectedItem(day);
            } else {
                dayComboBox.setSelectedIndex(0);
            }

            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            model.setColumnIdentifiers(listColumn);
            for (Menu menu : listItem) {
                model.addRow(new Object[]{menu.getFoodName()});

                //imgPath=mealDatabase.
            }
            table = new JTable(model);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                        int selectedRowIndex = table.getSelectedRow();
                        selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                        String temp = table.getModel().getValueAt(selectedRowIndex, 0).toString();
                        foodNameTextField.setText(temp);
                        foodName = temp;
                    }
                }
            });

            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
            table.getTableHeader().setPreferredSize(new Dimension(100, 50));
            table.setRowHeight(50);

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(1100, 400));
            viewPanel.removeAll();
            viewPanel.setLayout(new BorderLayout());
            viewPanel.add(scrollPane, BorderLayout.CENTER);
            viewPanel.validate();
            viewPanel.repaint();

            mealDatabase.displayPhoto(id, mealPhotoLabel, 300, 300);
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error setting data to table.", ex);
        }
    }

    public void setEvent() {
        addMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MealDatabase mealDatabase = new MealDatabase();
                String temp = mealDatabase.generateNewMenuID();
                if (temp != null) {
                    menuComboBox.addItem(temp);
                } else {
                    LOGGER.log(Level.WARNING, "Unable to generate new MenuID.");
                }
            }
        });

        menuComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = (String) menuComboBox.getSelectedItem();
                setDataToTable();
            }
        });

        dayComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newSelectedDay = (String) dayComboBox.getSelectedItem();
                if (!newSelectedDay.equals(day)) {
                    MealDatabase mealDatabase = new MealDatabase();
                    System.out.println("id: " + id + ",imgpath: " + imgPath + ",newSelected day:" + newSelectedDay + ",old_day:" + day);
                    try {
                        mealDatabase.addOrUpdateDay(id, newSelectedDay);
                    } catch (SQLException ex) {
                        Logger.getLogger(MealController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    day = newSelectedDay;
                }
                setDataToTable();
            }
        });

        addDishButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (foodNameTextField.getText() == null || foodNameTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a food name!");
                } else {
                    Menu menu = new Menu(id, foodNameTextField.getText());
                    MenuDatabase.createMenuItem(menu);
                    setDataToTable();
                }
            }
        });

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Menu menu = new Menu(id, foodNameTextField.getText());
                MenuDatabase.updateMenuItem(id, foodName, menu.getFoodName());
                setDataToTable();
            }
        });

        choosePhotoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] imgPathTemp = new String[1];
                MealDatabase mealDatabase = new MealDatabase();
                mealDatabase.browseImage(mealPhotoLabel, 300, 300, imgPathTemp);
                imgPath = imgPathTemp[0];
                mealDatabase.addOrUpdateMeal(id, imgPath, day);
            }
        });

        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuDatabase.deleteMenuItem(id, foodName);
                setDataToTable();
            }
        });
        removeMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MealDatabase mealDatabase = new MealDatabase();
                mealDatabase.deleteMenu(id);
                setComboBox();
                //setDataToTable();
            }
        });
    }
}
