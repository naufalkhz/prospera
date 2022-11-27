package com.prospera.corebanking.dto.request;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class TabunganData {


    private long nikKtp;
    private long noRekening;
    private long saldo;
    private String nama;
}
