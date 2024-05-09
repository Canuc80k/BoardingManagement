package model.calendar;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class CalendarDatabase {
    public static void addDate(LocalDate date) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String query = "Insert into calendar values(?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setDate(1, Date.valueOf(date));
        pstmt.executeUpdate();

        pstmt.close();
        con.close();
    }
}
