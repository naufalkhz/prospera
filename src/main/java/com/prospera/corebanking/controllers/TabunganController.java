package com.prospera.corebanking.controllers;


import com.prospera.corebanking.dto.models.entities.Tabungan;
import com.prospera.corebanking.dto.request.TransaksiSaldo;
import com.prospera.corebanking.dto.response.ResponseData;
import com.prospera.corebanking.dto.response.TabunganDTO;
import com.prospera.corebanking.services.TabunganService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/tabungan")
public class TabunganController {
    @Autowired
    private TabunganService tabunganService;

    @Autowired(required = true)
    private ModelMapper modelMapper;



    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// GET ALL TABUNGAN /////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Tabungan>>> findAll(){
        ResponseData<Iterable<Tabungan>> responseData = new ResponseData<>();
        Iterable<Tabungan> tabungan = tabunganService.findAll();

        responseData.setStatus(true);
        responseData.setPayload(tabungan);

        return ResponseEntity.ok(responseData);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// GET ONE OFFICER BY NIK ///////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/{norekening}")
    public ResponseEntity<ResponseData<Tabungan>> findTabunganByNoRekening(@PathVariable("norekening") Long norek){
        ResponseData<Tabungan> responseData = new ResponseData<>();
        Tabungan pembiayaan = tabunganService.findByNoRekening(norek);
        responseData.setStatus(true);
        responseData.setPayload(pembiayaan);

        return ResponseEntity.ok(responseData);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// UPDATE OFFICER ///////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping
    public ResponseEntity<ResponseData<Tabungan>> update (@RequestBody @Valid Tabungan tabunganData, Errors errors){

        System.out.println(tabunganData);
        ResponseData<Tabungan> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Tabungan tabungan = modelMapper.map(tabunganData, Tabungan.class);
        responseData.setStatus(true);
        responseData.setPayload(tabunganService.update(tabungan));
        return ResponseEntity.ok(responseData);
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// UPDATE SALDO ///////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/transaksi-saldo")
    public ResponseEntity<ResponseData<Tabungan>> updateSaldo (@RequestBody @Valid TransaksiSaldo transaksiSaldo, Errors errors){

        System.out.println(transaksiSaldo);
        Tabungan tabungan = tabunganService.findByNikKtp(transaksiSaldo.getNikKtp());
        tabungan.setSaldo(tabungan.getSaldo() + transaksiSaldo.getNominal());
        tabunganService.update(tabungan);

        ResponseData<Tabungan> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Tabungan updatedTabungan = modelMapper.map(tabungan, Tabungan.class);
        responseData.setStatus(true);
        responseData.setPayload(updatedTabungan);
        return ResponseEntity.ok(responseData);
    }

}
