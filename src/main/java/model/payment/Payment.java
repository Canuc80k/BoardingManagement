package model.payment;

public class Payment {
    private String pupilID;
    private int totalPay, received, payback, state, absenceDay, boardingDay;

    public void setID(String id) {this.pupilID = id;}
    public String getID() {return pupilID;}
    public void setTotalPay(int totalPay) {this.totalPay = totalPay;}
    public int getTotalPay() {return totalPay;}
    public void setReceived(int received) {this.received = received;}
    public int getReceived() {return received;}
    public void setPayback(int payback) {this.payback = payback;}
    public int getPayback() {return payback;}
    public void setState(int state) {this.state = state;}
    public int getState() {return state;}
    public void setAbsenceDay(int absenceDay) {this.absenceDay = absenceDay;}
    public int getAbsenceDay() {return absenceDay;}
    public void setBoardingDay(int boardingDay) {this.boardingDay = boardingDay;}
    public int getBoardingDay() {return boardingDay;}
}
