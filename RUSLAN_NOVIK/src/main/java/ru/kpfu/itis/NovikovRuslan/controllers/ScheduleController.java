package ru.kpfu.itis.NovikovRuslan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.NovikovRuslan.entities.Schedule;
import ru.kpfu.itis.NovikovRuslan.service.SchedulesService;


@Controller
public class ScheduleController {
    @Autowired
    SchedulesService schedulesService;

    @RequestMapping(value = "/follow/{cityId:\\d+}/{polyclinicId:\\d+}/{doctorId:\\d+}")
    public String getSchedule(@PathVariable("cityId") Long cityId,
                              @PathVariable("polyclinicId") Long polyclinicId,
                              @PathVariable("doctorId") Long doctorId,Model model) {
        System.out.println("YYYYYYYYYYYY");
        System.out.println(doctorId);
        Schedule schedule = schedulesService.getSchedule(doctorId);
        System.out.println("AAAAAAAAAAAAAAAAAAAA");
        model.addAttribute("cityId",cityId);
        model.addAttribute("polyclinicId",polyclinicId);
        model.addAttribute("schedules",schedule);

        return "schedule";
    }
}
