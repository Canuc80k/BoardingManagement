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
    private void setID(String id) {
    	this.id = id; 
    }
    
    public String getName() {
    	return name;
    }
    private void setName(String name) {
    	this.name = name; 
    }
    
    public LocalDateTime getDoB() {
    	return dob;
    }
    private void setDoB(LocalDateTime dob) {
    	this.dob = dob; 
    }
    
    public String getPhone() {
    	return phone;
    }
    private void setPhone(String phone) {
    	this.phone = phone; 
    }
    
    public String getAddress() {
    	return address;
    }
    private void setAddress(String Address) {
    	this.address = Address;
    }
}
