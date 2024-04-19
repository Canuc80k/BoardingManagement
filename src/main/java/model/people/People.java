package model.people;

import java.sql.*;
import java.time.LocalDateTime;
public class People {
    private String id;
    private String name;
    private LocalDateTime dob;
    private String phone;
    private String address;
    
    public String getID() {
    	return id;
    }
    private void setID(String ID) {
    	this.id = ID; 
    }
    
    public String getName() {
    	return name;
    }
    private void setName(String Name) {
    	this.name = Name; 
    }
    
    public LocalDateTime getDoB() {
    	return dob;
    }
    private void setDoB(LocalDateTime DoB) {
    	this.dob = DoB; 
    }
    
    public String getPhone() {
    	return phone;
    }
    private void setPhone(String Phone) {
    	this.phone = Phone; 
    }
    
    public String getAddress() {
    	return address;
    }
    private void setAddress(String Address) {
    	this.address = Address;
    }
}
