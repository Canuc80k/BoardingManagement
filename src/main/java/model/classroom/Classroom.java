package model.classroom;

public class Classroom {
	private String classID;
	private String room;
	private int quantity;
	
	public String getClassID() {
		return classID;
	}
	private void setClassID(String classID) {
		this.classID = classID;
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