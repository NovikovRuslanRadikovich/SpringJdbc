package ru.kpfu.itis.dao;

import ru.kpfu.itis.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao {

    void save(User user) throws SQLException;

    User findOne(Long userId);

    List<User> findAll();

    User findByNickname(String nickname);

    User findByEmail(String email);

    User findByNicknameIgnoreCase(String name) throws SQLException;

    User findByEmailIgnoreCase(String email);
}
