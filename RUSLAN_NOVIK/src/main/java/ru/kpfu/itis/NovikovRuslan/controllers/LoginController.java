package ru.kpfu.itis.NovikovRuslan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;


@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Principal principal){
        if(principal != null) {
            return "redirect:/";
        }
        return "login";
    }
}
