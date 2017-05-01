package ru.kpfu.itis.NovikovRuslan.entities;


import ru.kpfu.itis.NovikovRuslan.enumeration.UserRole;

import javax.persistence.*;


@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_generator")
    @SequenceGenerator(name = "users_id_generator", sequenceName = "users_id_seq")
    Long id;


    @Column(name = "username",unique = true)
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "role")
    private UserRole role;

    public Long getId() {
        return id;
    }

    public User(){}

    public User(String username, String password,UserRole role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {return role;}

    public void setRole(UserRole role){this.role = role;}

}
