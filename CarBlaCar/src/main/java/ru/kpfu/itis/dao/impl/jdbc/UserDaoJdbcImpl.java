package ru.kpfu.itis.dao.impl.jdbc;

import org.apache.log4j.Logger;
import ru.kpfu.itis.dao.UsersDao;
import ru.kpfu.itis.dao.factory.JDBCConnectionFactory;
import ru.kpfu.itis.dao.impl.hibernate.AutosDaoHibernateImpl;
import ru.kpfu.itis.model.User;

import java.sql.*;
import java.util.List;

public class UserDaoJdbcImpl implements UsersDao{

    private static final Logger logger = Logger.getLogger(AutosDaoHibernateImpl.class);

    Connection con = null;
    Statement stmt = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    private Connection getConnection() throws SQLException {
        return JDBCConnectionFactory.getInstance().getConnection();
    }
    @Override
    public void save(User user) throws SQLException {
            con = getConnection();
            ptmt = con.prepareStatement("Insert into users(nickname,password,firstname,surname,avatar,email,role) values(?,?,?,?,?,?,?);");
            ptmt.setString(1,user.getNickname());
            ptmt.setString(2,user.getPassword());
            ptmt.setString(3,user.getFirstname());
            ptmt.setString(4,user.getSurname());
            ptmt.setString(5,user.getAvatar());
            ptmt.setString(6,user.getEmail());
            ptmt.setString(7,user.getRole());
            ptmt.execute();

        //TODO: реализовать метод
    }

    @Override
    public User findOne(Long userId) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByNickname(String nickname) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByNicknameIgnoreCase(String name) throws SQLException {
        con = getConnection();
        stmt = con.createStatement();
        User user = null;
        rs = stmt.executeQuery("Select * from users where nickname = '" + name +"'");
        user = new User(
                rs.getString("nickname"),
                rs.getString("password"),
                rs.getString("firstname"),
                rs.getString("surname"),
                rs.getString("email"),
                rs.getString("role"),
                rs.getString("avatar")
        );
        user.setId((long) rs.getInt("id"));
        //TODO: реализовать метод
        return user;
    }

    @Override
    public User findByEmailIgnoreCase(String email) {
        return null;
    }
}
