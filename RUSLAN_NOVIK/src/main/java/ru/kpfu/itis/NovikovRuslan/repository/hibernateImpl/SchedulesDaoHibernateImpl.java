package ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl;


import org.apache.log4j.Logger;
import org.hibernate.Session;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.NovikovRuslan.entities.Doctor;
import ru.kpfu.itis.NovikovRuslan.entities.Schedule;
import ru.kpfu.itis.NovikovRuslan.repository.factory.HibernateUtil;




@Repository
public class SchedulesDaoHibernateImpl {

   @Transactional
    public void saveSchedule(Schedule schedule) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(schedule);
            session.getTransaction().commit();
        }catch(Exception e){
           e.printStackTrace();
        } finally{
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Schedule getSchedule(Long doctorId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Doctor doctor =(Doctor) session.createQuery("from Doctor where id=:doctorId").setParameter("doctorId",doctorId).setMaxResults(1).uniqueResult();
        return doctor.getSchedule();
    }


}
