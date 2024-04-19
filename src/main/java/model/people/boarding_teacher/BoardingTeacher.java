package model.people.boarding_teacher;

import model.people.People;
public class BoardingTeacher extends People {
    private String boardingroom;
    
    public String getBoardingroom() {
    	return boardingroom;
    }
    public void setBoardingroom(String boardingroom) {
    	this.boardingroom = boardingroom;
    }
}
