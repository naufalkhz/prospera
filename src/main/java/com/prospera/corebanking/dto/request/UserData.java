package com.prospera.corebanking.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class UserData {

//    private long id;

//    @NotEmpty(message = "Name is Required !")

//    @NotEmpty(message = "Email is Required!")
//    @Email(message = "Email is not valid!")
//    private String email;

    private String email;
    private String password;
}
