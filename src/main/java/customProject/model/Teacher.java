package customProject.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    String name;
    String surname;
    long phone;
    String info;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "teacher")
    List<Schoolchild> schoolchild;

    public Teacher() {
    }

    public Teacher(String name, String surname, long phone, String info) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.info = info;
    }

    public Teacher(String name, String surname, long phone, String info, List<Schoolchild> schoolchild) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.info = info;
        this.schoolchild = schoolchild;
    }

    public Teacher(List<Schoolchild> schoolchild) {
        this.schoolchild = schoolchild;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Schoolchild> getSchoolchild() {
        return schoolchild;
    }

    public void setSchoolchild(List<Schoolchild> schoolchild) {
        this.schoolchild = schoolchild;
    }

    //    @Override
//    public String toString() {
//        return "Teacher{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", surname='" + surname + '\'' +
//                ", phone=" + phone +
//                ", info='" + info + '\'' +
//                ", schoolchild=" + schoolchild +
//                '}';
//    }
}
