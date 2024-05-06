package model.people.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminDatabase {

    public AdminDatabase() {
    }

    public static List<Admin> getAllAdmin(String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Admin> res = new ArrayList<>();
        while (rs.next()) {
            Admin temp = new Admin(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getString(5), rs.getString(6));
            res.add(temp);
        }
        rs.close();
        stmt.close();
        con.close();
        return res;
    }

    public static int countAdmins() throws SQLException, ClassNotFoundException {
        int count = 0;
        String query = "SELECT COUNT(*) AS adminCount FROM admin";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", ""); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                count = resultSet.getInt("adminCount");
            }
        }

        return count;
    }

    public static int create(Admin admin) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "INSERT INTO admin(ID, name, dateofbirth, gender, phone,address) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, admin.getID());
            pstmt.setString(2, admin.getName());
            pstmt.setDate(3, new java.sql.Date(admin.getDoB().getTime()));
            pstmt.setInt(4, admin.getGender());
            pstmt.setString(5, admin.getPhone());
            pstmt.setString(6, admin.getAddress());

            int rowsInserted = pstmt.executeUpdate();

            pstmt.close();
            con.close();
            return rowsInserted;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int update(Admin admin) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "UPDATE admin SET name=?, dateofbirth=?, username=?, password=? WHERE ID=?";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, admin.getName());
            pstmt.setDate(2, new java.sql.Date(admin.getDoB().getTime()));
            pstmt.setInt(3, admin.getGender());
            pstmt.setString(4, admin.getPhone());
            pstmt.setString(5, admin.getAddress());
            pstmt.setString(6, admin.getID());

            int rowsUpdated = pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return rowsUpdated;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int delete(Admin admin) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
            Statement stmt = con.createStatement();

            String query = "DELETE FROM admin WHERE ID='" + admin.getID() + "'";

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
