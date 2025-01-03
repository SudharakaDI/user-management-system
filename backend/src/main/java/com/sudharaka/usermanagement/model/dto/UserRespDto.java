package com.sudharaka.usermanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRespDto {

    private long id;
    private String firstName;
    private String lastName;
}
