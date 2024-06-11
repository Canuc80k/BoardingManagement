/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.dashboard_controller.teacher_dashboard_controller;

import static controller.dashboard_controller.admin_dashboard_controller.InitController.loadFromFile;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.account.Account;
import model.boardingroom.BoardingroomDatabase;
import model.classroom.ClassroomDatabase;
import model.meal.MealDatabase;
import model.people.admin.Admin;
import model.people.admin.AdminDatabase;
import model.people.pupil.Pupil;
import model.people.pupil.PupilDatabase;
import model.people.teacher.Teacher;
import model.people.teacher.TeacherDatabase;
import view.dashboard.admin_dashboard.ManagePupilJFrame;

public class MenuInitController {

    private String FileName = "text_table.txt";
    //private JTextField jtfSearch;
    private JLabel teacherLabel;
    private JLabel pupilLabel;
    private JLabel classroomLabel;
    private JLabel boardingroomLabel;
    //private JTable table;
    private Account account;
    private JTextArea mondayTextArea;
    private JTextArea tuesdayTextArea;
    private JTextArea wednesdayTextArea;
    private JTextArea thursdayTextArea;
    private JTextArea fridayTextArea;
    private JLabel mondayLabel;
    private JLabel tuesdayLabel;
    private JLabel wednesdayLabel;
    private JLabel thursdayLabel;
    private JLabel fridayLabel;

    public MenuInitController(JLabel teacherLabel, JLabel pupilLabel, JLabel classroomLabel, JLabel boardingroomLabel, Account account, JTextArea mondayTextArea, JTextArea tuesdayTextArea, JTextArea wednesdayTextArea, JTextArea thursdayTextArea, JTextArea fridayTextArea, JLabel modayLabel, JLabel tuesdayLabel, JLabel wednesdayLabel, JLabel thursdayLabel, JLabel fridayLabel) throws ClassNotFoundException, SQLException {
        this.teacherLabel = teacherLabel;
        this.pupilLabel = pupilLabel;
        this.classroomLabel = classroomLabel;
        this.boardingroomLabel = boardingroomLabel;
        this.account = account;
        this.mondayTextArea = mondayTextArea;
        this.tuesdayTextArea = tuesdayTextArea;
        this.wednesdayTextArea = wednesdayTextArea;
        this.thursdayTextArea = thursdayTextArea;
        this.fridayTextArea = fridayTextArea;
        this.mondayLabel = modayLabel;
        this.tuesdayLabel = tuesdayLabel;
        this.wednesdayLabel = wednesdayLabel;
        this.thursdayLabel = thursdayLabel;
        this.fridayLabel = fridayLabel;
        updateLabelCounts();
        getUser();
    }

    public void getUser() throws SQLException, ClassNotFoundException {
        List<Teacher> listItemTeacher = TeacherDatabase.getAllTeacher("SELECT * FROM teacher where ID=" + account.getID());
        List<Pupil> listItemPupil = PupilDatabase.getAllPupil("SELECT * FROM pupil where ID=" + account.getID());
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        for (Teacher teacher : listItemTeacher) {
            String res = teacher.getID() + "," + teacher.getName() + "," + teacher.getClassID() + "," + formattedTime;
            appendToFile(FileName, res);
        }
        for (Pupil pupil : listItemPupil) {
            String res = pupil.getID() + "," + pupil.getName() + "," + pupil.getClassID() + "," + formattedTime;
            appendToFile(FileName, res);
        }
    }

    public void setData() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/boardingmanagement"; // URL kết nối đến cơ sở dữ liệu
        String username = "root"; // Tên đăng nhập
        String password = ""; // Mật khẩu

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT "
                    + "Meal.MenuID, "
                    + "Meal.MealPhoto, "
                    + "Meal.Day, "
                    + "Menu.FoodName "
                    + "FROM "
                    + "meal "
                    + "JOIN "
                    + "menu ON meal.MenuID = menu.MenuID ";

            //model.setColumnIdentifiers(listColumn);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery();
            MealDatabase mealDatabase = new MealDatabase();
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String day = resultSet.getString("Day");
                    String foodName = resultSet.getString("FoodName");
                    String id = resultSet.getString("MenuID");

                    System.out.println("day:" + day + " foodname:" + foodName + " id:" + id + "\n");
                    if (day != null) {
                        switch (day) {
                            case "Monday":
                                mondayTextArea.append(foodName + "\n");
                                // mondayLabel= 
                                mealDatabase.displayPhoto(id, mondayLabel, 256, 201);
                                break;
                            case "Tuesday":
                                tuesdayTextArea.append(foodName + "\n");
                                mealDatabase.displayPhoto(id, tuesdayLabel, 256, 201);
                                break;
                            case "Wednesday":
                                wednesdayTextArea.append(foodName + "\n");
                                mealDatabase.displayPhoto(id, wednesdayLabel, 256, 201);
                                break;
                            case "Thursday":
                                thursdayTextArea.append(foodName + "\n");
                                mealDatabase.displayPhoto(id, thursdayLabel, 256, 201);
                                break;
                            case "Friday":
                                fridayTextArea.append(foodName + "\n");
                                mealDatabase.displayPhoto(id, fridayLabel, 256, 201);
                                break;
                            default:
                                // Handle the case where Day is NULL or not recognized
                                break;
                        }
                    } else {
                        // Handle the case where Day is NULL (if necessary)
                        System.err.println("Day is NULL for MenuID: " + resultSet.getString("MenuID"));
                    }
                }

            }

        }

    }

    public static void appendToFile(String filePath, String data) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath, true); // true indicates append mode
            fileWriter.write(data);
            fileWriter.write(System.lineSeparator()); // Add a new line after the data
        } catch (IOException e) {
            System.err.println("An IOException was caught: " + e.getMessage());
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.err.println("Failed to close the FileWriter: " + e.getMessage());
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

        }
    }
}
