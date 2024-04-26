package model.people.admin;

import java.util.Date;

import model.people.People;
public class Admin extends People{
    public Admin(String id, String name, Date dob, String phone, String address) {
        super(id, name, dob, phone, address);
    }
}
