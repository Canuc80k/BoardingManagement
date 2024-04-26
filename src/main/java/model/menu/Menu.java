package model.menu;

import java.util.Calendar;
public class Menu {
    private String foodName;
    private Calendar calendarID;
    
    public String getFoodName() {
    	return foodName;
    }
    private void setFoodName(String foodName) {
    	this.foodName = foodName;
    }
    
    public Calendar getCalendarID() {
    	return calendarID;
    }
    private void setCalendarID(Calendar calendarID) {
    	this.calendarID = calendarID;
    }
}
