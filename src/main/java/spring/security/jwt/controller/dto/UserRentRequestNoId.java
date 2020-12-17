package spring.security.jwt.controller.dto;

import spring.security.jwt.bean.ComputerStuff;
import spring.security.jwt.bean.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRentRequestNoId {
    @NotNull
    private User user;
    @NotNull
    @Size(min = 4 , message = "name from 4 ")
    private String name;
    @NotNull
    @Size(min = 4 , message = "surname from 4 ")
    private String surname;
    @NotNull
    private ComputerStuff computerStuff;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ComputerStuff getComputerStuff() {
        return computerStuff;
    }

    public void setComputerStuff(ComputerStuff computerStuff) {
        this.computerStuff = computerStuff;
    }
}
