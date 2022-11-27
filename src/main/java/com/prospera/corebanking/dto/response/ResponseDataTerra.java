package com.prospera.corebanking.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseDataTerra<T> {
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private T payload;
//    private long noRekening;
    private NasabahDTO nasabahDTO;
}
