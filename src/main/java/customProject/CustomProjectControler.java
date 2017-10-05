package customProject;

import customProject.dao.ITeachChildDao;
import customProject.model.LibraryCard;
import customProject.model.Schoolchild;
import customProject.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomProjectControler {

    @Autowired
    ITeachChildDao teachChildDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMain(){return "firstPage";}

    // REDIRECTS HEADER TO JSP WINDOW
    @RequestMapping(name="/redirectwindow", method = RequestMethod.GET)
    public String getNextWindow(@RequestParam("address") String address){
        return address;
    }

    // REQUEST DATA FOR firstPage/MeniuOption FROM for(Optional) Jsp
    @RequestMapping(value="/firstPage", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView getForTeachersJspData(HttpServletResponse response
            ,@RequestParam("address") String address) {
        ModelAndView model = new ModelAndView(address);
        switch (address) {
            case "forTeachers":
                List<Schoolchild> schoolchildlist = teachChildDao.schoolchildlist();
                model.addObject("schoolchildlist", schoolchildlist);
                break;
            case "forChildren":
                List<Teacher> teacherlist = teachChildDao.teacherlist();
                model.addObject("teacherlist", teacherlist);
                break;
            case "forParents":
                List<Schoolchild> schoolchildlist2 = teachChildDao.schoolchildlist();
                model.addObject("childteacherlist", schoolchildlist2);
                break;
        }
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        return model;
    }

    // GET CHILDREN LIST
    @RequestMapping(value = "/allchildren", method = RequestMethod.GET)
    public ResponseEntity<List<Schoolchild>> getChildrenList(){
        List<Schoolchild> schoolchildlist = teachChildDao.schoolchildlist();
        return new ResponseEntity<List<Schoolchild>>(schoolchildlist, HttpStatus.OK);
    }

    // ADD CHILD
    @RequestMapping(value = "/addchild", method = RequestMethod.POST)
    public String addChild(@RequestBody Schoolchild schoolchild, @RequestParam ("teacher_id") int teacher_id){
        Teacher teacher = new Teacher();
        teacher.setId(teacher_id);
        LibraryCard libraryCard = new LibraryCard(schoolchild.getLibraryCard().getName(), schoolchild.getLibraryCard().getExpiredDate(), schoolchild.getLibraryCard().isStatus());
        Schoolchild child = new Schoolchild(schoolchild.getName(),schoolchild.getSurname(),schoolchild.getParentinfo(),schoolchild.getEmail(),schoolchild.getAddress(), teacher, libraryCard);
        teachChildDao.insertChild(child);
        teachChildDao.insertLibrary(child);
        return "adminPage";
    }

    // UPDATE CHILD
    @RequestMapping(value = "/updatechild", method = RequestMethod.POST)
    @ResponseBody
    public void updateChild(@RequestBody Schoolchild schoolchild, @RequestParam ("teacher_id") int teacher_id
    ,@RequestParam ("id") int id, @RequestParam ("idcard") int idcard){
        Teacher teacher = new Teacher();
        teacher.setId(teacher_id);
        LibraryCard library= new LibraryCard(schoolchild.getLibraryCard().getName(), schoolchild.getLibraryCard().getExpiredDate(), schoolchild.getLibraryCard().isStatus());
        library.setId(idcard);
        Schoolchild child = new Schoolchild(schoolchild.getName(),schoolchild.getSurname(),schoolchild.getParentinfo(),schoolchild.getEmail(),schoolchild.getAddress(), teacher, library);
        child.setId(id);
        teachChildDao.updateChild(child);
    }

    // DELETE CHILD
    @RequestMapping(value = "/deletechild", method = RequestMethod.POST)
    public void deleteChild(@RequestParam("id") int id){
        teachChildDao.deleteChild(id);
    }


    // GET TEACHER LIST
    @RequestMapping(value = "/allteachers", method = RequestMethod.GET)
    public ResponseEntity<List<Teacher>> getPlanetList() {
        List<Teacher> teacherlist = teachChildDao.teacherlist();
        return new ResponseEntity<List<Teacher>>(teacherlist, HttpStatus.OK);
    }

    // ADD TEACHER
    @RequestMapping(value = "/addteacher", method = RequestMethod.POST)
    public String addTeacher(@ModelAttribute Teacher teacher){
        teachChildDao.insertTeacher(teacher);
        return "adminPage";
    }

    // DELETE TEACHER
    @RequestMapping(value = "/deleteteacher", method = RequestMethod.POST)
    public void deleteTeacher(@RequestParam("id") int id){
        teachChildDao.deleteTeacher(id);
    }

    // UPDATE TEACHER
    @RequestMapping(value = "/updateteacher", method = RequestMethod.POST)
    @ResponseBody
    public void updateTeacher(@ModelAttribute Teacher teacher){
        teachChildDao.updateTeacher(teacher);
    }


    // Redirects to activitiesPage/MeniuOption (option) and gets getActivitiesChildList(address)
    @RequestMapping(value="/activities/", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView getForActivitiesJspData(HttpServletResponse response
            ,@RequestParam("activities") String address) {
        ModelAndView model = new ModelAndView("/activities/activity");
        switch (address) {
            case "football":
                List<Schoolchild> schoolchildlist = teachChildDao.getActivitiesChildList(address);
                model.addObject("schoolchildlist", schoolchildlist);
                break;
            case "basketball":
                List<Schoolchild> schoolchildlist1 = teachChildDao.getActivitiesChildList(address);
                model.addObject("schoolchildlist", schoolchildlist1);
                break;
            case "volleyball":
                List<Schoolchild> schoolchildlist2 = teachChildDao.getActivitiesChildList(address);
                model.addObject("schoolchildlist", schoolchildlist2);
                break;
        }
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        return model;
    }



    // TEST JQUERY gaunantn per @ModelAttribute, grazinant per RersponseEntity<>
    // Gaunam is Jquery informacija kaip atribudutus, o siunciam i Jquery kaip JSON
    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    public ResponseEntity<Teacher> test1(@ModelAttribute Teacher teacher){
        // debugams naudojau paziuret ar ateina informacija is Ajaxa
        // siuntimui pasirinkau savo random skaiciu
        Teacher teacherById = teachChildDao.getTeacherById(1);
        return new ResponseEntity<Teacher>(teacherById, HttpStatus.OK);
    }

    // TEST JQUERY gaunam per @ModelAttribute, grazinam per return (siuo atveju) Object, Reikia @ResponseBody siuo atveju, nes nereturnins body
    // Gaunam is Jquery informacija kaip atribudutus, o siunciam i Jquery kaip JSON
    @RequestMapping(value="/test2", method = RequestMethod.POST)
    @ResponseBody
    public Teacher test2(@ModelAttribute Teacher teacher) {
        Teacher teacherById = teachChildDao.getTeacherById(1);
        return  teacherById;
    }

    // TEST JQUERY gaunam per @RequestBody, grazinant per RersponseEntity<>
    // Gaunam is Jquery informacija kaip Json ir siunciam i Jquery kaip JSON
    @RequestMapping(value="/test3", method = RequestMethod.POST)
    public ResponseEntity<Teacher> test3(@RequestBody Teacher teacher) {
        Teacher teacherById = teachChildDao.getTeacherById(1);
        return  new ResponseEntity<Teacher>(teacherById, HttpStatus.OK);
    }

    // TEST JQUERY gaunam per @RequestBody, grazinam per return (siuo atveju) Object, Reikia @ResponseBody siuo atveju, nes nereturnins body
    // Gaunam is Jquery informacija kaip Json ir siunciam i Jquery kaip JSON
    @RequestMapping(value="/test4", method = RequestMethod.POST)
    @ResponseBody
    public Teacher test4(@RequestBody Teacher teacher) {
        Teacher teacherById = teachChildDao.getTeacherById(1);
        return  teacherById;
    }

    // TEST JQUERY siuo atveju nieko nesiunciam, o tik nusiunciam antram JSP musu pasiriktus is DB duomenis, uzklausiam antro JSP duomenu visu (HTML)
    // Gaunam is Jquery tik uzklausa ir siunciam i Jquery kaip html
    @RequestMapping(value = "/test5", method = RequestMethod.GET)
    public ModelAndView test5(){
        List<Teacher> teachersList = teachChildDao.teacherlist();
        ModelAndView model = new ModelAndView("forTeachers");
        List<Schoolchild> schoolchildlist = teachChildDao.schoolchildlist();
        model.addObject("schoolchildlist",schoolchildlist);
        return model;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/usersForCrudProject",method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Schoolchild> testChildList(@ModelAttribute Schoolchild schoolchild){
        List<Schoolchild> schoolchildlist = teachChildDao.schoolchildlist();
        return schoolchildlist;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getChildById",method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Schoolchild getChildById(@RequestParam("id") int id){
        Schoolchild childById = teachChildDao.getChildById(id);
        return childById;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/addchildfromAngular", method = RequestMethod.POST)
    public String addchildfromAngular(@RequestBody Schoolchild schoolchild, @RequestParam ("teacher_id") int teacher_id){
        Teacher teacher = new Teacher();
        teacher.setId(teacher_id);
        LibraryCard libraryCard = new LibraryCard(schoolchild.getLibraryCard().getName(), schoolchild.getLibraryCard().getExpiredDate(), schoolchild.getLibraryCard().isStatus());
        Schoolchild child = new Schoolchild(schoolchild.getName(),schoolchild.getSurname(),schoolchild.getParentinfo(),schoolchild.getEmail(),schoolchild.getAddress(), teacher, libraryCard);
        teachChildDao.insertChild(child);
        teachChildDao.insertLibrary(child);
        return "adminPage";
    }

    @RequestMapping(value = "/updatechilfromAnguldar", method = RequestMethod.POST)
    @ResponseBody
    public void updatechilfromAnguldar(@RequestBody Schoolchild schoolchild, @RequestParam ("teacher_id") int teacher_id
            ,@RequestParam ("id") int id, @RequestParam ("idcard") int idcard){
        Teacher teacher = new Teacher();
        teacher.setId(teacher_id);
        LibraryCard library= new LibraryCard(schoolchild.getLibraryCard().getName(), schoolchild.getLibraryCard().getExpiredDate(), schoolchild.getLibraryCard().isStatus());
        library.setId(idcard);
        Schoolchild child = new Schoolchild(schoolchild.getName(),schoolchild.getSurname(),schoolchild.getParentinfo(),schoolchild.getEmail(),schoolchild.getAddress(), teacher, library);
        child.setId(id);
        teachChildDao.updateChild(child);
    }


}
