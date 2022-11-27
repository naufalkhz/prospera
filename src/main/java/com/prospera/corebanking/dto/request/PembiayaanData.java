package com.prospera.corebanking.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class PembiayaanData {

    private long nikKtp;
    private String nama;
    private int status;
    private long jumlahPembiayaan;
    private long jumlahHarusBayar;
    private long jumlahHarusBayarBulan;
    private Date tanggalPembiayaan;
    private int tenor;
}
