package model.people.teacher;

import java.util.Date;

import model.people.People;
public class Teacher extends People {
    public Teacher(String id, String name, Date dob, String phone, String address) {
        super(id, name, dob, phone, address);
    }
    private String classID;
    
    public String getClassID() {
    	return classID;
    }
    public void setClassID(String classID) {
    	this.classID = classID;
    }
}
