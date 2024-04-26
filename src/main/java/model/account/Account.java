package model.account;

public class Account {
    private String id;
    private String password;
    private String username;
    private int role;

    public Account(String password, String username) {
        this.password = password;
        this.username = username;
    }
    
    public String getID() {
    	return id;
    }
    public void setID(String id) {
    	this.id = id; 
    }
    
    public String getPassword() {
    	return password;
    }
    public void setPassword(String password) {
    	this.password = password; 
    }
    public String getUsername() {
    	return username;
    }
    public void setUsername(String username) {
    	this.username = username; 
    }
    public int getRole() {
    	return role;
    }
    public void setRole(int role) {
    	this.role = role; 
    }
}
