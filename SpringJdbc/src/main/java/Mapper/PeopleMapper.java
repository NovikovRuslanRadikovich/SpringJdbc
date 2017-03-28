package Mapper;

import Models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by розалия on 20.03.2017.
 */
public class PeopleMapper implements RowMapper<Person> {

    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person(1,"a","a","a");
        person.setId(rs.getInt("id"));
        person.setCountry(rs.getString("country"));
        person.setName(rs.getString("name"));
        person.setSurname(rs.getString("surname"));
        return person;
    }
}
