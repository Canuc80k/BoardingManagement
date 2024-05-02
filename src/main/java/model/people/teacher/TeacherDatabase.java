package model.people.teacher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherDatabase {

    public TeacherDatabase() {
    }

    public static List<Teacher> getAllTeacher(String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Teacher> res = new ArrayList<Teacher>();
        while (rs.next()) {
            //res.add(new Teacher(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5)));
            Teacher temp = new Teacher(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5));
            temp.setClassID(rs.getString(6));
            res.add(temp);
        }
        rs.close();
        stmt.close();
        con.close();
        return res;
    }

    public static int create(Teacher teacher) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
            Statement stmt = con.createStatement();

            String query = "INSERT INTO teacher(ID, name, dateofbirth, phonenumber, address, classID) "
                    + "VALUES ('" + teacher.getID() + "', '" + teacher.getName() + "', '"
                    + new Date(teacher.getDoB().getTime()) + "', '" + teacher.getPhone() + "', '"
                    + teacher.getAddress() + "', '" + teacher.getClassID() + "')";

            int rowsInserted = stmt.executeUpdate(query);

            stmt.close();
            con.close();

            return rowsInserted; // Return number of rows inserted
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(Teacher teacher) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
            Statement stmt = con.createStatement();

            String query = "UPDATE teacher SET name='" + teacher.getName() + "', dateofbirth='"
                    + new Date(teacher.getDoB().getTime()) + "', phonenumber='"
                    + teacher.getPhone() + "', address='" + teacher.getAddress()
                    + "' WHERE ID='" + teacher.getID() + "'";

            int rowsUpdated = stmt.executeUpdate(query); // Execute the update query

            stmt.close();
            con.close();

            return rowsUpdated; // Return the number of rows updated
        } catch (SQLException e) {
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

            int rowsDeleted = stmt.executeUpdate(query); // Execute the delete query

            stmt.close();
            con.close();

            return rowsDeleted; // Return the number of rows deleted
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
