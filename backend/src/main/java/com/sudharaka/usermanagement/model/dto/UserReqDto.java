package com.sudharaka.usermanagement.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserReqDto {

    private String firstName;
    private String lastName;
    @NotBlank(message = "User must have a user name")
    private String userName;
    @NotBlank(message = "User must have a password")
    private String password;
}
