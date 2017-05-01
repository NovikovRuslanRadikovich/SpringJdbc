package ru.kpfu.itis.NovikovRuslan.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;



import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.NovikovRuslan.entities.Doctor;
import ru.kpfu.itis.NovikovRuslan.entities.Polyclinic;

import ru.kpfu.itis.NovikovRuslan.service.PolyclinicsService;

import java.util.List;



@Controller("/follow")
public class DoctorController {

    @Autowired
    PolyclinicsService polyclinicsService;

    @RequestMapping(value = "/follow/{cityId:\\d+}/{polyclinicId:\\d+}", method = RequestMethod.GET)
    public String getDoctors(@PathVariable("cityId") Long cityId, @PathVariable("polyclinicId") Long polyclinicId, Model model){
        Polyclinic polyclinic = polyclinicsService.getPolyclinic(polyclinicId);

        List<Doctor> doctors = polyclinicsService.getAllDoctors(polyclinicId);
        System.out.println(doctors.size());
        model.addAttribute("cityId",cityId);
        System.out.println(cityId);
        model.addAttribute("polyclinicId",polyclinicId);
        System.out.println(polyclinicId);
        model.addAttribute("doctors", doctors);
        return "doctors";
    }

}
