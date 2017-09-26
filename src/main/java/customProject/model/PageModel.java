package customProject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    String header;
    String textarea;

    public PageModel() {
    }

    public PageModel(String header, String textarea) {
        this.header = header;
        this.textarea = textarea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTextarea() {
        return textarea;
    }

    public void setTextarea(String textarea) {
        this.textarea = textarea;
    }
}
