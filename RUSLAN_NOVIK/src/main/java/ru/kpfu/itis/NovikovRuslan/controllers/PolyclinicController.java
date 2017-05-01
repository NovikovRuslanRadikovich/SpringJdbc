package ru.kpfu.itis.NovikovRuslan.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.NovikovRuslan.entities.City;
import ru.kpfu.itis.NovikovRuslan.service.CitiesService;




@Controller
public class PolyclinicController {

    @Autowired
    CitiesService citiesService;

    @RequestMapping(value = "/follow/{cityId:\\d+}", method = RequestMethod.GET)
    public String getPolyclinicsFromCity(@PathVariable("cityId") Long cityId,ModelMap model){
        City city = citiesService.getCity(cityId);
        model.addAttribute("polyclinics",city.getPolyclinics());
        model.addAttribute("cityId",cityId);
        return "hospitals";
    }
}
