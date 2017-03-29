package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.entities.Person;
import ru.kpfu.itis.service.PersonService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by розалия on 29.03.2017.
 */
@Controller
public class MainController {

    @Autowired
    private PersonService personService;

    @Autowired
    HttpServletRequest request;


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getPeople(Model model) {
        //   request.getSession().getAttribute("isAllowed");
        if(personService.getAll() == null){
            model.addAttribute("quantity",0);
            model.addAttribute("allPeople",new Person(1,"a","b","c"));
        } else  if(personService.getAll() != null){
            model.addAttribute("quantity",personService.getAll().size());
            model.addAttribute("allPeople", personService.getAll());
        }
        return "allPeople";
    }

    @RequestMapping(value="/newContact",method = RequestMethod.GET)
    public String newContact() {
        return "newContact";
    }

    @RequestMapping(value="/newContact",method = RequestMethod.POST)
    public String newContactAdded( @RequestParam(value = "country",required = false) String country,
                                   @RequestParam(value = "name",required = false) String name,
                                   @RequestParam(value = "surname",required = false) String surname) {

        personService.addPerson(country,name,surname);
        String redirectUrl = "/";
        return "redirect:" + redirectUrl;
    }


    @RequestMapping(value="/editContact/{id}",method = RequestMethod.GET)
    public String editContact(@PathVariable("id") int id, Model model) {
        Person person = personService.getById(id);
        model.addAttribute("person",person);
        return "editContact";
    }

    @RequestMapping(value="/editContact/{id}",method = RequestMethod.POST)
    public String editedContact(@PathVariable("id") int id,
                                @RequestParam(value="country",required = false) String country,
                                @RequestParam(value="name", required = false) String name,
                                @RequestParam(value="surname",required = false) String surname,
                                Model model) {
        personService.updatePerson(id,country,name,surname);
        String redirectUrl = "/";
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value="/deleteContact/{id}",method = RequestMethod.GET)
    public String deleteContact(@PathVariable("id") int id, Model model){
        personService.deletePerson(id);
        model.addAttribute("allPeople",personService.getAll());
        String redirectUrl = "/";
        return "redirect:" + redirectUrl;
    }
}
