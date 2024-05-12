package model.payment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import constant.PaymentState;

public class PaymentDatabase {
    public static Payment get(String pupilID, LocalDate date) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String query = "Select * from payment where PupilID = ? and Date = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, pupilID);
        pstmt.setDate(2, Date.valueOf(date));
        ResultSet res = pstmt.executeQuery();

        Payment payment = new Payment();
        if (!res.next()) {
            if (LocalDate.now().isBefore(date)) payment.setState(PaymentState.DAY_HASNT_COME_YET);
            else payment.setState(PaymentState.HASNT_PAY);
            return payment;
        }        
        payment.setTotalPay(res.getInt(4));
        payment.setReceived(res.getInt(3));
        payment.setPayback(res.getInt(5));
        pstmt.close();
        con.close();
        res.close();
        return payment;
    }
}
