package ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl;

import org.apache.log4j.Logger;
import org.hibernate.Session;


import org.springframework.stereotype.Repository;

import ru.kpfu.itis.NovikovRuslan.entities.City;
import ru.kpfu.itis.NovikovRuslan.repository.factory.HibernateUtil;


import javax.transaction.Transactional;
import java.util.List;



@Repository
public class CitiesDaoHibernateImpl {
private static final Logger logger = Logger.getLogger(CitiesDaoHibernateImpl.class);
    public static List getAllCities(){
        Session session = null;
        List cities = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            cities = session.createQuery("From City").list();

        } catch(Exception e) {
            System.out.println(e.getCause());
        } finally{
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cities;
    }

    @Transactional
    public static void saveCity(City city) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(city);
            session.getTransaction().commit();
        }catch(Exception e) {
            System.out.println(e.getCause());
        } finally{
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Transactional
    public static void deleteCity(Long id) {
       Session session = null;
       City city = null;
       try{
           session = HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
           city = (City) session.createQuery("from City where id=:id").setParameter("id",id).setMaxResults(1).uniqueResult();
           session.delete(city);
           session.getTransaction().commit();

       } catch(Exception e) {
           e.printStackTrace();
       }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public City getCity(Long id) {
        Session session = null;
        City city = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            city = (City) session.createQuery("from City where id=:id").setParameter("id",id).setMaxResults(1).uniqueResult();
        } catch(Exception e) {
           System.out.println(e.getCause());
        } finally{
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
        return city;
    }

    public City getCity(String name) {
        Session session = null;
        City city = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            city = (City) session.createQuery("from City where name=:cityname").setParameter("cityname",name).setMaxResults(1).uniqueResult();
        } catch(Exception e) {
           e.printStackTrace();
        } finally{
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
        return city;
    }
}
