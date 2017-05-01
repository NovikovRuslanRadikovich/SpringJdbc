package ru.kpfu.itis.NovikovRuslan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.NovikovRuslan.entities.Doctor;
import ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.DoctorsDaoHibernateImpl;



@Service
public class DoctorsService {
    @Autowired
    DoctorsDaoHibernateImpl doctorsDao;

    public void saveDoctor(Doctor doctor) {doctorsDao.saveDoctor(doctor);}

    public void deleteDoctor(Long id) {doctorsDao.deleteDoctor(id);}


}
