package customProject.service;

import customProject.dao.ITeachChildDao;
import customProject.model.PageModel;
import customProject.model.Schoolchild;
import customProject.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class TeachChildService implements ITeachChildService {

    @Autowired
    ITeachChildDao teachChildDao;


    @Override
    public List<Schoolchild> schoolchildlist() {
        return teachChildDao.schoolchildlist();
    }

    @Override
    public List<Teacher> teacherlist() {
        return teachChildDao.teacherlist();
    }

    @Override
    public Map<Integer, Schoolchild> childrenOfTeacher(int teacherId) {
        return teachChildDao.childrenOfTeacher(teacherId);
    }

    @Override
    public void insertChild(Schoolchild schoolchild) {
        teachChildDao.insertChild(schoolchild);
    }

    @Override
    public void updateChild(Schoolchild schoolchild) {
        teachChildDao.updateChild(schoolchild);
    }

    @Override
    public void deleteChild(int id) {
        teachChildDao.deleteChild(id);
    }

    @Override
    public Schoolchild getChildById(int id) {
        return teachChildDao.getChildById(id);
    }

    @Override
    public void insertTeacher(Teacher teacher) {
        teachChildDao.insertTeacher(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teachChildDao.updateTeacher(teacher);
    }

    @Override
    public void deleteTeacher(int id) {
        teachChildDao.deleteTeacher(id);
    }

    @Override
    public Teacher getTeacherById(int id) {
        return teachChildDao.getTeacherById(id);
    }

    @Override
    public Schoolchild getChildByNameNSurname(String name, String surname) {
        return teachChildDao.getChildByNameNSurname(name, surname);
    }

    @Override
    public void insertLibrary(Schoolchild schoolchild) {

    }

    @Override
    public List<Schoolchild> getActivitiesChildList(String activity) {
        return teachChildDao.getActivitiesChildList(activity);
    }


}
