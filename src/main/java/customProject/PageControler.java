package customProject;

import customProject.dao.IPageDataDao;
import customProject.model.ImgModel;
import customProject.model.PageModel;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("admin")
@Repository
@Transactional
public class PageControler {
    @Autowired
    IPageDataDao pageDataDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<PageModel>> getPageDataList(){
        List<PageModel> pageModels = pageDataDao.listPageModel();
        return new ResponseEntity<List<PageModel>>(pageModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/updatePageData", method = RequestMethod.POST)
    @ResponseBody
    public String updatePageDataDiv(@ModelAttribute PageModel pageModel, @RequestParam ("id") int id){
        pageModel.setId(id);
        pageDataDao.updateDiv(pageModel);
        return "Updated successfully";
    }

    @RequestMapping(value = "/addPageData", method = RequestMethod.POST)
    @ResponseBody
    public String addPageDataDiv(@ModelAttribute PageModel pageModel){
        pageDataDao.addDiv(pageModel);
        return "Added successfully";
    }

    @RequestMapping(value = "/deletePageData", method = RequestMethod.POST)
    @ResponseBody
    public String deletePageDataDiv(@RequestParam ("id") int id){
        pageDataDao.deleteDiv(id);
        return "Deleted successfully";
    }

    // ADD IMG FROM URL (postgres checked with @RequestBody)
    @RequestMapping(value = "/addImgFromUrl", method = RequestMethod.POST)
    public ResponseEntity<String> addImgFromUrl(@ModelAttribute ImgModel imgModel) throws IOException, SQLException {

        // iraso faila is URL i duomenu faila
        URL url = new URL(imgModel.getImgUrl());
        InputStream is = url.openStream();
        // jei noriu irasyti i kataloga
//        String pathToWrite = "C:\\Users\\Code Academy\\IdeaProjects\\personal_project_4\\src\\main\\java\\customProject\\images\\"+imgModel.getImgname()+".jpg";
//        OutputStream os = new FileOutputStream(pathToWrite);
        byte[] imageInByte;
        // convert BufferedImage to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[2048];
        int length;
        while ((length = is.read(b)) != -1) {
            baos.write(b, 0, length);
        }
        // ka gavom
        is.close();
        baos.flush();
        // i kuri kataloga irasem
        baos.close();

        imageInByte = baos.toByteArray();

        ImgModel img = new ImgModel(imgModel.getImgname(), imageInByte);
        pageDataDao.addImg(img);
        return new ResponseEntity<String>("Isirase sekmingai", HttpStatus.OK);
    }


    // ADD IMG FROM COMPUTER BROWSER
    @RequestMapping(value = "/addImgFromComputerBrowser", method = RequestMethod.POST)
    public String addImgFromComputerBrowser(@RequestParam("fileUpload") MultipartFile fileUpload[]) throws IOException, SQLException {
        if (fileUpload != null && fileUpload.length > 0) {
            Arrays.stream(fileUpload).forEach(s ->{
                try{
                    byte[] imageInByte;
                    BufferedImage originalImage = ImageIO.read((File) s);
                    // convert BufferedImage to byte array
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "jpg", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();

                    ImgModel img = new ImgModel(s.getName(),imageInByte);
                    pageDataDao.addImg(img);
                } catch (Exception e){
                    System.out.println("Klaida pridedant failu masyva is compiuterio: " + e);
                }
            });
        }
        return "Isirase is Computer Browser sekmingai";
    }


    // GET ALL IMG LIST
    @RequestMapping(value = "/getImgList", method = RequestMethod.GET)
    public ResponseEntity<List<ImgModel>> getImgList() throws IOException {
        List<ImgModel> imgModels = pageDataDao.listImgModel();
        return new ResponseEntity<List<ImgModel>>(imgModels, HttpStatus.OK);
    }

    // GET CHOSEN TWO IMG LIST
    @RequestMapping(value = "/getChosenImgList", method = RequestMethod.GET)
    public ResponseEntity<List<ImgModel>> getChosenImgList() throws IOException {
        List<ImgModel> imgModels = pageDataDao.chosenImg();
        return new ResponseEntity<List<ImgModel>>(imgModels, HttpStatus.OK);
    }

    // UPDATE CHOSEN IMG FROM LIST
    @RequestMapping(value = "/updateChosenImgFromList", method = RequestMethod.POST)
    public ResponseEntity<String> updateChosenImgFromList(@RequestParam ("id") int image_id) throws IOException {

        return new ResponseEntity<String>("Pasikeite sekmingai", HttpStatus.OK);
    }

    // Delete IMG LIST FROM LIST
    @RequestMapping(value = "/deleteChosenImgFromList", method = RequestMethod.POST)
    public ResponseEntity<String> deleteChosenImgFromList(@RequestParam ("imgname") String imgname) throws IOException {
        pageDataDao.deleteImg(imgname);
        return new ResponseEntity<String>("Issitryne", HttpStatus.OK);
    }

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public void test() throws IOException {

        byte[] imageInByte;
        BufferedImage originalImage = ImageIO.read(new File(
                "C:\\Users\\Code Academy\\IdeaProjects\\personal_project_4\\src\\main\\java\\customProject\\images\\embed2.jpg"));
        // convert BufferedImage to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(originalImage, "jpg", baos);
        baos.flush();
        imageInByte = baos.toByteArray();
        baos.close();

        // irasyti byte[] i DB
        String sqlAddImg = "INSERT INTO images(imgname, imgbyte) VALUES (?,?)";
        Query nativeQuery = entityManager.createNativeQuery(sqlAddImg, ImgModel.class);
        nativeQuery.setParameter(1, "test");
        nativeQuery.setParameter(2, imageInByte);
        nativeQuery.executeUpdate();
    }

    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    @ResponseBody
    public ImgModel test1() throws IOException {
        // gauti byte[] is DB
        String sqlImgList = "SELECT imgname, imgbyte FROM images WHERE imgname='test'";
//        ImgModel singleResult = (ImgModel) entityManager.createNativeQuery(sqlImgList, ImgModel.class).getSingleResult();

        ImgModel imgModel1 = jdbcTemplate.queryForObject(sqlImgList, new RowMapper<ImgModel>() {
            @Override
            public ImgModel mapRow(ResultSet resultSet, int i) throws SQLException {
                ImgModel imgModel = new ImgModel();
                imgModel.setImgname(resultSet.getString("imgname"));
                imgModel.setImgbyte(resultSet.getBytes("imgbyte"));
                return imgModel;
            }
        });

//        // convert byte array back to BufferedImage
//        InputStream in = new ByteArrayInputStream(imgModel1.getImgbyte());
//        BufferedImage bImageFromConvert = ImageIO.read(in);
//        // write image to directory
//        ImageIO.write(bImageFromConvert, "jpg", new File(
//                "C:\\Users\\Code Academy\\IdeaProjects\\personal_project_4\\src\\main\\java\\customProject\\images\\test-embed2.jpg"));

        return imgModel1;
    }



    // UPDATE/CHANGE IMG
    // DELETE IMG

}
