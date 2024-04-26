package model.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDatabase {
    protected static Account getAccountByUsername(String username) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");  
        String sql = "Select * from loginaccount where username like \'" + username + "\'";
        Statement pst = con.createStatement();
        ResultSet rs = pst.executeQuery(sql);

        Account account = null;
        if (rs.next()) account = new Account(rs.getString("ID"), rs.getString("Username"), rs.getString("Password"));
        
        rs.close(); pst.close(); con.close();
        return account;
    }    
    
    protected static Account getAccountByID(String id) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");  
        String sql = "Select * from loginaccount where id like \'" + id + "\'";
        Statement pst = con.createStatement();
        ResultSet rs = pst.executeQuery(sql);

        Account account = null;
        if (rs.next()) account = new Account(rs.getString("ID"), rs.getString("Username"), rs.getString("Password"));
        rs.close(); pst.close(); con.close();
        
        return account;
    }
}
