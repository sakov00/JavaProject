package spring.security.jwt.bean;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_computer_table")
@Data
public class UserRentForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String userName;

    @Column
    private String userSurname;

    @ManyToOne
    @JoinColumn(name = "computerStuff_id")
    private ComputerStuff computerStuff;

    @Column
    private boolean rent;

    public UserRentForm(User user, String userName, String userSurname, ComputerStuff computerStuff) {
        this.user = user;
        this.userName = userName;
        this.userSurname = userSurname;
        this.computerStuff = computerStuff;
    }

    public UserRentForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public ComputerStuff getComputerStuff() {
        return computerStuff;
    }

    public void setComputerStuff(ComputerStuff computerStuff) {
        this.computerStuff = computerStuff;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }
}
