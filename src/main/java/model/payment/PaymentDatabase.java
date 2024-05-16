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
import model.boardingpay.BoardingPay;
import model.holiday.HolidayDatabase;

public class PaymentDatabase {
    public static void add(String pupilID, LocalDate date) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String query = "Insert into payment (Date, PupilID, Status, AbsenceDay) values (?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setDate(1, Date.valueOf(date));
        pstmt.setString(2, pupilID);
        pstmt.setInt(3, 0);
        pstmt.setInt(4, 0);
        pstmt.executeUpdate();

        pstmt.close();
        con.close();
    }

    public static void updateAbsenceDay(String pupilID, LocalDate date, int delta) throws ClassNotFoundException, SQLException {
        try {PaymentDatabase.add(pupilID, date);} catch(Exception e) {}
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String query = "Update payment set absenceday = ? where pupilid = ? and date = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, PaymentDatabase.get(pupilID, date).getAbsenceDay() + delta);
        pstmt.setString(2, pupilID);
        pstmt.setDate(3, Date.valueOf(date));
        pstmt.executeUpdate();

        pstmt.close();
        con.close();
    }
    
    public static void updateState(String pupilID, LocalDate date, int state) throws ClassNotFoundException, SQLException {
        try {PaymentDatabase.add(pupilID, date);} catch(Exception e) {}
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String query = "Update payment set status = ? where pupilid = ? and date = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, state);
        pstmt.setString(2, pupilID);
        pstmt.setDate(3, Date.valueOf(date));
        pstmt.executeUpdate();

        pstmt.close();
        con.close();
    }

    public static Payment get(String pupilID, LocalDate date) throws ClassNotFoundException, SQLException {
        date = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
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
                if (HolidayDatabase.find(LocalDate.of(date.getYear(), date.getMonthValue(), i))) continue;
                if (LocalDate.of(date.getYear(), date.getMonth(), i).getDayOfWeek().getValue() >= 6) continue; 
                dayCount ++;
            } catch(Exception e) {}
        }

        Payment payment = new Payment();
        int totalPay = MaxBoardingDaysInMonth.value * BoardingPay.boardingFee + BoardingPay.cleaningFee;
        int received = dayCount * BoardingPay.boardingFee + BoardingPay.cleaningFee;
        int payback = totalPay - received;
        payment.setReceived(received);
        payment.setPayback(payback);
        payment.setTotalPay(totalPay);
        payment.setAbsenceDay(0);
        if (!res.next()) {
            if (LocalDate.now().isBefore(date)) payment.setState(PaymentState.DAY_HASNT_COME_YET);
            else payment.setState(PaymentState.HASNT_PAY);
            return payment;
        }
        
        payment.setState(res.getInt(3));
        payment.setAbsenceDay(res.getInt(4));
        if (payment.getState() == 0) {
            if (LocalDate.now().isBefore(date)) {
                payment.setState(PaymentState.DAY_HASNT_COME_YET);
            } else {
                payment.setState(PaymentState.HASNT_PAY);
            }
        }
        dayCount -= res.getInt(4);
        received = dayCount * BoardingPay.boardingFee + BoardingPay.cleaningFee;
        payback = totalPay - received;
        payment.setBoardingDay(dayCount);
        payment.setReceived(received);
        payment.setPayback(payback);
        pstmt.close();
        con.close();
        res.close();
        return payment;
    }

    public static int getState(String pupilID, LocalDate date) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        String query = "Select * from payment where PupilID = ? and Date = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, pupilID);
        pstmt.setDate(2, Date.valueOf(date));
        ResultSet res = pstmt.executeQuery();

        if (!res.next()) {
            if (LocalDate.now().isBefore(date)) return PaymentState.DAY_HASNT_COME_YET;
            return PaymentState.HASNT_PAY;
        }
        if (res.getInt(3) == 0) {
            if (LocalDate.now().isBefore(date)) return PaymentState.DAY_HASNT_COME_YET;
            return PaymentState.HASNT_PAY;
        }
        int state = res.getInt(3);
        pstmt.close();
        con.close();
        res.close();
        return state;
    }
}
