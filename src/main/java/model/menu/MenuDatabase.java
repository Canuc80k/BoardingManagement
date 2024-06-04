package model.menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MenuDatabase {

    public static List<Menu> getAllMenuItems(String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Menu> res = new ArrayList<>();
        while (rs.next()) {
            Menu temp = new Menu(rs.getString(1), rs.getString(2));
            res.add(temp);
        }
        rs.close();
        stmt.close();
        con.close();
        return res;
    }

    public static int createMenuItem(Menu menu) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "INSERT INTO menu VALUES (?, ?)";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, menu.getMenuID());
            pstmt.setString(2, menu.getFoodName());

            int rowsInserted = pstmt.executeUpdate();

            pstmt.close();
            con.close();
            return rowsInserted;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int deleteMenuItem(String menuID, String foodname) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM menu WHERE menuid=? AND foodname=?");

            pstmt.setString(1, menuID);
            pstmt.setString(2, foodname);

            int rowsDeleted = pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return rowsDeleted;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int updateMenuItem(String menuID, String oldFoodName, String newFoodName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "UPDATE menu SET foodname=? WHERE menuid=? AND foodname=?";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, newFoodName);
            pstmt.setString(2, menuID);
            pstmt.setString(3, oldFoodName);

            int rowsUpdated = pstmt.executeUpdate();

            pstmt.close();
            con.close();
            return rowsUpdated;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
