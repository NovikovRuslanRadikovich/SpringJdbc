package ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl;


import org.apache.log4j.Logger;
import org.hibernate.Session;

import org.springframework.stereotype.Repository;
import ru.kpfu.itis.NovikovRuslan.entities.Doctor;

import ru.kpfu.itis.NovikovRuslan.repository.factory.HibernateUtil;

import javax.transaction.Transactional;


@Repository
public class DoctorsDaoHibernateImpl {
    private static final Logger logger = Logger.getLogger(DoctorsDaoHibernateImpl.class);

    @Transactional
    public void saveDoctor(Doctor doctor) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(doctor);
            session.getTransaction().commit();
        }catch(Exception e) {
           logger.error("error saving doctor",e.getCause());
        } finally{
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }
    @Transactional
    public void deleteDoctor(Long id) {
        Session session = null;
        Doctor doctor = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            doctor = (Doctor) session.createQuery("from Doctor where id=:id").setParameter("id",id).setMaxResults(1).uniqueResult();
            session.delete(doctor);
            session.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
        } finally{
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
