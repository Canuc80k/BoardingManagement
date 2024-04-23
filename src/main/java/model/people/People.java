package model.people;

import java.util.Date;
public class People {
    private String id;
    private String name;
    private Date dob;
    private String phone;
    private String address;
    
    public People(String id, String name, Date dob, String phone, String address) {
        this.id = id;
        this.name=  name;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
    }

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
    
    public Date getDoB() {
    	return dob;
    }
    public void setDoB(Date dob) {
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
