package ru.kpfu.itis.NovikovRuslan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.NovikovRuslan.entities.User;
import ru.kpfu.itis.NovikovRuslan.repository.hibernateImpl.UsersDaoHibernateImpl;



@Service
public class UserService {
    @Autowired
    UsersDaoHibernateImpl usersDaoHibernateImpl;

    public void saveUser(User user) {usersDaoHibernateImpl.saveUser(user);}

    public User findUser(String username){return usersDaoHibernateImpl.findUser(username);}
}
