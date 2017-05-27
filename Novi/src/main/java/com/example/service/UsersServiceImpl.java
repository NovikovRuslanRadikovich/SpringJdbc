package com.example.service;

import com.example.entities.User;
import com.example.entities.specifications.UserSpecs;
import com.example.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @PostConstruct
    public void generateTestData() {
        save(new User("admin","admin","123"));
        save(new User("ruslan","ruslan","123"));
        save(new User("sidney","crosby","123"));
        save(new User("evgeny","malkin","123"));
        save(new User("vladimir","tarasenko","123"));
    }


    @Override
    public User findOne(Long ID) {
        return usersRepository.findOne(ID);
    }

    @Override
    public User save(User user) {
        return usersRepository.save(user);
    }

    @Override
    public List<User> findFromUsernameAndPassword(String username, String password) {
        return usersRepository.findAll(Specifications.where(UserSpecs.checkParams(username,password)));
    }

    @Override
    public List<User> findFromUsernameAndTelephone(String username, String telephone) {
        return usersRepository.findAll(Specifications.where(UserSpecs.checkParams2(username,telephone)));
    }
}
