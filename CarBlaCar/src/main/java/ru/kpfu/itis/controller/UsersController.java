package ru.kpfu.itis.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.dao.UsersDao;
import ru.kpfu.itis.dao.impl.jdbc.UserDaoJdbcImpl;
import ru.kpfu.itis.forms.UserForm;
import ru.kpfu.itis.model.*;
import ru.kpfu.itis.service.AutosService;
import ru.kpfu.itis.service.DriversService;
import ru.kpfu.itis.service.PassengersService;
import ru.kpfu.itis.service.UsersService;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import ru.kpfu.itis.service.impl.UsersServiceImpl;
import ru.kpfu.itis.validator.UserFormValidator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;




@Controller
public class UsersController extends HttpServlet implements UsersService {

    private UsersDao usersDao;

    static User user = new User("","","","","","","");

    @Autowired
    UsersService usersService;

    @Autowired
    DriversService driversService;

    @Autowired
    AutosService autosService;

    @Autowired
    PassengersService passengersService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    UserFormValidator userFormValidator;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        Long sessionUid = (Long) request.getSession().getAttribute("session_uid");
        if(sessionUid != null) {
            return "redirect:/";
        }
        return "login";
    }

    public static String md5Apache(String st) {
        String md5Hex = DigestUtils.md5Hex(st);
        return md5Hex;
    }



     @RequestMapping(value="/login", method = RequestMethod.POST)
     public String authorization(Model model) {
         String passwordSalt = md5Apache(request.getParameter("password"));
    /*
    TODO: обработать форму авторизации. Использовать метод findByNickname у userService.
    При авторизации нужно положить session_uid в сессию, для маяка, что пользователь уже авторизован
     */
         if (findByNickname(request.getParameter("name")).getPassword().equals(passwordSalt)) {
             request.getSession().setAttribute("session_uid", 12.0);
             return "home";
         }
         return "login";


     }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationGet(ModelMap modelMap) {
        Long sessionUid = (Long) request.getSession().getAttribute("session_uid");
        if(sessionUid != null) {
            return "redirect:/";
        }
        modelMap.put("regForm", new UserForm());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationPost(@ModelAttribute("user") UserForm regForm, BindingResult result,
                                   @RequestParam(value="avatar",required = false) MultipartFile avatar,
                                   HttpServletRequest request) throws IOException, ServletException, FileUploadException {
        userFormValidator.validate(regForm,result);
                if(result.hasErrors()) {
                    return "registration";
                } else {
                    if(!avatar.isEmpty()) {
                    //    validateImage(avatar);
                        DiskFileItemFactory factory = new DiskFileItemFactory();
                        factory.setSizeThreshold(1024*1024);
                        File repository = (File) getServletConfig().getServletContext().getAttribute("javax.servlet.context.tempdir");
                        factory.setRepository(repository);
                        ServletFileUpload upload = new ServletFileUpload(factory);
                        upload.setSizeMax(1024*1024*3);
                        try{
                            List<FileItem> fileItems = upload.parseRequest(request);
                            Iterator iterator = fileItems.iterator();
                            if(iterator.hasNext()) {
                                do{
                                    FileItem item = (FileItem) iterator.next();
                                    if(item.isFormField()){
                             //           processFormField(item);
                                    } else{
                                      processUploadedFile(item);
                                    }
                                } while(iterator.hasNext());
                            }
                        } catch(Exception ex) {
                            ex.printStackTrace();
                        }

                        try {
                            usersDao = new UserDaoJdbcImpl();

                            usersDao.save(user);

                             user = new User("","","","","","","");

                        } catch (SQLException e) {

                            e.printStackTrace();

                        }
                    }

                }


        /*TODO: сохранить пользователя в бд.
        По дефолту, новый пользователь является пассажиром.
            пароль должен храниться в зашифрованном виде.
         */

        return "redirect:/login";
    }

    private String processUploadedFile(FileItem item) throws Exception {

        String path = "C:\\Users\\ruslan\\Desktop\\CarBlaCar\\src\\main\\webapp\\imagesOfusers\\"  + item.getString() + ".jpg";

        File uploadedFile = new File(path);


        uploadedFile.createNewFile();


        item.write(uploadedFile);


        return item.getName();

    }

    private void processFormField(FileItem item) {

        String fieldName = item.getFieldName();


        switch(fieldName) {

            case "password":

                user.setPassword(item.getString());

                break;

            case "role":

                user.setRole(item.getString());

                break;

            case "email":

                user.setEmail(item.getString());

                break;
            case "firstname":

                user.setFirstname(item.getString());

                break;

            case "surname":
                user.setSurname(item.getString());

                break;

            case "nickname":

                user.setNickname(item.getString());

        }


    }

//    private void validateImage(MultipartFile image) {
//        if(!image.getContentType().equals("image/jpeg")) {
//            throw new ImageUploadExcepion("Only JPG images accepted");
//        }
//    }

    private void saveImage(String filename, MultipartFile image)  {

    }

    @RequestMapping(value = "/users/{userId:\\d+}", method = RequestMethod.GET)
    public String profile(ModelMap modelMap, @PathVariable Long userId
    ) {
        Long sessionUid = (Long) request.getSession().getAttribute("session_uid");
        if(sessionUid == null) {
            return "login";
        }
        User userInfo = usersService.findById(userId);
        modelMap.put("userinfo", userInfo);
        if (userInfo.getDriver() != null && userInfo.getDriver().getTrips().size() > 0) {
            List<Trip> tripList = userInfo.getDriver().getTrips();
            List<Trip> driverTrips = new ArrayList<>();
            List<Trip> endDriverTrips = new ArrayList<>();
            for (Trip trip : tripList) {
                if (trip.getStatus().contains("Ожидание")) {
                    driverTrips.add(trip);
                } else if (trip.getStatus().contains("Завершено")) {
                    endDriverTrips.add(trip);
                }
            }
            modelMap.put("driverTrips", driverTrips);
            modelMap.put("endDriverTrips", endDriverTrips);

        }


        List<Trip> tripList = userInfo.getPassenger().getTrips();
        List<Trip> pasTrips = new ArrayList<>();
        List<Trip> endPasTrips = new ArrayList<>();
        for (Trip trip : tripList) {
            if (trip.getStatus().contains("Ожидание")) {
                pasTrips.add(trip);
            } else if (trip.getStatus().contains("Завершено")) {
                endPasTrips.add(trip);
            }
        }
        modelMap.put("pasTrips", pasTrips);
        modelMap.put("endPasTrips", endPasTrips);

        if (userInfo.getDriver() != null) {
            tripList = userInfo.getDriver().getTrips();
            List<Trip> driverTrips = new ArrayList<>();
            for (Trip trip : tripList) {
                if (trip.getStatus().contains("Ожидание")) {
                    driverTrips.add(trip);
                }
            }
            List<Booking> bookings = new ArrayList<>();
            for (Trip trip : driverTrips) {
                for (Booking booking : trip.getBookings()) {
                    if (booking.getConfirm() == null) {
                        bookings.add(booking);
                    }
                }
            }
            modelMap.put("bookings", bookings);
        }

        return "profile";
    }

    @RequestMapping(value = "/newauto", method = RequestMethod.GET)
    public String newAutoPage() {
        return "newauto";
    }

    @RequestMapping(value = "/newauto", method = RequestMethod.POST)
    public String newAuto(@ModelAttribute Automobile automobile) {
        Long userId = (Long) request.getSession().getAttribute("session_uid");
        if(userId == null) {
            return "login";
        }
        User user = usersService.findById(userId);
        Driver driver = user.getDriver();
        if (driver == null) {
            driver = new Driver();
            driver.setUser(user);
            driver.setRating(0);
            driver.setAutomobileList(new ArrayList<Automobile>());
            driver.setExperience(0);
            driversService.addDriver(driver);
            user.setDriver(driver);
            user.setRole("DRIVER");
            try {
                usersService.update(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        automobile.setDriver(driver);
        autosService.addAuto(automobile);
        return "redirect:/users/" + user.getId();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        request.getSession().removeAttribute("session_uid");
        return "redirect:/";
    }

    @Override
    public User addUser(User user) throws SQLException {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public void update(User user) throws SQLException {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByNickname(String name) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}

