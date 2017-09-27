package customProject.dao;

import customProject.model.ImgModel;
import customProject.model.PageModel;

import javax.servlet.jsp.tagext.PageData;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IPageDataDao {
    List<PageModel> listPageModel();
    void addDiv(PageModel pageModel);
    void updateDiv(PageModel pageModel);
    void deleteDiv(int id);

    List<ImgModel> listImgModel() throws IOException;
    void addImg(ImgModel imgModel) throws IOException, SQLException;
    void updateImg(int image_id, String imagename);
    void deleteImg(String imgname);
    List<ImgModel> chosenImg();
}
