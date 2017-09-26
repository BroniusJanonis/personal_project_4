package customProject.model;

import javax.persistence.*;

@Entity
public class ImgChosen {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    String imageName;
    @OneToOne
    ImgModel imgModel;

    public ImgChosen() {
    }

    public ImgChosen(String imageName) {
        this.imageName = imageName;
    }

    public ImgChosen(String imageName, ImgModel imgModel) {
        this.imageName = imageName;
        this.imgModel = imgModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public ImgModel getImgModel() {
        return imgModel;
    }

    public void setImgModel(ImgModel imgModel) {
        this.imgModel = imgModel;
    }
}
