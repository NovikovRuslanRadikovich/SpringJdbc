package com.example.entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
public class Automobile {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String model;

    @Column
    Long year;

    @Column
    Long probeg;

    @Column
    Long powerty;

    @Column
    Long rentpay;

    public Automobile(){}

    public Automobile(String model, Long year, Long probeg, Long powerty, Long rentpay ){
        this.model = model;
        this.year = year;
        this.probeg = probeg;
        this.powerty = powerty;
        this.rentpay = rentpay;

    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getModel() {return model;}

    public void setModel(String model) {this.model = model;}

    public Long getYear() {return year;}

    public void setYear(Long year) {this.year = year;}

    public Long getProbeg() {return probeg;}

    public void setProbeg(Long probeg) {this.probeg = probeg;}

    public Long getPowerty() {return powerty;}

    public void setPowerty(Long powerty) {this.powerty = powerty;}

    public Long getRentpay() {return rentpay;}

    public void setRentpay(Long rentpay) {this.rentpay = rentpay;}
}
