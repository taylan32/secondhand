package com.example.secondhand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private String mail;
    private String firstName;
    private String middleName;
    private String lastName;

}
