package com.example.springbooks.forms;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComputerForm {
    private int id;
    private String Name;
    private String OS;
}
