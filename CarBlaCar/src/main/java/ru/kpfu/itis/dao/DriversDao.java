package ru.kpfu.itis.dao;

import ru.kpfu.itis.model.Driver;

import java.sql.SQLException;
import java.util.List;

public interface DriversDao{

    void save(Driver driver);

    Driver findOne(Long id);

    List<Driver> findAll();

    List findTop9ByOrderByRatingDesc() throws SQLException;

}
