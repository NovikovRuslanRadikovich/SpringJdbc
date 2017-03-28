package components;

import Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by розалия on 19.03.2017.
 */
@Controller
public class MainController {

    @Autowired
    private DaoImplController daoImplController;

    @Autowired
    HttpServletRequest request;


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getPeople(Model model) {
    //   request.getSession().getAttribute("isAllowed");
        if(daoImplController.getAll() == null){
            model.addAttribute("quantity",0);
            model.addAttribute("allPeople",new Person(1,"a","b","c"));
        } else  if(daoImplController.getAll() != null){
            model.addAttribute("quantity",daoImplController.getAll().size());
            model.addAttribute("allPeople", daoImplController.getAll());
        }
        return "allPeople";
    }

    @RequestMapping(value="/newContact",method = RequestMethod.GET)
    public String newContact(Model model) {
        return "newContact";
    }

    @RequestMapping(value="/newContact",method = RequestMethod.POST)
    public String newContactAdded( @RequestParam(value = "country",required = false) String country,
                                   @RequestParam(value = "name",required = false) String name,
                                   @RequestParam(value = "surname",required = false) String surname, Model model) {

        daoImplController.addPerson(country,name,surname);
        String redirectUrl = "/";
        return "redirect:" + redirectUrl;
    }


    @RequestMapping(value="/editContact/{id}",method = RequestMethod.GET)
    public String editContact(@PathVariable("id") int id, Model model) {
        Person person = daoImplController.getById(id);
        model.addAttribute("person",person);
        return "editContact";
    }

    @RequestMapping(value="/editContact/{id}",method = RequestMethod.POST)
    public String editedContact(@PathVariable("id") int id,
                                @RequestParam(value="country",required = false) String country,
                                @RequestParam(value="name", required = false) String name,
                                @RequestParam(value="surname",required = false) String surname,
                                Model model) {
              daoImplController.updatePerson(id,country,name,surname);
              String redirectUrl = "/";
              return "redirect:" + redirectUrl;
    }

    @RequestMapping(value="/deleteContact/{id}",method = RequestMethod.GET)
    public String deleteContact(@PathVariable("id") int id, Model model){
        daoImplController.deletePerson(id);
        model.addAttribute("allPeople",daoImplController.getAll());
        String redirectUrl = "/";
        return "redirect:" + redirectUrl;
    }
}
