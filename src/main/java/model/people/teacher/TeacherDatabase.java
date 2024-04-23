package model.people.teacher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherDatabase {
    public static List<Teacher> getAllTeacher(String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");  
        Statement stmt = con.createStatement(); 
        ResultSet rs = stmt.executeQuery(query);

        List<Teacher> res = new ArrayList<Teacher>();
        while (rs.next()) {
            res.add(new Teacher(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5)));
        }
        rs.close();
        stmt.close();
        con.close();
        return res;
    }
}
