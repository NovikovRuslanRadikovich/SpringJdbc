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
    String reservationmodel;

    @Column
    String give_date;

    @Column
    String back_date;

    public Reservation(){}

    public Reservation(String username, String telephone, String model, String give_date,
                       String back_date){
        this.username = username;
        this.telephone = telephone;
        this.reservationmodel = model;
        this.give_date = give_date;
        this.back_date = back_date;
    }
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getTelephone() {return telephone;}

    public void setTelephone(String telephone) {this.telephone = telephone;}

    public String getReservationmodel() {return reservationmodel;}

    public void setReservationmodel(String model) {this.reservationmodel = model;}

    public String getGive_date() {return give_date;}

    public void setGive_date(String give_date) {this.give_date = give_date;}

    public String getBack_date() {return back_date;}

    public void setBack_date(String back_date) {this.back_date = back_date;}

}
