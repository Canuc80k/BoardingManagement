package launcher;

import java.sql.SQLException;
import java.util.List;

import model.people.teacher.Teacher;
import model.people.teacher.TeacherDatabase;
import view.login.Login;

public class Launcher {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        new Login().setVisible(true);

        List<Teacher> ls = TeacherDatabase.getAllTeacher("SELECT * from teacher");
        System.out.println(ls.size());
    }
}
