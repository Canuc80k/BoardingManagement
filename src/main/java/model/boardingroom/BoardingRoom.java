package model.boardingroom;

public class BoardingRoom {
	private String managerID;
	private String room;
	private int quantity;
	
	public String getManagerID() {
		return managerID;
	}
	private void setManagerID(String ManagerID) {
		this.managerID = ManagerID;
	}
	
	public String getRoom() {
		return room;
	}
	private void setRoom(String Room) {
		this.room = Room;
	}
	
	public int getQuantity() {
		return quantity;
	}
	private void setQuantity(int Quantity) {
		this.quantity = Quantity;
	}
}
