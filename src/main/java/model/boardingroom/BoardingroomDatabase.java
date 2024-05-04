package model.boardingroom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardingroomDatabase {

    public BoardingroomDatabase() {
    }

    public static List<Boardingroom> getAllBoardingrooms(String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Boardingroom> res = new ArrayList<>();
        while (rs.next()) {
            Boardingroom temp = new Boardingroom(rs.getString(1), rs.getString(2), rs.getInt(3));
            res.add(temp);
        }
        rs.close();
        stmt.close();
        con.close();
        return res;
    }
    public static int countBoardingrooms() throws SQLException, ClassNotFoundException {
        int count = 0;
        String query = "SELECT COUNT(*) AS boardingroomCount FROM boardingroom";

        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a connection to the database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", ""); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                count = resultSet.getInt("boardingroomCount");
            }
        }

        return count;
    }
    public static int create(Boardingroom boardingroom) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "INSERT INTO boardingroom(Room, ManagerID, Quantity) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, boardingroom.getRoom());
            pstmt.setString(2, boardingroom.getManagerID());
            pstmt.setInt(3, boardingroom.getQuantity());

            int rowsInserted = pstmt.executeUpdate();

            pstmt.close();
            con.close();
            return rowsInserted;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int update(Boardingroom boardingroom) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "UPDATE boardingroom SET ManagerID=?, Quantity=? WHERE Room=?";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, boardingroom.getManagerID());
            pstmt.setInt(2, boardingroom.getQuantity());
            pstmt.setString(3, boardingroom.getRoom());

            int rowsUpdated = pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return rowsUpdated;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int delete(Boardingroom boardingroom) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
            Statement stmt = con.createStatement();

            String query = "DELETE FROM boardingroom WHERE Room='" + boardingroom.getRoom() + "'";

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
