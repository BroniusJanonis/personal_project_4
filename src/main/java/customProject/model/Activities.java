package customProject.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Activities {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    String name;
    @ManyToMany(mappedBy = "activities")
    List<Schoolchild> schoolchildList;

    public Activities() {
    }

    public Activities(String name) {
        this.name = name;
    }

    public Activities(String name, List<Schoolchild> schoolchildList) {
        this.name = name;
        this.schoolchildList = schoolchildList;
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

    public List<Schoolchild> getSchoolchildList() {
        return schoolchildList;
    }

    public void setSchoolchildList(List<Schoolchild> schoolchildList) {
        this.schoolchildList = schoolchildList;
    }
}
