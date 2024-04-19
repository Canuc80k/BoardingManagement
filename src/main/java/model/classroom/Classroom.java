package model.classroom;

public class Classroom {
	private String classID;
	private String room;
	private int quantity;
	
	public String getClassID() {
		return classID;
	}
	private void setClassID(String ClassID) {
		this.classID = ClassID;
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