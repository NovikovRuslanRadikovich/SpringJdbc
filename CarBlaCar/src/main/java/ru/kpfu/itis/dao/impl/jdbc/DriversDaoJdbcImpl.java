package ru.kpfu.itis.dao.impl.jdbc;

import ru.kpfu.itis.dao.DriversDao;
import ru.kpfu.itis.dao.factory.JDBCConnectionFactory;
import ru.kpfu.itis.model.Automobile;
import ru.kpfu.itis.model.Driver;
import ru.kpfu.itis.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriversDaoJdbcImpl implements DriversDao {

    Connection con = null;
    Statement stmt = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    private Connection getConnection() throws SQLException {
        return JDBCConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void save(Driver driver) {

    }

    @Override
    public Driver findOne(Long id) {
        return null;
    }

    @Override
    public List<Driver> findAll() {
        return null;
    }

    @Override
    public List<Driver> findTop9ByOrderByRatingDesc() throws SQLException {
        con = getConnection();
        stmt = con.createStatement();
        List<Driver> drivers = null;
        User user = null;
        rs =  stmt.executeQuery("Select * from drivers ORDER by rating DESC ");
        int num = 0;
        while(rs.next() && num < 9) {
            num++;
            List<Automobile> list = new ArrayList();
             user = null;

            Statement stmt2 = con.createStatement();

            ResultSet rs2 = stmt2.executeQuery("Select(nickname,password,firstname,surname,email,role,avatar) from users where id = '" + Integer.toString(rs.getInt("user_id")) + "'");
            user = new User(rs2.getString("nickname"),
                    rs2.getString("password"),
                    rs2.getString("firstname"),
                    rs2.getString("surname"),
                    rs2.getString("email"),
                    rs2.getString("role"),
                    rs2.getString("avatar")
                    );

            Statement stmt3 = con.createStatement();
            ResultSet rs3 = stmt3.executeQuery("Select(brand,model,license_plate,age,driver_id) from autos where driver_id = '" + Integer.toString(rs.getInt("id"))+ "'");
            while(rs3.next()) {
                Automobile automobile = new Automobile(
                        rs3.getString("brand"),
                        rs3.getString("model"),
                        rs3.getString("license_plate"),
                        rs3.getInt("age"),
                        new Driver(rs.getInt("experience"),rs.getInt("rating")  // ???)
                        //вот тут я не понимаю, у Driver нужно сформировать AutomobileList , но у каждого Automobile есть Driver
                        // при этом Driver еще не сформирован

            }

            Driver driver = new Driver(
               rs.getInt("experience"),
               rs.getInt("user_id"),
               user,
               list
            );

        }
        //TODO: реализовать метод
        return null;
    }
}
