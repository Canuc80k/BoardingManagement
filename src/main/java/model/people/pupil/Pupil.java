package model.people.pupil;

import java.util.Date;

import model.people.People;

public class Pupil extends People {
    private String classID;
    private String parentName;
    private String boardingroom;
    private int absentday;
   // private boolean gender;

    public Pupil(String id, String name, Date dob, String phone, String address, String classID, String parentName, String boardingroom, int absentday) {
        super(id, name, dob, phone, address);
        this.classID = classID;
        this.parentName = parentName;
        this.boardingroom = boardingroom;
        this.absentday = absentday;
        //this.gender = gender;
    }

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

//    public boolean getGender() {
//        return gender;
//    }
//
//    public void setGender(boolean gender) {
//        this.gender = gender;
//    }
}
