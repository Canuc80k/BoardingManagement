package model.people.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManagerDatabase {

    public ManagerDatabase() {
    }

    public static List<Manager> getAllManagers(String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Manager> res = new ArrayList<Manager>();
        while (rs.next()) {
            Manager temp = new Manager(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
            res.add(temp);
        }
        rs.close();
        stmt.close();
        con.close();
        return res;
    }

    public static int create(Manager manager) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "INSERT INTO manager(ID, name, dateofbirth, gender, phonenumber, address, boardingroom) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, manager.getID());
            pstmt.setString(2, manager.getName());
            pstmt.setDate(3, new java.sql.Date(manager.getDoB().getTime()));
            pstmt.setInt(4, manager.getGender());
            pstmt.setString(5, manager.getPhone());
            pstmt.setString(6, manager.getAddress());
            pstmt.setString(7, manager.getBoardingroom());

            int rowsInserted = pstmt.executeUpdate();

            pstmt.close();
            con.close();
            return rowsInserted;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int update(Manager manager) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "UPDATE manager SET name=?, dateofbirth=?, gender=?, phonenumber=?, address=? WHERE ID=?";
            
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, manager.getName());
            pstmt.setDate(2, new java.sql.Date(manager.getDoB().getTime()));
            pstmt.setInt(3, manager.getGender());
            pstmt.setString(4, manager.getPhone());
            pstmt.setString(5, manager.getAddress());
            pstmt.setString(6, manager.getID());

            int rowsUpdated = pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return rowsUpdated;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int delete(Manager manager) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
            Statement stmt = con.createStatement();

            String query = "DELETE FROM manager WHERE ID='" + manager.getID() + "'";

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

