
package model.meal;

import java.sql.Blob;

public class Meal {

    private String menuID;
    private Blob mealPhoto;

    public Meal(String menuID, Blob mealPhoto) {
        this.menuID = menuID;
        this.mealPhoto = mealPhoto;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public Blob getMealPhoto() {
        return mealPhoto;
    }

    public void setMealPhoto(Blob mealPhoto) {
        this.mealPhoto = mealPhoto;
    }
}
