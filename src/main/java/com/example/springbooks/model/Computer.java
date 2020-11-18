package com.example.springbooks.model;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Computer {
    @Getter @Setter private int id;
    @Getter @Setter private String Name;
    @Getter @Setter private String OS;
}
