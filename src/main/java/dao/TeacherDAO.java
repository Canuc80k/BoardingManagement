package dao;
import java.util.*;

import model.people.teacher.Teacher;
public interface TeacherDAO {
    public List<Teacher> getList();
    public int createOrUpdate(Teacher teacher);
}
