package ru.kpfu.itis.NovikovRuslan.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.NovikovRuslan.entities.Schedule;
import ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.SchedulesDaoHibernateImpl;



@Service
public class SchedulesService {
    @Autowired
    SchedulesDaoHibernateImpl schedulesDao;

    public void saveSchedule(Schedule schedule) {schedulesDao.saveSchedule(schedule);}

    public Schedule getSchedule(Long doctorId) { return schedulesDao.getSchedule(doctorId);}
}
