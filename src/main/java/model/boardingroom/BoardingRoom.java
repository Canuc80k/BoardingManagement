package model.boardingroom;

public class Boardingroom {
	private String managerID;
	private String room;
	private int quantity;

    public Boardingroom(String managerID, String room, int quantity) {
        this.managerID = managerID;
        this.room = room;
        this.quantity = quantity;
    }
	
	public String getManagerID() {
		return managerID;
	}
	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}
	
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
