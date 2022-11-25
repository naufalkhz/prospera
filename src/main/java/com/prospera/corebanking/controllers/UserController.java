package com.prospera.corebanking.controllers;


import com.prospera.corebanking.dto.models.entities.Officer;
import com.prospera.corebanking.dto.models.entities.User;
import com.prospera.corebanking.dto.request.OfficerData;
import com.prospera.corebanking.dto.request.UserData;
import com.prospera.corebanking.dto.response.ResponseData;
import com.prospera.corebanking.services.OfficerService;
import com.prospera.corebanking.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private OfficerService officerService;


    @Autowired(required = true)
    private ModelMapper modelMapper;

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// CREATE OFFICER ///////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping
    public ResponseEntity<ResponseData<Officer>> login (@RequestBody @Valid UserData userData, Errors errors){
        ResponseData<Officer> responseData = new ResponseData<>();
        System.out.println("Masuk");
        User foundUser = userService.findByEmail(userData.getEmail());

        long nik = foundUser.getNikKaryawan();

        Officer officer = officerService.findByNikKaryawan(nik);
//        if(errors.hasErrors()){
//            for(ObjectError error : errors.getAllErrors()){
//                responseData.getMessages().add(error.getDefaultMessage());
//            }
//            responseData.setStatus(false);
//            responseData.setPayload(null);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
//        }
//
//        User user = userService.s(userData);
        responseData.setStatus(true);
        responseData.setPayload(officer);
        return ResponseEntity.ok(responseData);
    }



}
