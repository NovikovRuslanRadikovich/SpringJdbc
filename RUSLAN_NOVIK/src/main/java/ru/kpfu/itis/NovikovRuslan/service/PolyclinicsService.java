package ru.kpfu.itis.NovikovRuslan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.NovikovRuslan.entities.Doctor;
import ru.kpfu.itis.NovikovRuslan.entities.Polyclinic;
import ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.PolyclinicsDaoHibernateImpl;

import java.util.List;



@Service
public class PolyclinicsService {
    @Autowired
    PolyclinicsDaoHibernateImpl polyclinicDao;

    public List<Doctor> getAllDoctors(Long polyclinic_id){ return polyclinicDao.getAllDoctors(polyclinic_id);}

    public void deletePolyclinic(Long polyclinic_id) { polyclinicDao.deletePolyclinic(polyclinic_id);}

    public void savePolyclinic(Polyclinic polyclinic){polyclinicDao.savePolyclinic(polyclinic);}

    public Polyclinic getPolyclinic(Long id){return polyclinicDao.getPolyclinic(id);}

    public Polyclinic getPolyclinic(String doctor_polyclinic) { return polyclinicDao.getPolyclinic(doctor_polyclinic);}

}

