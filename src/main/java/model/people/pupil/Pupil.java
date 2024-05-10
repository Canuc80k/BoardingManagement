package model.people.pupil;

import java.util.Date;

import model.people.People;

public class Pupil extends People {
    private String classID;
    private String parentName;
    private String boardingroom;
    //private int absentday;
   

    public Pupil(String id, String name, Date dob,int gender, String classID,  String parentName, String phone,String address, String boardingroom) {
        super(id, name, dob, gender,phone, address);
        this.classID = classID;
        this.parentName = parentName;
        this.boardingroom = boardingroom;
       // this.absentday = absentday;
       // this.gender = gender;
    }


    public String getClassID() {return classID;}
    public void setClassID(String classID) {this.classID = classID;}
    public String getParentName() {return parentName;}
    public void setParentName(String parentName) {this.parentName = parentName;}
    public String getBoardingroom() {return boardingroom;}
    public void setBoardingroom(String boardingroom) {this.boardingroom = boardingroom;}
}
