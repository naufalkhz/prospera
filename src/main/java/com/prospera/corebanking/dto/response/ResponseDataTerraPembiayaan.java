package com.prospera.corebanking.dto.response;

import com.prospera.corebanking.dto.models.entities.Pembiayaan;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseDataTerraPembiayaan<T> {
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private T payload;
//    private long noRekening;
    private Pembiayaan pembiayaan;
}
