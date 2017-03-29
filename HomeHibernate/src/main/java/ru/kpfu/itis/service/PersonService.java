package ru.kpfu.itis.service;

import org.springframework.stereotype.Component;
import ru.kpfu.itis.dao.hibernate.PeopleDaoHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.entities.Person;

import java.util.List;

/**
 * Created by розалия on 29.03.2017.
 */
@Component
public class PersonService {

    @Autowired
    private PeopleDaoHibernate peopleDaoHibernate;

 //   @Autowired
//    PersonService(PeopleDaoHibernate peopleDaoHibernate) {
//        this.peopleDaoHibernate = peopleDaoHibernate;
//    }

    public Person getById(int id){return peopleDaoHibernate.getById(id);}

    public void addPerson(String country,String name,String surname){peopleDaoHibernate.addPerson(country,name,surname);}

    public void deletePerson(int id) {peopleDaoHibernate.deletePerson(id);}

    public void updatePerson(int id,String country,String name,String surname){peopleDaoHibernate.updatePerson(id,country,name,surname);}

    public List<Person> getAll() {return peopleDaoHibernate.getAll();}
}
