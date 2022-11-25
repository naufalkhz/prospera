package com.prospera.corebanking.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class NasabahData {

//    private long id;

//    @NotEmpty(message = "Name is Required !")
    //private String nama;

//    @NotEmpty(message = "Email is Required!")
//    @Email(message = "Email is not valid!")
//    private String email;
    private String nama;
    private long nikKtp;
    private String email;
    private String password;
    private String noHP;
    private String pekerjaan;
    private String alamat;
    private int flagWarungTepat;
    private Date tanggalBuat;
}
