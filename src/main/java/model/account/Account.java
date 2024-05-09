package model.account;

import java.sql.SQLException;

public class Account {
    private String id;
    private String password;
    private String username;
    private int role;

    public Account(String id, String username, String password, int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role=role;
    }

    public static Account login(String data, String password, boolean loginByID) throws ClassNotFoundException, SQLException {
        Account account = null;
        if (loginByID) account = AccountDatabase.getAccountByID(data);
        else account = AccountDatabase.getAccountByUsername(data);
        
        if (account == null) return null;
        if (!account.getPassword().equals(password)) return null;
        return account;
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
