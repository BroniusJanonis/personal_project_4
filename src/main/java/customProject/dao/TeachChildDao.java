package customProject.dao;

import customProject.model.*;
import org.hibernate.FlushMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class TeachChildDao implements ITeachChildDao {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Schoolchild> schoolchildlist() {

        TypedQuery<Schoolchild> query = entityManager.createQuery("from Schoolchild", Schoolchild.class);
        List<Schoolchild> resultList = query.getResultList();

        List<Schoolchild> schoolchildList = new ArrayList<>();
        for (Schoolchild schoolchild : resultList) {
            Teacher teacher = schoolchild.getTeacher();
            Teacher teacher1 = new Teacher(teacher.getName(), teacher.getSurname(), teacher.getPhone(), teacher.getInfo());
            teacher1.setId(teacher.getId());
                LibraryCard libraryCard = schoolchild.getLibraryCard();
                Schoolchild tempschoolchild = new Schoolchild();
                tempschoolchild.setId(libraryCard.getSchoolchild().getId());
                LibraryCard libraryCard1 = new LibraryCard(libraryCard.getName(), libraryCard.getExpiredDate(), libraryCard.isStatus(), tempschoolchild);
                libraryCard1.setId(libraryCard.getId());
                    List<Activities> activitiesList = new ArrayList<>();
                    for(Activities activ: schoolchild.getActivities()){
                        Activities activities = new Activities(activ.getName());
                        activities.setId(activ.getId());
                        activitiesList.add(activities);
                    }
                        Schoolchild schoolchild1 = new Schoolchild(schoolchild.getName(), schoolchild.getSurname(), schoolchild.getParentinfo(), schoolchild.getEmail(), schoolchild.getAddress(), teacher1, libraryCard1, activitiesList);
                        schoolchild1.setId(schoolchild.getId());
                        schoolchildList.add(schoolchild1);
        }
        return schoolchildList;
    }

    @Override
    public List<Teacher> teacherlist() {
        TypedQuery<Teacher> query = entityManager.createQuery("from Teacher", Teacher.class);
        List<Teacher> resultList = query.getResultList();

        List<Teacher> teacherParsedList = new ArrayList<>();
        for (Teacher teacher : resultList) {
            List<Schoolchild> schoolchildList = new ArrayList<>();
            for (Schoolchild schoolchild : teacher.getSchoolchild()) {
                Schoolchild schoolchild1 = new Schoolchild(schoolchild.getName(), schoolchild.getSurname(), schoolchild.getParentinfo(), schoolchild.getEmail(), schoolchild.getAddress());
                schoolchild1.setId(schoolchild.getId());
                schoolchildList.add(schoolchild1);
            }
            Teacher teach = new Teacher(teacher.getName(), teacher.getSurname(), teacher.getPhone(), teacher.getInfo(), schoolchildList);
            teach.setId(teacher.getId());
            teacherParsedList.add(teach);
        }
        return teacherParsedList;
    }

    // Schoolchild's name and surname according to Teacher's id
    @Override
    public Map<Integer, Schoolchild> childrenOfTeacher(int teacherId) {
        String sqlChildrenOfTeacher = "SELECT schoolchild.name, schoolchild.surname FROM teacher INNER JOIN schoolchild ON teacher.id=schoolchild.teacher_id WHERE teacher.id=" + teacherId;
        List resultList = entityManager.createQuery(sqlChildrenOfTeacher).getResultList();
        Map<Integer, Schoolchild> map = new HashMap<>();
        resultList.forEach(g -> {
            map.put(teacherId, (Schoolchild) g);
        });
        return map;
    }

    @Override

    public void insertChild(Schoolchild schoolchild) {
        String sqlinsertChild = "Insert INTO schoolchild (name, surname, parentinfo, email, address, teacher_id) VALUES (?, ?, ?, ?, ?, ?)";
        Query nativeQuery = entityManager.createNativeQuery(sqlinsertChild, Schoolchild.class);
        nativeQuery.setParameter(1, schoolchild.getName());
        nativeQuery.setParameter(2, schoolchild.getSurname());
        nativeQuery.setParameter(3, schoolchild.getParentinfo());
        nativeQuery.setParameter(4, schoolchild.getEmail());
        nativeQuery.setParameter(5, schoolchild.getAddress());
        nativeQuery.setParameter(6, schoolchild.getTeacher().getId());
        nativeQuery.executeUpdate();
//        nativeQuery.setFlushMode(FlushModeType.COMMIT);
    }

    @Override
    public void updateChild(Schoolchild schoolchild) {
        String sqlupdateChild = "UPDATE schoolchild SET name='" + schoolchild.getName() + "', surname='" + schoolchild.getSurname() + "', parentinfo='" + schoolchild.getParentinfo() + "', email='" + schoolchild.getEmail() + "', address='" + schoolchild.getAddress() + "', teacher_id='" + schoolchild.getTeacher().getId() + "' WHERE id='" + schoolchild.getId() + "'";
        entityManager.createNativeQuery(sqlupdateChild, Schoolchild.class).executeUpdate();
        String sqlupdateLibrary = "UPDATE library_card SET name='" + schoolchild.getLibraryCard().getName() + "', expired_date='" + schoolchild.getLibraryCard().getExpiredDate() + "', status='" + schoolchild.getLibraryCard().isStatus() + "' WHERE id="+schoolchild.getLibraryCard().getId();
        entityManager.createNativeQuery(sqlupdateLibrary, LibraryCard.class).executeUpdate();
    }

    @Override
    public void deleteChild(int id) {
        String sqldelete = "DELETE FROM schoolchild where id=" + id;
        entityManager.createNativeQuery(sqldelete, Schoolchild.class).executeUpdate();
    }

    @Override
    public Schoolchild getChildById(int id) {
        String sqlgetbyid = "SELECT * FROM schoolchild WHERE id=" + id;
        Object singleResult = entityManager.createNativeQuery(sqlgetbyid, Schoolchild.class).getSingleResult();
        return (Schoolchild) singleResult;
    }

    @Override
    public Schoolchild getChildByNameNSurname(String name, String surname) {
        String sqlgetbyNameNSurname = "SELECT * FROM schoolchild WHERE name='" + name + "' and surname='" + surname + "'";
        // arba per JdbcTemplate
//        Schoolchild schoolchild = new Schoolchild();
//        Schoolchild query = jdbcTemplate.query(sqlgetbyNameNSurname, new ResultSetExtractor<Schoolchild>() {
//            @Override
//            public Schoolchild extractData(ResultSet resultSet) throws SQLException, DataAccessException {
//                schoolchild.setId(resultSet.getInt(0));
//                schoolchild.setName(resultSet.getString(1));
//                schoolchild.setSurname(resultSet.getString(2));
//                schoolchild.setParentinfo(resultSet.getString(3));
//                schoolchild.setEmail(resultSet.getString(4));
//                schoolchild.setAddress(resultSet.getString(5));
//                schoolchild.setTeacher((Teacher) resultSet.getObject(6));
//                schoolchild.setLibraryCard((LibraryCard) resultSet.getObject(7));
//                return schoolchild;
//            }
//        });
        Schoolchild singleResult1 = (Schoolchild)entityManager.createNativeQuery(sqlgetbyNameNSurname, Schoolchild.class).getSingleResult();
        Teacher teacher1 = singleResult1.getTeacher();
        Teacher teacher = new Teacher(teacher1.getName(), teacher1.getSurname(), teacher1.getPhone(), teacher1.getInfo());
        teacher.setId(teacher1.getId());
        Schoolchild schoolchild = new Schoolchild(singleResult1.getName(), singleResult1.getSurname(), singleResult1.getParentinfo(), singleResult1.getEmail(), singleResult1.getAddress(), teacher);
        schoolchild.setId(singleResult1.getId());
        return schoolchild;
    }

    @Override
    public void insertLibrary(Schoolchild schoolchild) {

        Schoolchild childByNameNSurname = getChildByNameNSurname(schoolchild.getName(), schoolchild.getSurname());

        String sqlinsertLibCard = "INSERT INTO library_card (expired_date, name, status, schoolchild_id) VALUES (?,?,?,?)";
        Query nativeQuery1 = entityManager.createNativeQuery(sqlinsertLibCard, LibraryCard.class);
        nativeQuery1.setParameter(1, schoolchild.getLibraryCard().getExpiredDate());
        nativeQuery1.setParameter(2, schoolchild.getLibraryCard().getName());
        nativeQuery1.setParameter(3, schoolchild.getLibraryCard().isStatus());
        nativeQuery1.setParameter(4, childByNameNSurname.getId());
        nativeQuery1.executeUpdate();
    }

    @Override
    public List<Schoolchild> getActivitiesChildList(String activity) {
        int activityId = 0;
        if(activity.matches("football")){
            activityId = 1;
        } else if(activity.matches("basketball")){
            activityId = 2;
        } else if(activity.matches("volleyball")){
            activityId = 3;
        }
        String sqlFootballList ="select * from schoolchild INNER JOIN schoolchild_activities on schoolchild.id = schoolchild_activities.schoolchild_id where schoolchild_activities.activities_id="+activityId;
        List<Schoolchild> list = entityManager.createNativeQuery(sqlFootballList, Schoolchild.class).getResultList();
        ArrayList<Schoolchild> footballList = new ArrayList<>(list.size());
        for(Schoolchild child: list){
            Schoolchild schoolchild = new Schoolchild(child.getName(), child.getSurname());
            footballList.add(schoolchild);
        }
        return footballList;
    }

    @Override
    public void insertTeacher(Teacher teacher) {
        String sqlinsert = "INSERT INTO teacher (name, surname, phone, info) VALUES (?, ?, ?, ?)";
        Query nativeQuery = entityManager.createNativeQuery(sqlinsert, Teacher.class);
        nativeQuery.setParameter(1, teacher.getName());
        nativeQuery.setParameter(2, teacher.getSurname());
        nativeQuery.setParameter(3, teacher.getPhone());
        nativeQuery.setParameter(4, teacher.getInfo());
        nativeQuery.executeUpdate();
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        String sqlupdate = "UPDATE teacher SET name='" + teacher.getName() + "', surname='" + teacher.getSurname() + "', phone='" + teacher.getPhone() + "', info='" + teacher.getInfo() + "' WHERE id='" + teacher.getId() + "'";
        entityManager.createNativeQuery(sqlupdate, Teacher.class).executeUpdate();
    }

    @Override
    public void deleteTeacher(int id) {
        String sqldelete = "DELETE FROM teacher where id=" + id;
        entityManager.createNativeQuery(sqldelete, Teacher.class).executeUpdate();
    }

    @Override
    public Teacher getTeacherById(int id) {
        String sqlgetbyid = "SELECT * FROM teacher WHERE id=" + id;
        Teacher singleResult = (Teacher) entityManager.createNativeQuery(sqlgetbyid, Teacher.class).getSingleResult();
        List<Schoolchild> schoolchildList = new ArrayList<>();
        for(Schoolchild skclh: singleResult.getSchoolchild()){
            Schoolchild schoolchild = new Schoolchild(skclh.getName(), skclh.getSurname(), skclh.getParentinfo(), skclh.getEmail(), skclh.getAddress());
            schoolchild.setId(skclh.getId());
            schoolchildList.add(schoolchild);
        }
        Teacher teacher = new Teacher(singleResult.getName(), singleResult.getSurname(), singleResult.getPhone(), singleResult.getInfo(), schoolchildList);
        teacher.setId(singleResult.getId());
        return teacher;
    }


}
