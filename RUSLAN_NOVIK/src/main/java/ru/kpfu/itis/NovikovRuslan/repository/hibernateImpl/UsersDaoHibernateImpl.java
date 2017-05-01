package ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl;








import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;



import org.springframework.stereotype.Repository;
import ru.kpfu.itis.NovikovRuslan.entities.User;
import ru.kpfu.itis.NovikovRuslan.enumeration.UserRole;
import ru.kpfu.itis.NovikovRuslan.repository.factory.HibernateUtil;

import javax.transaction.Transactional;



@Repository
public class UsersDaoHibernateImpl {
    private static final Logger logger = Logger.getLogger(UsersDaoHibernateImpl.class);

    @Transactional
    public  void saveUser(User user) {
        Session session = null;
        if("admin".equals(user.getPassword()) && "admin".equals(user.getUsername())){
            user.setRole(UserRole.ROLE_ADMIN);
        } else{
            user.setRole(UserRole.ROLE_USER);
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        try{
           session = HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
           session.save(user);
           session.getTransaction().commit();
        } catch(Exception e) {
           logger.error("error saving user",e.getCause());
        } finally{
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public User findUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (User) session.createQuery("from User where username =:name").setParameter("name",username).
                setMaxResults(1).uniqueResult();
    }
}
