package com.prospera.corebanking.services;

import com.prospera.corebanking.dto.models.entities.Officer;
import com.prospera.corebanking.dto.models.entities.User;
import com.prospera.corebanking.dto.models.repos.UserRepo;
import com.prospera.corebanking.dto.request.OfficerData;
import com.prospera.corebanking.dto.models.repos.OfficerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OfficerService {
    @Autowired
    private OfficerRepo officerRepo;
    @Autowired
    private UserRepo userRepo;

    public Officer saveOfficer (OfficerData officerData){

        // Buat handler jika number nya ke generate yang sama

        System.out.println(officerData);

        //handler duplicate
        long number = (long) Math.floor(Math.random() * 9_000_000L) + 1_000_000L;
        //long number =4317203;
        Officer officer = new Officer();
        //handler duplicate
        Officer officers = officerRepo.findByNikKaryawan(number);
        while(officers != null){
            // membuat kembali no rekening
            number = (long) Math.floor(Math.random() * 9_000_000L) + 1_000_000L;
            break;
        }
        officer.setEmail(number+"@btpns.com");
        officer.setPassword(officerData.getPassword());

        officer.setNikKaryawan(number);

        officer.setNama(officerData.getNama());
        officer.setAlamat(officerData.getAlamat());
        officer.setJabatan(officerData.getJabatan());

        officer.setTempatLahir(officerData.getTempatLahir());
        officer.setTanggalLahir(officerData.getTanggalLahir());
        officer.setNikKtp(officerData.getNikKtp());
        officer.setCabang(officerData.getCabang());
        officer.setStatus(1);

        User user = new User();
        user.setEmail(number+"@btpns.com");
        user.setPassword(officerData.getPassword());
        user.setHak(officerData.getJabatan());
        user.setNikKaryawan(number);

        System.out.println(user);
        userRepo.save(user);

        return officerRepo.save(officer);
    }

    /*public Officer findOne (Long id){
        Optional<Officer> supplier = officerRepo.findById(id);
        if (!supplier.isPresent()){
            return null;
        }
        return supplier.get();
    }*/

    public Officer findByNikKaryawan (Long nikKaryawan){
        Officer officer = officerRepo.findByNikKaryawan(nikKaryawan);
//        if (!supplier.isPresent()){
//            return null;
//        }
        return officer;
    }

    public Officer update (Officer officer){
        return officerRepo.save(officer);
    }

    public Iterable<Officer> findAll(){
        return officerRepo.findAll();
    }

    public void removeOne(Long id){
        officerRepo.deleteById(id);
    }
}
