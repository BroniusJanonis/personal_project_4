package customProject.model;

import javax.persistence.*;
import java.awt.image.BufferedImage;

@Entity
public class ImgModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    String imgname;
    String imgUrl;
    byte[] imgbyte;

    public ImgModel() {
    }

    public ImgModel(String imgname, String imgUrl) {
        this.imgname = imgname;
        this.imgUrl = imgUrl;
    }

    public ImgModel(String imgname, byte[] imgbyte) {
        this.imgname = imgname;
        this.imgbyte = imgbyte;
    }

    public ImgModel(String imgname, String imgUrl, byte[] imgbyte) {
        this.imgname = imgname;
        this.imgUrl = imgUrl;
        this.imgbyte = imgbyte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgname() {
        return imgname;
    }

    public void setImgname(String imgname) {
        this.imgname = imgname;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public byte[] getImgbyte() {
        return imgbyte;
    }

    public void setImgbyte(byte[] imgbyte) {
        this.imgbyte = imgbyte;
    }
}
