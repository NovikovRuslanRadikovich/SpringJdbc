package com.example.entities;






import javax.persistence.*;
import java.util.List;


@Entity
@Table
public class User {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String username;

    @Column
    String password;

    @Column
    String telephone;


    public User(){

    }

    public User(String username, String password, String telephone) {
        this.username = username;
        this.password = password;
        this.telephone = telephone;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getTelephone() {return telephone;}

    public void setTelephone(String telephone) {this.telephone = telephone;}

}
