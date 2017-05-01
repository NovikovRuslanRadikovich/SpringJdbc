package ru.kpfu.itis.NovikovRuslan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.NovikovRuslan.service.CitiesService;



@Controller
public class StartController {

    @Autowired
    CitiesService citiesService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCities(Model model) {
        model.addAttribute("cities", citiesService.getAllCities());
        return "start";
    }



}
