package customProject.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Schoolchild {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    String name;
    String surname;
    String parentinfo;
    String email;
    String address;
    @ManyToOne
    Teacher teacher;
    @OneToOne(mappedBy = "schoolchild")
    LibraryCard libraryCard;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "schoolchild_activities", joinColumns = @JoinColumn(name = "schoolchild_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "activities_id", referencedColumnName = "id"))
    List<Activities> activities;


    public Schoolchild() {
    }

    public Schoolchild(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Schoolchild(String name, String surname, String parentinfo, String email, String address) {
        this.name = name;
        this.surname = surname;
        this.parentinfo = parentinfo;
        this.email = email;
        this.address = address;
    }

    public Schoolchild(String name, String surname, String parentinfo, String email, String address, Teacher teacher) {
        this.name = name;
        this.surname = surname;
        this.parentinfo = parentinfo;
        this.email = email;
        this.address = address;
        this.teacher = teacher;
    }

    public Schoolchild(String name, String surname, String parentinfo, String email, String address, Teacher teacher, LibraryCard libraryCard) {
        this.name = name;
        this.surname = surname;
        this.parentinfo = parentinfo;
        this.email = email;
        this.address = address;
        this.teacher = teacher;
        this.libraryCard = libraryCard;
    }

    public Schoolchild(String name, String surname, String parentinfo, String email, String address, Teacher teacher, LibraryCard libraryCard, List<Activities> activities) {
        this.name = name;
        this.surname = surname;
        this.parentinfo = parentinfo;
        this.email = email;
        this.address = address;
        this.teacher = teacher;
        this.libraryCard = libraryCard;
        this.activities = activities;
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

    public String getParentinfo() {
        return parentinfo;
    }

    public void setParentinfo(String parentinfo) {
        this.parentinfo = parentinfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public LibraryCard getLibraryCard() {
        return libraryCard;
    }

    public void setLibraryCard(LibraryCard libraryCard) {
        this.libraryCard = libraryCard;
    }

    public List<Activities> getActivities() {
        return activities;
    }

    public void setActivities(List<Activities> activities) {
        this.activities = activities;
    }

//    @Override
//    public String toString() {
//        return "Schoolchild{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", surname='" + surname + '\'' +
//                ", parentinfo='" + parentinfo + '\'' +
//                ", email='" + email + '\'' +
//                ", address='" + address + '\'' +
//                ", teacher=" + teacher +
//                '}';
//    }
}
