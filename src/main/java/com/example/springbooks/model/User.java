package com.example.springbooks.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Getter @Setter private int Id;
    @Getter @Setter private String Login;
    @Getter @Setter private String Password;
}
