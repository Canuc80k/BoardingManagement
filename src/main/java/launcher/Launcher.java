package launcher;

import java.sql.SQLException;
import java.util.List;

import model.people.teacher.Teacher;
import model.people.teacher.TeacherDatabase;
import view.login.Login;

public class Launcher {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        new Login().setVisible(true);
        
//        List<Teacher> ls = TeacherDatabase.getAllTeacher("SELECT * from teacher");
//        
//        for (int i = 0; i < ls.size(); i++) {
//                System.out.println(ls.get(i).getName()); // Assuming Teacher class has a method getName() to retrieve the name
//            }
    }
}
