package model.menu;
public class Menu {
    private String menuID;
    private String foodName;

    public Menu(String menuID, String foodName) {
        this.foodName=foodName;
        this.menuID=menuID;
    }
 

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }
    
    public String getFoodName() {
    	return foodName;
    }
    public void setFoodName(String foodName) {
    	this.foodName = foodName;
    }
   
}
