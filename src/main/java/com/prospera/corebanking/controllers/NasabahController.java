package com.prospera.corebanking.controllers;


import com.prospera.corebanking.dto.models.entities.Nasabah;
import com.prospera.corebanking.dto.models.entities.Officer;
import com.prospera.corebanking.dto.request.NasabahData;
import com.prospera.corebanking.dto.response.ResponseData;
import com.prospera.corebanking.services.NasabahService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/nasabah")
public class NasabahController {
    @Autowired
    private NasabahService nasabahService;

    @Autowired(required = true)
    private ModelMapper modelMapper;

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// CREATE NASABAH ///////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping
    public ResponseEntity<ResponseData<Nasabah>> create (@RequestBody @Valid NasabahData nasabahData, Errors errors){
        ResponseData<Nasabah> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Nasabah nasabah = nasabahService.saveNasabah(nasabahData);
        responseData.setStatus(true);
        responseData.setPayload(nasabah);
        return ResponseEntity.ok(responseData);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// GET ALL OFFICER //////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Nasabah>>> findAll(){
        ResponseData<Iterable<Nasabah>> responseData = new ResponseData<>();
        Iterable<Nasabah> nasabah = nasabahService.findAll();
        responseData.setStatus(true);
        responseData.setPayload(nasabah);

        return ResponseEntity.ok(responseData);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// GET ONE OFFICER BY NIK ///////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/{nikKtp}")
    public ResponseEntity<ResponseData<Nasabah>> findOne(@PathVariable("nikKtp") Long nikKtp){
        ResponseData<Nasabah> responseData = new ResponseData<>();
        Nasabah nasabah = nasabahService.findByNikKtp(nikKtp);
        responseData.setStatus(true);
        responseData.setPayload(nasabah);

        return ResponseEntity.ok(responseData);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// UPDATE OFFICER ///////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping
    public ResponseEntity<ResponseData<Nasabah>> update (@RequestBody @Valid Nasabah nasabahData, Errors errors){

        System.out.println(nasabahData);
        ResponseData<Nasabah> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Nasabah nasabah = modelMapper.map(nasabahData, Nasabah.class);
        responseData.setStatus(true);
        responseData.setPayload(nasabahService.update(nasabah));
        return ResponseEntity.ok(responseData);
    }



}
