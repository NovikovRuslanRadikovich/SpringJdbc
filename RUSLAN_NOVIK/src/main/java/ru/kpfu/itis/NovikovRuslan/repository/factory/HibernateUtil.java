package ru.kpfu.itis.NovikovRuslan.repository.factory;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;


import org.hibernate.cfg.Configuration;





public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() throws HibernateException {
        try {
//         Configuration configuration = new Configuration().configure();
//         StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
//                 applySettings(configuration.getProperties());
//        return configuration.buildSessionFactory(builder.build());
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            System.out.println("NAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHH");
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {

        getSessionFactory().close();
    }
}
