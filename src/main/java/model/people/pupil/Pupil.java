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
    private void setClassID(String ClassID) {
    	this.classID = ClassID;
    }
    
    public String getParentName() {
    	return parentName;
    }
    private void setParentName(String ParentName) {
    	this.parentName = ParentName;
    }
    
    public String getBoardingroom() {
    	return boardingroom;
    }
    private void setBoardingroom(String Boardingroom) {
    	this.boardingroom = Boardingroom;
    }
    
    public int getAbsentday() {
    	return absentday;
    }
    private void setAbsentday(int Absentday) {
    	this.absentday = Absentday;
    }
    
    public boolean getGender() {
    	return gender;
    }
    private void setGender(boolean Gender) {
    	this.gender = Gender;
    }
}
