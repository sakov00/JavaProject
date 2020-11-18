package com.example.springbooks.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentUsers {
    @Getter @Setter private int Id;
    @Getter @Setter private String LoginUser;
    @Getter @Setter private String FirstName;
    @Getter @Setter private String SecondName;
    @Getter @Setter private int CountOfRentalDays;
}
