package customProject.service;

import customProject.model.ImgModel;
import customProject.model.PageModel;

import javax.servlet.jsp.tagext.PageData;
import java.io.IOException;
import java.util.List;

public interface IPageDataService {
    List<PageModel> listPageModel();
    void addDiv(PageModel pageModel);
    void updateDiv(PageModel pageModel);
    void deleteDiv(int id);

    List<ImgModel> listImgModel() throws IOException;
    void addImg(ImgModel imgModel);
    void updateImg(int image_id, String imagename);
    void deleteImg(String imgname);
    List<ImgModel> chosenImg();

}
