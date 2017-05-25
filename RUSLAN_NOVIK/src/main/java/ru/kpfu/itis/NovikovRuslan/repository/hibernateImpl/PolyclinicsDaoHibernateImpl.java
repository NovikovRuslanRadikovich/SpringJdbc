package ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl;



import org.apache.log4j.Logger;
import org.hibernate.Session;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.NovikovRuslan.entities.City;

import ru.kpfu.itis.NovikovRuslan.entities.Polyclinic;
import ru.kpfu.itis.NovikovRuslan.repository.factory.HibernateUtil;


import java.util.List;

@Repository
public class PolyclinicsDaoHibernateImpl {

    public List getAllDoctors(Long polyclinic_id){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Polyclinic polyclinic = getPolyclinic(polyclinic_id);
       City city = polyclinic.getPolyclinic_city();
       String polyclinic_name = polyclinic.getPolyclinic_name();
       String city_name = city.getName();
       Query query =  session.createQuery("from Doctor where city_name=:city_name AND" +
                " polyclinic_name=:polyclinic_name").
                setParameter("city_name",city_name).
                setParameter("polyclinic_name",polyclinic_name);
        return query.list();
    }

   @Transactional
    public static void deletePolyclinic(Long polyclinic_id) {
       Session session = null;
       Polyclinic polyclinic = null;
       try{
           session = HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
           polyclinic = (Polyclinic) session.createQuery("from Polyclinic where id=:id").setParameter("id",polyclinic_id).setMaxResults(1).uniqueResult();
           session.delete(polyclinic);
           session.getTransaction().commit();
       } catch(Exception e) {
           e.printStackTrace();
       } finally{
           if (session != null && session.isOpen()) {
               session.close();
           }
       }
    }
   @Transactional
    public  void savePolyclinic(Polyclinic polyclinic){
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(polyclinic);
            session.getTransaction().commit();
        } catch(Exception e){
           e.printStackTrace();
        } finally{
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public Polyclinic getPolyclinic(Long polyclinicId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Polyclinic polyclinic = (Polyclinic) session.createQuery("from Polyclinic where id=:id").setParameter("id",polyclinicId).setMaxResults(1).uniqueResult();
        return polyclinic;
    }

    public Polyclinic getPolyclinic(String doctor_polyclinic) {
        Session session = null;
        Polyclinic polyclinic = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            polyclinic = (Polyclinic)session.createQuery("from Polyclinic where polyclinic_name =:name").setParameter("name",doctor_polyclinic).setMaxResults(1).uniqueResult();
        } catch(Exception e) {
           e.printStackTrace();
        } finally{
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
        return polyclinic;
    }
}
