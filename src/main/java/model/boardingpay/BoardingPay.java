package model.boardingpay;

import java.sql.SQLException;

public class BoardingPay {
    public static int boardingFee, cleaningFee;
    static {
        try {
            BoardingPayDatabase.get();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
