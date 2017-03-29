package ru.kpfu.itis.entities;

import javax.persistence.*;

/**
 * Created by розалия on 29.03.2017.
 */
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_generator")
    @SequenceGenerator(name = "person_id_generator", sequenceName = "people_id_seq",allocationSize = 1)
    int id;
    private String country;
    private String name;
    private String surname;

    public Person() {

    }

    public Person(int id,String country,String name,String surname) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
