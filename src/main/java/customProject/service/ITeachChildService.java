package customProject.service;

import customProject.model.PageModel;
import customProject.model.Schoolchild;
import customProject.model.Teacher;

import java.util.List;
import java.util.Map;

public interface ITeachChildService {
    List<Schoolchild> schoolchildlist();
    List<Teacher> teacherlist();
    Map<Integer, Schoolchild> childrenOfTeacher(int teacherId);
    void insertChild(Schoolchild schoolchild);
    void updateChild(Schoolchild schoolchild);
    void deleteChild(int id);
    Schoolchild getChildById(int id);
    void insertTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(int id);
    Teacher getTeacherById(int id);
    Schoolchild getChildByNameNSurname(String name, String surname);
    void insertLibrary(Schoolchild schoolchild);
    List<Schoolchild> getActivitiesChildList(String activity);

}
