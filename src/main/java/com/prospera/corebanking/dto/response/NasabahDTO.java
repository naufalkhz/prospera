package com.prospera.corebanking.dto.response;

import com.prospera.corebanking.dto.models.entities.Pembiayaan;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class NasabahDTO {

    private String nama;
    private long nikKtp;
    private String email;
    private String password;
    private String noHP;
    private String pekerjaan;
    private String alamat;
    private int flagWarungTepat;
    private Date tanggalBuat;
    Iterable<Pembiayaan> pembiayaan = new ArrayList<>();
    private long noRekening;
    private long saldo;
}
