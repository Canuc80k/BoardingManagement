package model.holiday;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
}
