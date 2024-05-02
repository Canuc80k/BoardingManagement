
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.account.Account;
import model.people.teacher.Teacher;

public class AccountDAOImpl implements AccountDAO{

    @Override
    public Account login(String username, String password) {
        Connection con = DBConnect.getConnection();
        String sql = "Select * from loginaccount where username like ? and password like ?";
        Account account=null;
        try {
            
            
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
//                account=new Account(username,password);
                account.setRole(rs.getInt("role"));
                account.setID(rs.getString("ID"));
               
            }
            ps.close();
            rs.close();
            con.close();
            return account;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
