package dao;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DBConnect {
    public static Connection getConnection(){
        try {
            Connection con= null;
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:4306/boardingmanagement","root","");
            return con;
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) throws SQLException {
        Connection con=getConnection();
        System.out.println(con.toString());
        con.close();
    }
}
