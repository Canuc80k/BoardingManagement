package model.people.teacher;

import model.people.People;
public class Teacher extends People{
    private String classID;
    
    public String getClassID() {
    	return classID;
    }
    private void setClassID(String ClassID) {
    	this.classID = ClassID;
    }
}
