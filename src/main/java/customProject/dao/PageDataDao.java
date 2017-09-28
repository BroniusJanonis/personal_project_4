package customProject.dao;

import customProject.model.ImgModel;
import customProject.model.PageModel;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PageDataDao implements IPageDataDao {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<PageModel> listPageModel() {
        String sqlListPageModel = "SELECT * FROM pagemodel";
        List<PageModel> resultList = entityManager.createNativeQuery(sqlListPageModel, PageModel.class).getResultList();
        return resultList;
    }

    @Override
    public void addDiv(PageModel pageModel) {
        String sqlAddPageModel = "INSERT INTO pagemodel (header, textarea) VALUES (?,?)";
        Query nativeQuery = entityManager.createNativeQuery(sqlAddPageModel, PageModel.class);
        nativeQuery.setParameter(1, pageModel.getHeader());
        nativeQuery.setParameter(2, pageModel.getTextarea());
        nativeQuery.executeUpdate();
    }

    @Override
    public void updateDiv(PageModel pageModel) {
        String sqlUpdatePageModel = "UPDATE pagemodel SET header='"+pageModel.getHeader()+"', textarea='"+pageModel.getTextarea()+"' WHERE id="+pageModel.getId();
        entityManager.createNativeQuery(sqlUpdatePageModel, PageModel.class).executeUpdate();
    }

    @Override
    public void deleteDiv(int id) {
        String sqlDeletePageModel = "DELETE FROM pagemodel WHERE id="+id;
        entityManager.createNativeQuery(sqlDeletePageModel, PageModel.class).executeUpdate();
    }

    @Override
    public List<ImgModel> listImgModel() throws IOException {
        String sqlImgList = "SELECT id, imgname, imgbyte FROM images";
        List<ImgModel> list = new ArrayList<>();
        List<ImgModel> query = jdbcTemplate.query(sqlImgList, new ResultSetExtractor<List<ImgModel>>() {
            @Override
            public List<ImgModel> extractData(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    ImgModel img = new ImgModel();
                    img.setId(resultSet.getInt("id"));
                    img.setImgname(resultSet.getString("imgname"));
                    img.setImgbyte(resultSet.getBytes("imgbyte"));
                    list.add(img);
                }
                return list;
            }
        });
        return query;
    }

    @Override
    public void addImg(ImgModel imgModel) {
        String sqlAddImg = "INSERT INTO images(imgname, imgbyte) VALUES (?,?)";
        Query nativeQuery = entityManager.createNativeQuery(sqlAddImg, ImgModel.class);
        nativeQuery.setParameter(1, imgModel.getImgname());
        nativeQuery.setParameter(2, imgModel.getImgbyte());
        nativeQuery.executeUpdate();
    }

    @Override
    public void updateImg(int image_id, String imagename) {
        String sqlUpdate = "UPDATE imgchosen SET image_id='"+image_id+"' WHERE imagename='"+imagename+"'";
        entityManager.createNativeQuery(sqlUpdate,ImgModel.class).executeUpdate();
    }

    @Override
    public void deleteImg(String imgname) {
        String sqlDelete = "DELETE FROM images WHERE imgname='"+imgname+"'";
        entityManager.createNativeQuery(sqlDelete, ImgModel.class).executeUpdate();
    }

    @Override
    public List<ImgModel> chosenImg() {
        String sqlTwoImages = "SELECT images.imgname, images.imgbyte FROM imgchosen INNER JOIN images ON imgchosen.image_id=images.id ORDER BY imgchosen.id";
        // SELECT images.imgname, images.imgbyte FROM imgchosen INNER JOIN images ON imgchosen.image_id=images.id ORDER BY imgchosen.id
        List<ImgModel> imgModelList = new ArrayList<>();
        List<ImgModel> query = jdbcTemplate.query(sqlTwoImages, new ResultSetExtractor<List<ImgModel>>() {
            @Override
            public List<ImgModel> extractData(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    ImgModel imgModel = new ImgModel();
                    imgModel.setImgname(resultSet.getString("imgname"));
                    imgModel.setImgbyte(resultSet.getBytes("imgbyte"));
                    imgModelList.add(imgModel);
                }
                return imgModelList;
            }
        });
        return query;
    }

}
