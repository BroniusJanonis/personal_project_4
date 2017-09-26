package customProject.service;

import customProject.dao.IPageDataDao;
import customProject.model.ImgModel;
import customProject.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.jsp.tagext.PageData;
import java.io.IOException;
import java.util.List;

public class PageDataService implements IPageDataService {
    @Autowired
    IPageDataDao pageDataDao;

    @Override
    public List<PageModel> listPageModel() {
        return pageDataDao.listPageModel();
    }

    @Override
    public void addDiv(PageModel pageModel) {

    }

    @Override
    public void updateDiv(PageModel pageModel) {

    }

    @Override
    public void deleteDiv(int id) {

    }

    @Override
    public List<ImgModel> listImgModel() throws IOException {
        return pageDataDao.listImgModel();
    }

    @Override
    public void addImg(ImgModel imgModel) {

    }

    @Override
    public void updateImg(ImgModel imgModel) {

    }

    @Override
    public void deleteImg(String imgname) {

    }

}
