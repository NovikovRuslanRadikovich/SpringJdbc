package ru.kpfu.itis.NovikovRuslan.forms;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



public class UserForm {
    @NotEmpty
    @Size(min = 5,max = 12, message = "Password length must be between 5 and 12")
    private String password;
    @Pattern(regexp="^[a-zA-Z0-9]+$",message="Username must be alphanumeric with no spaces")
    private String username;

    public UserForm() {
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
