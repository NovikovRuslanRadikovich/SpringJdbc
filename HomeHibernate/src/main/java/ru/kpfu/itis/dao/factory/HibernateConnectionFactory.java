package ru.kpfu.itis.dao.factory;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

/**
 * Created by розалия on 29.03.2017.
 */
public class HibernateConnectionFactory {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try{
            return new Configuration().configure().buildSessionFactory();
        } catch(Throwable ex) {
            System.err.println("Initial SessionFactory creation failed :" + ex.getCause());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static void shutdown(){
        getSessionFactory().close();
    }

}
