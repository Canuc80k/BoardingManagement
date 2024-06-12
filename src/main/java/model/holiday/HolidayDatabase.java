package model.holiday;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.calendar.CalendarDatabase;

public class HolidayDatabase {
    public static boolean find(LocalDate date) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String query = "Select * from holiday where CalendarID = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setDate(1, Date.valueOf(date));
        ResultSet res = pstmt.executeQuery();

        boolean canFind = res.next();
        
        pstmt.close();
        con.close();
        res.close();
        return canFind;
    }

    public static void registHoliday(LocalDate date) throws SQLException, ClassNotFoundException {
        try {CalendarDatabase.addDate(date);} catch(Exception e) {}
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String query = "Insert into holiday values(?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setDate(1, Date.valueOf(date));
        pstmt.executeUpdate();

        pstmt.close();
        con.close();
    }

    public static void undoRegistHoliday(LocalDate date) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String query = "Delete from holiday where CalendarID = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setDate(1, Date.valueOf(date));
        pstmt.executeUpdate();

        pstmt.close();
        con.close();
    }
}
