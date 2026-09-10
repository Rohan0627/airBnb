package com.rohan.airBnb.dto;


import com.rohan.airBnb.Entity.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequestDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Gender gender;
    private LocalDate dateOfBirth;
}
