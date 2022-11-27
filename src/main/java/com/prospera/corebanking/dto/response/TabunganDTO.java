package com.prospera.corebanking.dto.response;

import com.prospera.corebanking.dto.models.entities.Pembiayaan;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class TabunganDTO {
    private long nikKtp;
    private long noRekening;
    private long saldo;

    private String nama;
}
