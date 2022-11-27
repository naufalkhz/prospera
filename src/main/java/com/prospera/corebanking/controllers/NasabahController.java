package com.prospera.corebanking.controllers;


import com.prospera.corebanking.dto.models.entities.Nasabah;
import com.prospera.corebanking.dto.request.NasabahData;
import com.prospera.corebanking.dto.response.NasabahDTO;
import com.prospera.corebanking.dto.response.ResponseData;
import com.prospera.corebanking.dto.response.ResponseDataTerra;
import com.prospera.corebanking.services.NasabahService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin("*")
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
    @PostMapping("/create")
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
    @GetMapping("/all")
    public ResponseEntity<ResponseData<Iterable<Nasabah>>> findAll(){
        ResponseData<Iterable<Nasabah>> responseData = new ResponseData<>();
        Iterable<Nasabah> nasabah = nasabahService.findAll();
        responseData.setStatus(true);
        responseData.setPayload(nasabah);

        return ResponseEntity.ok(responseData);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// GET ONE NASABAH BY NIK ///////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/{nikKtp}")
    public ResponseEntity<ResponseData<NasabahDTO>> findOne(@PathVariable("nikKtp") Long nikKtp){
        System.out.println("masuk nasabah controller");
        NasabahDTO nasabah = nasabahService.findByNikKtp(nikKtp);
        System.out.println(nasabah);
        ResponseData<NasabahDTO> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setPayload(nasabah);

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/terra/{nikKtp}")
    public ResponseEntity<ResponseDataTerra<NasabahDTO>> findOneTerra(@PathVariable("nikKtp") Long nikKtp){
        System.out.println("terra masuk nasabah controller");
        NasabahDTO nasabah = nasabahService.findByNikKtp(nikKtp);
        System.out.println(nasabah);
        ResponseDataTerra<NasabahDTO> responseData = new ResponseDataTerra<>();

        responseData.setStatus(true);
        responseData.setPayload(nasabah);
        responseData.setNasabahDTO(nasabah);


        return ResponseEntity.ok(responseData);
    }



    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// UPDATE OFFICER ///////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/update")
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


    @PostMapping(value = "/login-warung-tepat", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<Nasabah>> loginNasabahurlencoded (@Valid NasabahData nasabahData, Errors errors){
        Nasabah nasabah = nasabahService.findNasabahByNomorHandphone(nasabahData.getNoHP(), nasabahData.getPassword());
        System.out.println(nasabah);
        ResponseData<Nasabah> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

//        Nasabah nasabah = nasabahService.saveNasabah(nasabahData);
        responseData.setStatus(true);
        responseData.setPayload(nasabah);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<Nasabah>> loginNasabahJson (@RequestBody @Valid NasabahData nasabahData, Errors errors){
        Nasabah nasabah = nasabahService.findNasabahByNomorHandphone(nasabahData.getNoHP(), nasabahData.getPassword());
        System.out.println(nasabah);
        ResponseData<Nasabah> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

//        Nasabah nasabah = nasabahService.saveNasabah(nasabahData);
        responseData.setStatus(true);
        responseData.setPayload(nasabah);
        return ResponseEntity.ok(responseData);
    }

}
