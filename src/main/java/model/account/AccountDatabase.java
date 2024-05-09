package model.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDatabase {
    public static Account getAccountByUsername(String username) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String sql = "Select * from loginaccount where username like \'" + username + "\'";
        Statement pst = con.createStatement();
        ResultSet rs = pst.executeQuery(sql);

        Account account = null;
        if (rs.next()) {
             account = new Account(rs.getString("ID"), rs.getString("Username"), rs.getString("Password"),rs.getInt("Role"));
        }

        rs.close();
        pst.close();
        con.close();
        return account;
    }

    public static Account getAccountByID(String id) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String sql = "Select * from loginaccount where id like \'" + id + "\'";
        Statement pst = con.createStatement();
        ResultSet rs = pst.executeQuery(sql);

        Account account = null;
        if (rs.next()) {
            account = new Account(rs.getString("ID"), rs.getString("Username"), rs.getString("Password"),rs.getInt("Role"));
        }
        rs.close();
        pst.close();
        con.close();

        return account;
    }

    public static int create(Account account) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "INSERT INTO loginaccount(ID, Password, Role, Username) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, account.getID());
            ps.setString(2, account.getPassword());
            ps.setInt(3, account.getRole());
            ps.setString(4, account.getUsername());

            int rowsInserted = ps.executeUpdate();

            ps.close();
            con.close();
            return rowsInserted; // Return number of rows inserted
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int update(Account account) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "UPDATE loginaccount SET Password = ?, Role = ?, Username = ? WHERE ID = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, account.getPassword());
            ps.setInt(2, account.getRole());
            ps.setString(3, account.getUsername());
            ps.setString(4, account.getID());

            int rowsUpdated = ps.executeUpdate(); // Execute the update query

            ps.close();
            con.close();

            return rowsUpdated; // Return the number of rows updated
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

     public static int delete(Account account) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
            
            String query = "DELETE FROM loginaccount WHERE ID = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, account.getID());

            int rowsDeleted = pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return rowsDeleted; // Return the number of rows deleted
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
