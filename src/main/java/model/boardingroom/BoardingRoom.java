package model.boardingroom;

public class BoardingRoom {
	private String managerID;
	private String room;
	private int quantity;
	
	public String getManagerID() {
		return managerID;
	}
	private void setManagerID(String managerID) {
		this.managerID = managerID;
	}
	
	public String getRoom() {
		return room;
	}
	private void setRoom(String room) {
		this.room = room;
	}
	
	public int getQuantity() {
		return quantity;
	}
	private void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
