package model.payment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import constant.MaxBoardingDaysInMonth;
import constant.PaymentState;
import model.absence.AbsenceDatabase;
import model.boardingpay.BoardingPay;
import model.holiday.HolidayDatabase;

public class PaymentDatabase {
    public static Payment get(String pupilID, LocalDate date) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String query = "Select * from payment where PupilID = ? and Date = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, pupilID);
        pstmt.setDate(2, Date.valueOf(date));
        ResultSet res = pstmt.executeQuery();

        int dayCount = 0;
        for (int i = 1; i <= 31; i ++) {
            try {
                if (AbsenceDatabase.find(pupilID, LocalDate.of(date.getYear(), date.getMonthValue(), i))) continue;
                if (HolidayDatabase.find(LocalDate.of(date.getYear(), date.getMonthValue(), i))) continue;
                if (LocalDate.of(date.getYear(), date.getMonth(), i).getDayOfWeek().getValue() >= 6) continue; 
                dayCount ++;
            } catch(Exception e) {}
        }

        Payment payment = new Payment();
        int totalPay = MaxBoardingDaysInMonth.value * BoardingPay.boardingFee + BoardingPay.cleaningFee;
        int received = dayCount * BoardingPay.boardingFee + BoardingPay.cleaningFee;
        int payback = totalPay - received;
        System.out.println(MaxBoardingDaysInMonth.value + " " + BoardingPay.boardingFee + " " + BoardingPay.cleaningFee);
        payment.setTotalPay(totalPay);
        payment.setReceived(received);
        payment.setPayback(payback);
        if (!res.next()) {
            if (LocalDate.now().isBefore(date)) payment.setState(PaymentState.DAY_HASNT_COME_YET);
            else payment.setState(PaymentState.HASNT_PAY);
            return payment;
        }
        payment.setState(res.getInt(3));
        pstmt.close();
        con.close();
        res.close();
        return payment;
    }
}
