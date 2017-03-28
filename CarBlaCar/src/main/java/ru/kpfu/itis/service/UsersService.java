package ru.kpfu.itis.service;

import ru.kpfu.itis.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersService {
    User addUser(User user) throws SQLException;

    User findById(Long id);

    void update(User user) throws SQLException;

    List<User> findAll();

    User findByNickname(String name);

    User findByEmail(String email);
}
