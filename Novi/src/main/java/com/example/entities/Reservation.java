package com.example.entities;

import javax.persistence.*;


@Entity
@Table
public class Reservation {
    @Id
    @GeneratedValue
    Long id;

    @Column
    String username;

    @Column
    String telephone;

    @Column
    String model;

    @Column
    String give_date;

    @Column
    String back_date;

    public Reservation(){}

    public Reservation(String username, String telephone, String model, String give_date,
                       String back_date){
        this.username = username;
        this.telephone = telephone;
        this.model = model;
        this.give_date = give_date;
        this.back_date = back_date;
    }
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getTelephone() {return telephone;}

    public void setTelephone(String telephone) {this.telephone = telephone;}

    public String getModel() {return model;}

    public void setModel(String model) {this.model = model;}

    public String getStartdate() {return give_date;}

    public void setStartdate(String give_date) {this.give_date = give_date;}

    public String getEnddate() {return back_date;}

    public void setEnddate(String back_date) {this.back_date = back_date;}

}
