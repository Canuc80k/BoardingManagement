package model.people.admin;

import java.util.Date;

import model.people.People;
public class Admin extends People{
    public Admin(String id, String name, Date dob,int gender, String phone, String address) {
        super(id, name, dob,gender, phone, address);
    }
}
