package model.account;

public class Account {
    private String id;
    private String password;
    private String username;
    private int role;
    
    public String getID() {
    	return id;
    }
    private void setID(String id) {
    	this.id = id; 
    }
    
    public String getPassword() {
    	return password;
    }
    private void setPassword(String password) {
    	this.password = password; 
    }
    public String getUsername() {
    	return username;
    }
    private void setUsername(String username) {
    	this.username = username; 
    }
    public int getRole() {
    	return role;
    }
    private void setRole(int role) {
    	this.role = role; 
    }
}
