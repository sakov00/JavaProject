package spring.security.jwt.bean;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "computer_stuff_table")
@Data
public class ComputerStuff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min = 4 , message = "login from 4 to ...")
    @NotNull(message = "Can not be null")
    private String name;

    @Column
    @Size(min = 4 , message = "login from 4 to ...")
    @NotNull(message = "Can not be null")
    private String description;

    @Column
    private int cost;

    @Column
    private Date expirationDate;

    public ComputerStuff() {
    }

    public ComputerStuff(@Size(min = 4, message = "login from 4 to ...") @NotNull(message = "Can not be null") String name, @Size(min = 4, message = "login from 4 to ...") @NotNull(message = "Can not be null") String description, int cost, Date expirationDate) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.expirationDate = expirationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
