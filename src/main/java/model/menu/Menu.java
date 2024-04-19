package model.menu;

import java.util.Calendar;
public class Menu {
    private String foodName;
    private Calendar calendarID;
    
    public String getFoodName() {
    	return foodName;
    }
    private void setFoodName(String FoodName) {
    	this.foodName = FoodName;
    }
    
    public Calendar getCalendarID() {
    	return calendarID;
    }
    private void setCalendarID(Calendar CalendarID) {
    	this.calendarID = CalendarID;
    }
}
