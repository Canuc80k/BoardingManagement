package model.people.boarding_teacher;

import model.people.People;
public class BoardingTeacher extends People {
    private String boardingroom;
    
    public String getBoardingroom() {
    	return boardingroom;
    }
    private void setBoardingroom(String Boardingroom) {
    	this.boardingroom = Boardingroom;
    }
}
