package model.absent;

import java.util.Calendar;
public class Absent {
    private String pupilID;
    private Calendar calendarID;
    
    public String getPupilID() {
    	return pupilID;
    }
    private void setPupilID(String PupilID) {
    	this.pupilID = PupilID;
    }
    public Calendar getCalendarID() {
    	return calendarID;
    }
    private void setCalendarID(Calendar CalendarID) {
    	this.calendarID = CalendarID;
    }
}
