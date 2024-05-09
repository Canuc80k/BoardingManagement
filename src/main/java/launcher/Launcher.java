package launcher;

import java.sql.SQLException;

import view.login.Login;

public class Launcher {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        new Login().setVisible(true);
    }
}