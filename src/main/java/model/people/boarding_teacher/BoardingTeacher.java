package model.people.boarding_teacher;

import java.util.Date;

import model.people.People;
public class BoardingTeacher extends People {
    private String boardingroom;
    
    public BoardingTeacher(String id, String name, Date dob, String phone, String address) {
        super(id, name, dob, phone, address);
    }
    
    public String getBoardingroom() {
    	return boardingroom;
    }
    public void setBoardingroom(String boardingroom) {
    	this.boardingroom = boardingroom;
    }
}
