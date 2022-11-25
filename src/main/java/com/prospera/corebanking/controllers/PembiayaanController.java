package com.prospera.corebanking.controllers;


import com.prospera.corebanking.dto.models.entities.Officer;
import com.prospera.corebanking.dto.models.entities.Pembiayaan;
import com.prospera.corebanking.dto.request.OfficerData;
import com.prospera.corebanking.dto.request.PembiayaanData;
import com.prospera.corebanking.dto.response.ResponseData;
import com.prospera.corebanking.services.OfficerService;
import com.prospera.corebanking.services.PembiayaanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pembiayaan")
public class PembiayaanController {
    @Autowired
    private PembiayaanService pembiayaanService;

    @Autowired(required = true)
    private ModelMapper modelMapper;

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// CREATE PEMBIAYAAN ///////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping
    public ResponseEntity<ResponseData<Pembiayaan>> create (@RequestBody @Valid PembiayaanData pembiayaanData, Errors errors){
        ResponseData<Pembiayaan> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Pembiayaan pembiayaan = pembiayaanService.savePembiayaan(pembiayaanData);
        responseData.setStatus(true);
        responseData.setPayload(pembiayaan);
        return ResponseEntity.ok(responseData);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// GET ALL OFFICER //////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping
    public Iterable<Pembiayaan> findAll(){
        return pembiayaanService.findAll();
    }



}
