package com.rohan.airBnb.dto;


import com.rohan.airBnb.Entity.enums.Gender;
import com.rohan.airBnb.Entity.enums.Role;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Gender gender;
    private LocalDate dateOfBirth;
    private Set<Role> roles;
}
