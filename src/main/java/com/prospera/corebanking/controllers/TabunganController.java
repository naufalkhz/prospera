package com.prospera.corebanking.controllers;


import com.prospera.corebanking.dto.models.entities.Officer;
import com.prospera.corebanking.dto.models.entities.Tabungan;
import com.prospera.corebanking.dto.request.OfficerData;
import com.prospera.corebanking.dto.response.ResponseData;
import com.prospera.corebanking.services.OfficerService;
import com.prospera.corebanking.services.TabunganService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tabungan")
public class TabunganController {
    @Autowired
    private TabunganService tabunganService;

    @Autowired(required = true)
    private ModelMapper modelMapper;



    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// GET ALL OFFICER //////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping
    public Iterable<Tabungan> findAll(){
        return tabunganService.findAll();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// GET ONE OFFICER BY NIK ///////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/{norekening}")
    public Tabungan findByNoRekening(@PathVariable("noRekening") Long noRekening){
        return tabunganService.findByNoRekening(noRekening);
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


}
