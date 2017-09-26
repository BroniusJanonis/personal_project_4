package customProject.model;

import javax.persistence.*;

@Entity
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    String name;
    String expiredDate;
    boolean status;
    @OneToOne
    Schoolchild schoolchild;

    public LibraryCard() {
    }

    public LibraryCard(String name, String expiredDate, boolean status) {
        this.name = name;
        this.expiredDate = expiredDate;
        this.status = status;
    }

    public LibraryCard(String name, String expiredDate, boolean status, Schoolchild schoolchild) {
        this.name = name;
        this.expiredDate = expiredDate;
        this.status = status;
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

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Schoolchild getSchoolchild() {
        return schoolchild;
    }

    public void setSchoolchild(Schoolchild schoolchild) {
        this.schoolchild = schoolchild;
    }
}
