package ru.kpfu.itis.NovikovRuslan.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.NovikovRuslan.entities.City;
import ru.kpfu.itis.NovikovRuslan.entities.Doctor;
import ru.kpfu.itis.NovikovRuslan.entities.Polyclinic;
import ru.kpfu.itis.NovikovRuslan.entities.Schedule;
import ru.kpfu.itis.NovikovRuslan.service.CitiesService;
import ru.kpfu.itis.NovikovRuslan.service.DoctorsService;
import ru.kpfu.itis.NovikovRuslan.service.PolyclinicsService;
import ru.kpfu.itis.NovikovRuslan.service.SchedulesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller("/admin")
public class AdminController {
   @Autowired
   CitiesService citiesService;

   @Autowired
   PolyclinicsService polyclinicsService;

   @Autowired
   DoctorsService doctorsService;

   @Autowired
   SchedulesService schedulesService;

   @RequestMapping(method = RequestMethod.POST)
   public String new_city(HttpServletRequest request,Model model) {
       if(request.getParameter("name") != null) {
           String city_name = request.getParameter("name");
           citiesService.saveCity(new City(city_name));
           model.addAttribute("cities", citiesService.getAllCities());

       } else if(request.getParameter("polyclinic_city") != null){
           String polyclinic_city = request.getParameter("polyclinic_city");
           City city = citiesService.getCity(polyclinic_city);
           polyclinicsService.savePolyclinic(new Polyclinic(request.getParameter("polyclinic_name"),
                   request.getParameter("polyclinic_address"),city));
           model.addAttribute("cities", citiesService.getAllCities());
       }
       return "start";
   }

   @RequestMapping(value = "/admin/new_doctor", method = RequestMethod.POST)
   public String new_doctor(HttpServletRequest request, Model model) {
          String doctor_city = request.getParameter("doctor_city");
          String doctor_polyclinic = request.getParameter("doctor_polyclinic");
          String fio = request.getParameter("fio");
          String specialization = request.getParameter("specialization");
          String regalia = request.getParameter("regalia");
          String telephone = request.getParameter("telephone");
          String monday = request.getParameter("monday_schedule");
          String tuesday = request.getParameter("tuesday_schedule");
          String wednesday = request.getParameter("wednesday_schedule");
          String thursday = request.getParameter("thursday_schedule");
          String friday = request.getParameter("friday_schedule");
          String saturday = request.getParameter("saturday_schedule");
          String sunday = request.getParameter("sunday_schedule");

          City city = citiesService.getCity(doctor_city);
          Polyclinic polyclinic = polyclinicsService.getPolyclinic(doctor_polyclinic);
          Doctor doctor = new Doctor(city,polyclinic,fio,specialization,regalia,telephone,doctor_city,doctor_polyclinic);
          Schedule schedule = new Schedule(doctor,monday,tuesday,wednesday,thursday,friday,saturday,sunday);

          doctorsService.saveDoctor(doctor);
          schedulesService.saveSchedule(schedule);

       model.addAttribute("cities", citiesService.getAllCities());
          return "start";
   }

    @RequestMapping(value = "/admin/delete_polyclinic/{id:\\d+}", method = RequestMethod.GET)
    public void delete_polyclinic(@PathVariable("id") Long polyclinic_id, HttpServletResponse response) throws IOException {
       polyclinicsService.deletePolyclinic(polyclinic_id);
       response.sendRedirect("/");
   }

    @RequestMapping(value = "/admin/delete_city/{id:\\d+}",method = RequestMethod.GET)
    public void delete_city(@PathVariable("id") Long city_id, HttpServletResponse response) throws IOException {
       citiesService.deleteCity(city_id);
       response.sendRedirect("/");

    }

    @RequestMapping(value = "/admin/delete_doctor/{id:\\d+}", method = RequestMethod.GET)
    public void delete_doctor(@PathVariable("id") Long doctor_id,HttpServletResponse response) throws IOException  {
       doctorsService.deleteDoctor(doctor_id);
       response.sendRedirect("/");
   }
}
