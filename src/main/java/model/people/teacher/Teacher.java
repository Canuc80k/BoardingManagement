package model.people.teacher;

import java.util.Date;

import model.people.People;
public class Teacher extends People {
    public Teacher(String id, String name, Date dob,int gender, String phone, String address,String classID) {
        super(id, name, dob,gender, phone, address);
        this.classID=classID;
    }
    private String classID;
   
    public String getClassID() {
    	return classID;
    }
    public void setClassID(String classID) {
    	this.classID = classID;
    }
    
}
