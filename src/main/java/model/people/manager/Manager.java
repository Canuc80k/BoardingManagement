package model.people.manager;

import java.util.Date;

import model.people.People;
public class Manager extends People {
    private String boardingroom;
    
    public Manager(String id, String name, Date dob,int gender, String phone, String address,String boardingroom) {
        super(id, name, dob,gender, phone, address);
        this.boardingroom=boardingroom;
    }
    
    public String getBoardingroom() {
    	return boardingroom;
    }
    public void setBoardingroom(String boardingroom) {
    	this.boardingroom = boardingroom;
    }
}
