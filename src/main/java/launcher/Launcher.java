package launcher;

import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.login.Login;

public class Launcher {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatArcIJTheme");
            UIManager.put("Button.arc", 10);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException| UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new Login().setVisible(true);
    }
}