package model.absence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.calendar.CalendarDatabase;

public class AbsenceDatabase {
    public static List<String> getAbsenceHistory(String id) throws ClassNotFoundException, SQLException {
        List<String> res = new ArrayList<String>();
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String query = "Select * from absence where PupilID = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, id);
        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            res.add(resultSet.getString(2));
        }
        
        pstmt.close();
        con.close();
        resultSet.close();
        return res;
    }

    public static boolean find(String id, LocalDate date) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String query = "Select * from absence where PupilID = ? and CalendarID = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, id);
        pstmt.setDate(2, Date.valueOf(date));
        ResultSet res = pstmt.executeQuery();

        boolean canFind = res.next();
        
        pstmt.close();
        con.close();
        res.close();
        return canFind;
    }

    public static void registAbsence(String id, LocalDate date) throws ClassNotFoundException, SQLException {
        try {CalendarDatabase.addDate(date);} catch(Exception e) {}
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String query = "Insert into absence values(?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, id);
        pstmt.setDate(2, Date.valueOf(date));
        pstmt.executeUpdate();

        pstmt.close();
        con.close();
    }

    public static void undoRegistAbsence(String id, LocalDate date) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String query = "Delete from absence where PupilID = ? and CalendarID = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, id);
        pstmt.setDate(2, Date.valueOf(date));
        pstmt.executeUpdate();

        pstmt.close();
        con.close();
    }
}
