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
    public void setClassID(String classID) {
    	this.classID = classID;
    }
    
    public String getParentName() {
    	return parentName;
    }
    public void setParentName(String parentName) {
    	this.parentName = parentName;
    }
    
    public String getBoardingroom() {
    	return boardingroom;
    }
    public void setBoardingroom(String boardingroom) {
    	this.boardingroom = boardingroom;
    }
    
    public int getAbsentday() {
    	return absentday;
    }
    public void setAbsentday(int absentday) {
    	this.absentday = absentday;
    }
    
    public boolean getGender() {
    	return gender;
    }
    public void setGender(boolean gender) {
    	this.gender = gender;
    }
}
