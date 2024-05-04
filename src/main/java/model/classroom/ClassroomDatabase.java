package model.classroom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassroomDatabase {

    public ClassroomDatabase() {
    }

    public static List<Classroom> getAllClassrooms(String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Classroom> res = new ArrayList<>();
        while (rs.next()) {
            Classroom temp = new Classroom(rs.getString(1), rs.getString(2), rs.getInt(3));
            res.add(temp);
        }
        rs.close();
        stmt.close();
        con.close();
        return res;
    }
    public static int countClassrooms() throws SQLException, ClassNotFoundException {
        int count = 0;
        String query = "SELECT COUNT(*) AS classroomCount FROM classroom";

        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a connection to the database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", ""); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                count = resultSet.getInt("classroomCount");
            }
        }

        return count;
    }
    public static int create(Classroom classroom) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "INSERT INTO classroom(ClassID, Room, Quantity) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, classroom.getClassID());
            pstmt.setString(2, classroom.getRoom());
            pstmt.setInt(3, classroom.getQuantity());

            int rowsInserted = pstmt.executeUpdate();

            pstmt.close();
            con.close();
            return rowsInserted;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int update(Classroom classroom) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "UPDATE classroom SET Room=?, Quantity=? WHERE ClassID=?";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, classroom.getRoom());
            pstmt.setInt(2, classroom.getQuantity());
            pstmt.setString(3, classroom.getClassID());

            int rowsUpdated = pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return rowsUpdated;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int delete(Classroom classroom) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
            Statement stmt = con.createStatement();

            String query = "DELETE FROM classroom WHERE ClassID='" + classroom.getClassID() + "'";

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
