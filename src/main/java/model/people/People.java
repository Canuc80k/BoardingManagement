package model.people;

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
    public void setID(String id) {
    	this.id = id; 
    }
    
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name; 
    }
    
    public LocalDateTime getDoB() {
    	return dob;
    }
    public void setDoB(LocalDateTime dob) {
    	this.dob = dob; 
    }
    
    public String getPhone() {
    	return phone;
    }
    public void setPhone(String phone) {
    	this.phone = phone; 
    }
    
    public String getAddress() {
    	return address;
    }
    public void setAddress(String Address) {
    	this.address = Address;
    }
}
