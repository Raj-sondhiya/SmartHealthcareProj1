package com.example.demo.dto;

import lombok.Data;

@Data
public class PatientUpdateDTO {
    private String firstName;
    private String lastName;
    private String address;
    private Integer age;
    private Long phoneNo;
    private String password;

    // Getters and setters
    // (You can use Lombok annotations like @Data if preferred)
}
