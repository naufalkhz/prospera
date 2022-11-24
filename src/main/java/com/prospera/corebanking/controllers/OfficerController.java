package com.prospera.corebanking.controllers;

import com.prospera.corebanking.dto.models.entities.Officer;
import com.prospera.corebanking.dto.request.OfficerData;
import com.prospera.corebanking.dto.response.ResponseData;
import com.prospera.corebanking.services.OfficerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/officer")
public class OfficerController {
    @Autowired
    private OfficerService officerService;

    @Autowired(required = true)
    private ModelMapper modelMapper;

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// CREATE OFFICER ///////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping
    public ResponseEntity<ResponseData<Officer>> create (@RequestBody @Valid OfficerData officerData, Errors errors){
        ResponseData<Officer> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Officer officer = officerService.saveOfficer(officerData);
        responseData.setStatus(true);
        responseData.setPayload(officer);
        return ResponseEntity.ok(responseData);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// GET ALL OFFICER //////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping
    public Iterable<Officer> findAll(){
        return officerService.findAll();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// GET ONE OFFICER BY NIK ///////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/{nikKaryawan}")
    public Officer findOfficerByNikKaryawan(@PathVariable("nikKaryawan") Long nikKaryawan){
        return officerService.findByNikKaryawan(nikKaryawan);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// UPDATE OFFICER ///////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping
    public ResponseEntity<ResponseData<Officer>> update (@RequestBody @Valid Officer officerData, Errors errors){

        System.out.println(officerData);
        ResponseData<Officer> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Officer officer = modelMapper.map(officerData, Officer.class);
        responseData.setStatus(true);
        responseData.setPayload(officerService.update(officer));
        return ResponseEntity.ok(responseData);
    }


}
