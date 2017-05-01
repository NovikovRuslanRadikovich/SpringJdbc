package ru.kpfu.itis.NovikovRuslan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.NovikovRuslan.entities.User;
import ru.kpfu.itis.NovikovRuslan.enumeration.UserRole;
import ru.kpfu.itis.NovikovRuslan.forms.UserForm;
import ru.kpfu.itis.NovikovRuslan.service.UserService;

import javax.validation.Valid;
import java.security.Principal;



@Controller
public class RegistrationController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationGet(ModelMap modelMap, Principal principal){
        if(principal != null) {
            return "redirect:/";
        }
        modelMap.put("regForm",new UserForm());
        return "registration";
    }

    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public String registrationPost(@ModelAttribute("user") @Valid UserForm regForm,
                                   BindingResult bindingResult,ModelMap modelMap){
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("errors",bindingResult.getAllErrors());
            return "registration";
        }

        if(!(userService.findUser(regForm.getUsername()) == null)) {
            modelMap.addAttribute("existingName", "User with this name already exists");
            return "registration";
        }

            User user2 = new User(regForm.getUsername(), regForm.getPassword(), UserRole.ROLE_USER);
            userService.saveUser(user2);

        return "redirect:/";
    }
}
