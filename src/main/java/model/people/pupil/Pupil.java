package model.people.pupil;

import model.people.People;
public class Pupil extends People{
    private String classID;
    private String parentName;
    private String boardingroom;
    private int absentday;
    private boolean gender;
    
    public String getClassID() {
    	return classID;
    }
    private void setClassID(String classID) {
    	this.classID = classID;
    }
    
    public String getParentName() {
    	return parentName;
    }
    private void setParentName(String parentName) {
    	this.parentName = parentName;
    }
    
    public String getBoardingroom() {
    	return boardingroom;
    }
    private void setBoardingroom(String boardingroom) {
    	this.boardingroom = boardingroom;
    }
    
    public int getAbsentday() {
    	return absentday;
    }
    private void setAbsentday(int absentday) {
    	this.absentday = absentday;
    }
    
    public boolean getGender() {
    	return gender;
    }
    private void setGender(boolean gender) {
    	this.gender = gender;
    }
}
