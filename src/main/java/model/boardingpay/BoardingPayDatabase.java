package model.boardingpay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardingPayDatabase {
    public static void get() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String query = "Select * from boardingpay";
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet res = pstmt.executeQuery();

        res.next();
        BoardingPay.boardingFee = res.getInt(1);
        BoardingPay.cleaningFee = res.getInt(2);
        pstmt.close();
        con.close();
        res.close();
    }
}
