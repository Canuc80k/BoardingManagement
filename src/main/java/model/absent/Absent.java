package model.absent;

import java.util.Calendar;
public class Absent {
    private String pupilID;
    private Calendar calendarID;
    
    public String getPupilID() {
    	return pupilID;
    }
    private void setPupilID(String pupilID) {
    	this.pupilID = pupilID;
    }
    public Calendar getCalendarID() {
    	return calendarID;
    }
    private void setCalendarID(Calendar calendarID) {
    	this.calendarID = calendarID;
    }
}
