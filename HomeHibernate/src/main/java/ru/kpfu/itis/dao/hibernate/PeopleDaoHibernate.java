package ru.kpfu.itis.dao.hibernate;

import ru.kpfu.itis.dao.factory.HibernateConnectionFactory;
import ru.kpfu.itis.entities.Person;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by розалия on 29.03.2017.
 */
@Repository
public class PeopleDaoHibernate {

    public List<Person> getAll() {
        Session session = null;
        List people = new ArrayList();
        try {
            session = HibernateConnectionFactory.getSessionFactory().openSession();
            people = session.createCriteria(Person.class).list();
        } catch(Exception e) {
            System.out.println("Unavailable to get all:" + e.getCause());
        } finally{
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
        return people;
    }

    public void updatePerson(int id,String country,String name,String surname) {
          Session session =  null;
          Person person = null;
          try {
              session = HibernateConnectionFactory.getSessionFactory().openSession();
              person = new Person(id,country,name,surname);
              session.beginTransaction();
              session.update(person);
              session.getTransaction().commit();
          }catch(Exception e) {
              System.out.println("Unavailable to update Person:" + e.getCause());
          } finally{
              if(session != null && session.isOpen()) {
                  session.close();
              }
          }
    }

    public void deletePerson(int id) {
          Session session = null;
          try{
              session = HibernateConnectionFactory.getSessionFactory().openSession();
              Query query = session.createQuery("delete Person where id = :ide" );
              query.setParameter("ide",id);
              int result = query.executeUpdate();
              System.out.println("fuckCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
          } catch(Exception e) {
              System.out.println("Unavailable to delete Person:" + e.getCause());
          } finally{
              if(session != null && session.isOpen()) {
                  session.close();
              }
          }
    }

    public void addPerson(String country,String name,String surname) {
        Session session = null;

        try{
            session = HibernateConnectionFactory.getSessionFactory().openSession();
            Person person = new Person(1,country,name,surname);
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
        } catch(Exception e) {
            System.out.println("Unavailable to save person " + e.getCause());
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Person getById(int id) {
        Session session = null;
        Person person = null;
        try{
            session = HibernateConnectionFactory.getSessionFactory().openSession();
            person = (Person) session.createCriteria(Person.class).add(Restrictions.eq("id",id)).setMaxResults(1).uniqueResult();
        } catch(Exception e) {
            System.out.println("Unavailable to delete Person:" + e.getCause());
        } finally{
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
        return person;
    }
}
