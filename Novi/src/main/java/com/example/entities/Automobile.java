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
    String automodel;

    @Column
    String year;

    @Column
    String probeg;

    @Column
    String powerty;

    @Column
    String rentpay;

    public Automobile(){}

    public Automobile(String automodel, String  year, String probeg, String powerty, String rentpay ){
        this.automodel = automodel;
        this.year = year;
        this.probeg = probeg;
        this.powerty = powerty;
        this.rentpay = rentpay;

    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getAutomodel() {return automodel;}

    public void setAutomodel(String automodel) {this.automodel = automodel;}

    public String getYear() {return year;}

    public void setYear(String year) {this.year = year;}

    public String getProbeg() {return probeg;}

    public void setProbeg(String probeg) {this.probeg = probeg;}

    public String getPowerty() {return powerty;}

    public void setPowerty(String powerty) {this.powerty = powerty;}

    public String getRentpay() {return rentpay;}

    public void setRentpay(String rentpay) {this.rentpay = rentpay;}
}
