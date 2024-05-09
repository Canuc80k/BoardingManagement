package model.absence;

import java.time.LocalDate;

public class Absence {
    private String pupilID;
    private LocalDate calendarID;

    public String getPupilID() {return pupilID;}
    public void setPupilID(String pupilID) {this.pupilID = pupilID;}
    public LocalDate getCalendarID() {return calendarID;}
    public void setCalendarID(LocalDate calendarID) {this.calendarID = calendarID;}
}
