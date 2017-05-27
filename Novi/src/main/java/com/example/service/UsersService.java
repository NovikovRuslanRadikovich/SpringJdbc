package com.example.service;

import com.example.entities.User;

import java.util.List;


public interface UsersService {

    User findOne(Long ID);

    User save(User user);

    List<User> findFromUsernameAndPassword(String username,String password);

    List<User> findFromUsernameAndTelephone(String username, String telephone);
}
