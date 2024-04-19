package model.account;

public class Account {
    private String id;
    private String password;
    private String username;
    private int role;
    
    public String getID() {
    	return id;
    }
    private void setID(String ID) {
    	this.id = ID; 
    }
    
    public String getPassword() {
    	return password;
    }
    private void setPassword(String Password) {
    	this.password = Password; 
    }
    public String getUsername() {
    	return username;
    }
    private void setUsername(String Username) {
    	this.username = Username; 
    }
    public int getRole() {
    	return role;
    }
    private void setRole(int Role) {
    	this.role = Role; 
    }
}
