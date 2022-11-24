package com.prospera.corebanking.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class OfficerData {

//    private long id;

//    @NotEmpty(message = "Name is Required !")
    private String nama;

//    @NotEmpty(message = "Email is Required!")
//    @Email(message = "Email is not valid!")
//    private String email;

    private long nikKtp;
    private String password;
    private Date tanggalLahir;
    private String tempatLahir;
    private String alamat;
    private String jabatan;
    private String cabang;
}
