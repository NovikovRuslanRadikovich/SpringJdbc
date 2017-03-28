package components;

import Mapper.PeopleMapper;
import Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by розалия on 19.03.2017.
 */

@Repository
public class DaoImplController {


    @Autowired
    JdbcTemplate jdbcTemplate;


    public  List<Person> getAll() {
        String SQL = "SELECT * FROM people";
        List<Person> people = jdbcTemplate.query(SQL, new PeopleMapper());
        return people;
    }

    public void updatePerson(int id,String country, String name,String surname) {
        String SQL = "UPDATE people set country = ?, name = ?, surname = ?  where id = ?";
        jdbcTemplate.update(SQL, country,name,surname,id);
    }

    public void deletePerson(int id) {
        String SQL = "DELETE FROM people where id = ?";
        jdbcTemplate.update(SQL, id);
    }

    public void addPerson(String country, String name, String surname) {
        String SQL = "INSERT INTO people (country,name,surname) values (?,?,?);";
        jdbcTemplate.update(SQL,country,name,surname);
    }

    public Person getById(int id) {
        String SQL = "SELECT * FROM people where id = ? ";
        List<Person> person =  jdbcTemplate.query(SQL,new Integer[] {id}, new PeopleMapper());
        return person.get(0);
    }

}
