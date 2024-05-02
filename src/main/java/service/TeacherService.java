
package service;

import java.util.List;
import model.people.teacher.Teacher;


public interface TeacherService {
    public List<Teacher> getList();
    public int createOrUpdate(Teacher teacher);
    public int create(Teacher teacher);
    public int update(Teacher teacher);
}
