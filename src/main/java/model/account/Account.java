package model.account;

import java.sql.SQLException;

public class Account {
    private String id;
    private String password;
    private String username;
    private int role;

    public Account(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public static boolean login(String data, String password, boolean loginByID) throws ClassNotFoundException, SQLException {
        Account account = null;
        if (loginByID) account = AccountDatabase.getAccountByID(data);
        else account = AccountDatabase.getAccountByUsername(data);
        
        if (account == null) return false;
        if (!account.getPassword().equals(password)) return false;
        return true;
    }
    
    public String getID() {return id;}
    public void setID(String id) {this.id = id;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public int getRole() {return role;}
    public void setRole(int role) {this.role = role;}
}
