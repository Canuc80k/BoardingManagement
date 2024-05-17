package model.people.teacher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherDatabase {

    public TeacherDatabase() {
    }

    public static Teacher getTeacher(String id) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from teacher where id = '" + id + "'");

        Teacher res = null;
        while (rs.next()) {
            res = new Teacher(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
        }
        rs.close();
        stmt.close();
        con.close();
        return res;
    }

    public static List<Teacher> getAllTeacher(String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Teacher> res = new ArrayList<Teacher>();
        while (rs.next()) {
            //res.add(new Teacher(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5)));
            Teacher temp = new Teacher(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));

            res.add(temp);
        }
        rs.close();
        stmt.close();
        con.close();
        return res;
    }

    public static int countTeachers() throws SQLException, ClassNotFoundException {
        int count = 0;
        String query = "SELECT COUNT(*) AS teacherCount FROM teacher";

        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a connection to the database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", ""); 
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) 
        {

            if (resultSet.next()) {
                count = resultSet.getInt("teacherCount");
            }
        }

        return count;
    }

    public static int create(Teacher teacher) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "INSERT INTO teacher(ID, name, dateofbirth,gender, phonenumber, address, classID) "
                    + "VALUES (?, ?, ?, ?, ?, ?,?)";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, teacher.getID());
            pstmt.setString(2, teacher.getName());
            pstmt.setDate(3, new java.sql.Date(teacher.getDoB().getTime()));
            pstmt.setInt(4, teacher.getGender());
            pstmt.setString(5, teacher.getPhone());
            pstmt.setString(6, teacher.getAddress());
            pstmt.setString(7, teacher.getClassID());

            int rowsInserted = pstmt.executeUpdate();

            pstmt.close();
            con.close();
            return rowsInserted;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int update(Teacher teacher) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "UPDATE teacher SET name=?, dateofbirth=?,gender=?, phonenumber=?, address=?,classID=? WHERE ID=?";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, teacher.getName());
            pstmt.setDate(2, new java.sql.Date(teacher.getDoB().getTime()));
            pstmt.setInt(3, teacher.getGender());
            pstmt.setString(4, teacher.getPhone());
            pstmt.setString(5, teacher.getAddress());
            pstmt.setString(6, teacher.getClassID());
            pstmt.setString(7, teacher.getID());

            int rowsUpdated = pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return rowsUpdated;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int delete(Teacher teacher) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
            Statement stmt = con.createStatement();

            String query = "DELETE FROM teacher WHERE ID='" + teacher.getID() + "'";

            int rowsDeleted = stmt.executeUpdate(query);

            stmt.close();
            con.close();

            return rowsDeleted;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
