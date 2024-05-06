package model.classroom;

public class Classroom {
	private String classID;
	private String room;
	private int quantity;

    public Classroom(String classID, String room, int quantity) {
        this.classID = classID;
        this.room = room;
        this.quantity = quantity;
    }
	
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
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